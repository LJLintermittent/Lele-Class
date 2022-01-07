package com.learn.eduservice.client;

import com.learn.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Description:
 * date: 2021/3/8 16:19
 * Package: com.learn.eduservice.client
 *
 * @author 李佳乐
 * @version 1.0
 */
@FeignClient(name = "service-vod",fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {

    @DeleteMapping("/eduvod/video/removeVideo/{id}")
    public R removeVideo(@PathVariable("id") String id);

    @DeleteMapping("/eduvod/video/delete-batch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);

}
