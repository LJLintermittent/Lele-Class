package com.learn.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.learn.commonutils.R;
import com.learn.eduservice.entity.EduCourse;
import com.learn.eduservice.entity.EduTeacher;
import com.learn.eduservice.service.EduCourseService;
import com.learn.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * date: 2021/3/10 20:46
 * Package: com.learn.eduservice.controller.front
 *
 * @author 李佳乐
 * @version 1.0
 */
@Api(description = "前台讲师列表模块")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/teacherfront")
public class TeacherFrontController {

    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduCourseService courseService;

    @PostMapping("getTeacherFrontList/{current}/{size}")
    public R getTeacherFrontList(@PathVariable long current
            , @PathVariable long size) {
        Page<EduTeacher> page = new Page<>(current, size);
        Map<String, Object> map = teacherService.getTeacherFrontList(page);
        return R.ok().data(map);
    }

    @GetMapping("getTeacherFrontInfo/{teacherId}")
    public R getTeacherFrontInfo(@PathVariable String teacherId) {
        EduTeacher eduTeacher = teacherService.getById(teacherId);
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id", teacherId);
        List<EduCourse> courseList = courseService.list(wrapper);
        return R.ok().data("teacher", eduTeacher).data("courseList", courseList);
    }

}


