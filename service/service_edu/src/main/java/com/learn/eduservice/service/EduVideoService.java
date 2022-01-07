package com.learn.eduservice.service;

import com.learn.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-03-04
 */
public interface EduVideoService extends IService<EduVideo> {

    void removeVideoByCourseId(String courseId);


}
