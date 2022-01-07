package com.learn.eduservice.controller;


import com.learn.commonutils.R;
import com.learn.eduservice.client.VodClient;
import com.learn.eduservice.entity.EduVideo;
import com.learn.eduservice.service.EduVideoService;
import com.learn.servicebase.exceptionhandler.GuLiException;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-04
 */
@Api(description = "课程小节模块")
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private VodClient vodClient;

    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo) {
        eduVideoService.save(eduVideo);
        return R.ok();
    }

    @DeleteMapping("deleteVideo/{videoId}")
    public R deleteVideo(@PathVariable String videoId) {
        EduVideo video = eduVideoService.getById(videoId);
        String videoSourceId = video.getVideoSourceId();
        if (!StringUtils.isEmpty(videoSourceId)) {
            R result = vodClient.removeVideo(videoSourceId);
            if (result.getCode() == 20001) {
                throw new GuLiException(20001, "删除小节失败，熔断器启动");
            }
        }
        eduVideoService.removeById(videoId);
        return R.ok();
    }

    //修改小节 TODO


}

