package com.learn.staservice.client;

import com.learn.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Description:
 * date: 2021/3/13 16:26
 * Package: com.learn.staservice.client
 *
 * @author 李佳乐
 * @version 1.0
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {

    @GetMapping("/ucenter/member/countRegister/{day}")
    public R countRegister(@PathVariable("day") String day);

}
