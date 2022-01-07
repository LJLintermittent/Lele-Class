package com.learn.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.learn.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.eduservice.entity.frontvo.CourseFrontVo;
import com.learn.eduservice.entity.frontvo.CourseWebVo;
import com.learn.eduservice.entity.vo.CourseInfoVo;
import com.learn.eduservice.entity.vo.CoursePublishVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-03-04
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo publishCourseInfo(String id);

    void removeCourse(String courseId);

    Map<String, Object> getCourseFrontList(Page<EduCourse> page, CourseFrontVo courseFrontVo);

    CourseWebVo getBaseCourseInfo(String courseId);

}
