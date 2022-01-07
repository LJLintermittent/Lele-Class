package com.learn.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.learn.commonutils.R;
import com.learn.eduservice.entity.EduCourse;
import com.learn.eduservice.entity.EduTeacher;
import com.learn.eduservice.service.EduCourseService;
import com.learn.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description:
 * date: 2021/3/9 14:52
 * Package: com.learn.eduservice.controller.front
 *
 * @author 李佳乐
 * @version 1.0
 */
@Api(description = "前台热门课程与讲师模块")
@RestController
@RequestMapping("/eduservice/indexfront")
@CrossOrigin
public class IndexFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduTeacherService teacherService;

    @Cacheable(key = "'index'",value = "courseAndTeacher")
    @GetMapping("index")
    public R index() {
        QueryWrapper<EduCourse> CourseWrapper = new QueryWrapper<>();
        CourseWrapper.orderByDesc("gmt_create");
        CourseWrapper.last("limit 8");
        List<EduCourse> courseList = courseService.list(CourseWrapper);

        QueryWrapper<EduTeacher> teacherWrapper = new QueryWrapper<>();
        teacherWrapper.orderByDesc("gmt_create");
        teacherWrapper.last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(teacherWrapper);

        return R.ok().data("courseList", courseList).data("teacherList", teacherList);

    }

}
