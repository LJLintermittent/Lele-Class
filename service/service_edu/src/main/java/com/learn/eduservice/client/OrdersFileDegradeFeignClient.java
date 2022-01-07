package com.learn.eduservice.client;

import org.springframework.stereotype.Component;

/**
 * Description:
 * date: 2021/3/13 15:23
 * Package: com.learn.eduservice.client
 *
 * @author 李佳乐
 * @version 1.0
 */
@Component
public class OrdersFileDegradeFeignClient implements OrdersClient{

    @Override
    public boolean isBuyCourse(String courseId, String memberId) {
        return false;
    }
}
