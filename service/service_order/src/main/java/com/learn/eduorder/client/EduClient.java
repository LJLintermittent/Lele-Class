package com.learn.eduorder.client;

import com.learn.commonutils.ordervo.CourseWebOrderVo;
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
@FeignClient("service-edu")
public interface EduClient {

    @PostMapping("/eduservice/coursefront/getCourseInfoOrder/{id}")
    public CourseWebOrderVo getCourseInfoOrder(@PathVariable("id") String id);

}
