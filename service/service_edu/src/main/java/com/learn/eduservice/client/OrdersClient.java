package com.learn.eduservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Description:
 * date: 2021/3/13 14:42
 * Package: com.learn.eduservice.client
 *
 * @author 李佳乐
 * @version 1.0
 */
@Component
@FeignClient(value = "service-order",fallback = OrdersFileDegradeFeignClient.class)
public interface OrdersClient {

    @GetMapping("/eduorder/order/isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable("courseId") String courseId, @PathVariable("memberId") String memberId);

}
