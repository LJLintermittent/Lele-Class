package com.learn.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.learn.commonutils.JwtUtils;
import com.learn.commonutils.R;
import com.learn.commonutils.ordervo.CourseWebOrderVo;
import com.learn.eduservice.client.OrdersClient;
import com.learn.eduservice.entity.EduCourse;
import com.learn.eduservice.entity.chapter.ChapterVo;
import com.learn.eduservice.entity.frontvo.CourseFrontVo;
import com.learn.eduservice.entity.frontvo.CourseWebVo;
import com.learn.eduservice.service.EduChapterService;
import com.learn.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * date: 2021/3/11 14:20
 * Package: com.learn.eduservice.controller.front
 *
 * @author 李佳乐
 * @version 1.0
 */
@Api(description = "前台课程列表模块")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/coursefront")
public class CourseFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;

    @Autowired
    private OrdersClient ordersClient;

    @PostMapping("getCourseFrontList/{current}/{size}")
    public R getCourseFrontList(@PathVariable long current,
                                @PathVariable long size,
                                @RequestBody(required = false) CourseFrontVo courseFrontVo) {
        Page<EduCourse> page = new Page<>(current, size);
        Map<String, Object> map = courseService.getCourseFrontList(page, courseFrontVo);
        return R.ok().data(map);
    }

    @GetMapping("getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request) {
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);
        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoByCourseId(courseId);
        String memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(request);
        boolean buyCourse = ordersClient.isBuyCourse(courseId, memberIdByJwtToken);
        return R.ok().data("courseWebVo", courseWebVo)
                .data("chapterVideoList", chapterVideoList)
                .data("isBuy", buyCourse);
    }

    @PostMapping("getCourseInfoOrder/{id}")
    public CourseWebOrderVo getCourseInfoOrder(@PathVariable String id) {
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(id);
        CourseWebOrderVo courseWebOrderVo = new CourseWebOrderVo();
        BeanUtils.copyProperties(courseWebVo, courseWebOrderVo);
        return courseWebOrderVo;
    }

}
