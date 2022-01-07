package com.learn.eduorder.controller;


import com.learn.commonutils.R;
import com.learn.eduorder.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-11
 */
@RestController
@RequestMapping("/eduorder/pay-log")
@CrossOrigin
public class PayLogController {

    @Autowired
    private PayLogService payLogService;

    @GetMapping("createNative/{orderNo}")
    public R createNative(@PathVariable String orderNo) {
        Map map = payLogService.createNative(orderNo);
        System.out.println("*****返回二维码map集合" + map);
        return R.ok().data(map);
    }

    @GetMapping("queryPayStatus/{orderNo}")
    public R queryPayStatus(@PathVariable String orderNo) {
        Map<String, String> map = payLogService.queryPayStatus(orderNo);
        System.out.println("*****查询订单状态map集合" + map);
        if (map == null) {
            return R.error().message("支付出错了");
        }
        if (map.get("trade_state").equals("SUCCESS")) {
            payLogService.updateOrderStatus(map);
            return R.ok().message("支付成功");
        }
        return R.ok().code(25000).message("支付中");
    }

}

