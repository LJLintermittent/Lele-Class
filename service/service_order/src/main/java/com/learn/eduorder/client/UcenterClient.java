package com.learn.eduorder.client;

import com.learn.commonutils.ordervo.MemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Description:
 * date: 2021/3/12 13:30
 * Package: com.learn.eduorder.client
 *
 * @author 李佳乐
 * @version 1.0
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {

    @PostMapping("/ucenter/member/getUserInfoOrder/{id}")
    public MemberOrder getUserInfoOrder(@PathVariable("id") String id);

}
