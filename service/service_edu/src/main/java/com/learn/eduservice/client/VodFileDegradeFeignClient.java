package com.learn.eduservice.client;

import com.learn.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description:
 * date: 2021/3/8 18:02
 * Package: com.learn.eduservice.client
 *
 * @author 李佳乐
 * @version 1.0
 */
@Component
public class VodFileDegradeFeignClient implements VodClient {

    @Override
    public R removeVideo(String id) {
        return R.error().message("删除视频出错了");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.error().message("删除多个视频出错了");
    }
}
