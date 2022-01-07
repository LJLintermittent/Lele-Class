package com.learn.eduorder.service.impl;

import com.learn.commonutils.ordervo.CourseWebOrderVo;
import com.learn.commonutils.ordervo.MemberOrder;
import com.learn.eduorder.client.EduClient;
import com.learn.eduorder.client.UcenterClient;
import com.learn.eduorder.entity.Order;
import com.learn.eduorder.mapper.OrderMapper;
import com.learn.eduorder.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.eduorder.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-03-11
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private EduClient eduClient;

    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public String saveOrder(String courseId, String memberId) {
        CourseWebOrderVo courseInfoOrder = eduClient.getCourseInfoOrder(courseId);
        MemberOrder userInfoOrder = ucenterClient.getUserInfoOrder(memberId);
        Order order = new Order();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        order.setTeacherName(courseInfoOrder.getTeacherName());
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(memberId);
        order.setMobile(userInfoOrder.getMobile());
        order.setNickname(userInfoOrder.getNickname());
        order.setStatus(0);
        order.setPayType(1);
        baseMapper.insert(order);
        return order.getOrderNo();
    }
}
