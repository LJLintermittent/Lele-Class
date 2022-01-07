package com.learn.eduorder.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.wxpay.sdk.WXPayUtil;
import com.learn.eduorder.entity.Order;
import com.learn.eduorder.entity.PayLog;
import com.learn.eduorder.mapper.PayLogMapper;
import com.learn.eduorder.service.OrderService;
import com.learn.eduorder.service.PayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.eduorder.utils.HttpClient;
import com.learn.servicebase.exceptionhandler.GuLiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-03-11
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

    @Autowired
    private OrderService orderService;

    @Override
    public Map createNative(String orderNo) {

        try {
            QueryWrapper<Order> wrapper = new QueryWrapper<>();
            wrapper.eq("order_no", orderNo);
            Order order = orderService.getOne(wrapper);

            //设置支付参数的map
            HashMap map = new HashMap();
            map.put("appid", "wx74862e0dfcf69954");
            map.put("mch_id", "1558950191");
            map.put("nonce_str", WXPayUtil.generateNonceStr());
            map.put("body", order.getCourseTitle());
            map.put("out_trade_no", orderNo);
            map.put("total_fee", order.getTotalFee().multiply(new BigDecimal("100")).longValue() + "");
            map.put("spbill_create_ip", "127.0.0.1");
            map.put("notify_url", "http://guli.shop/api/order/weixinPay/weixinNotify\n");
            map.put("trade_type", "NATIVE");

            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
            client.setXmlParam(WXPayUtil.generateSignedXml(map, "T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            client.setHttps(true);
            client.post();

            String xml = client.getContent();
            //返回结果的map
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);
            //封装返回结果集的map
            HashMap finalMap = new HashMap();
            finalMap.put("out_trade_no", orderNo);
            finalMap.put("course_id", order.getCourseId());
            finalMap.put("total_fee", order.getTotalFee());
            finalMap.put("result_code", resultMap.get("result_code"));
            finalMap.put("code_url", resultMap.get("code_url"));

            return finalMap;
        } catch (Exception e) {
            throw new GuLiException(20001, "生成二维码失败");
        }
    }

    @Override
    public Map<String, String> queryPayStatus(String orderNo) {
        try {
            Map m = new HashMap<>();
            m.put("appid", "wx74862e0dfcf69954");
            m.put("mch_id", "1558950191");
            m.put("out_trade_no", orderNo);
            m.put("nonce_str", WXPayUtil.generateNonceStr());
            HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
            client.setXmlParam(WXPayUtil.generateSignedXml(m, "T6m9iK73b0kn9g5v426MKfHQH7X8rKwb"));
            client.setHttps(true);
            client.post();
            String xml = client.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);
            return resultMap;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void updateOrderStatus(Map<String, String> map) {
        String orderNo = map.get("out_trade_no");
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", orderNo);
        Order order = orderService.getOne(wrapper);
        if (order.getStatus().intValue() == 1) {
            return;
        }
        order.setStatus(1);
        orderService.updateById(order);
        PayLog payLog = new PayLog();
        payLog.setOrderNo(orderNo);
        payLog.setPayTime(new Date());
        payLog.setPayType(1);//支付类型
        payLog.setTotalFee(order.getTotalFee());//总金额(分)
        payLog.setTradeState(map.get("trade_state"));//支付状态
        payLog.setTransactionId(map.get("transaction_id"));
        payLog.setAttr(JSONObject.toJSONString(map));
        baseMapper.insert(payLog);//插入到支付日志表

    }
}
