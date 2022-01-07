package com.learn.eduorder.service;

import com.learn.eduorder.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-03-11
 */
public interface OrderService extends IService<Order> {

    String saveOrder(String courseId, String memberId);

}
