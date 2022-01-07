package com.learn.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.learn.eduservice.entity.EduCourse;
import com.learn.eduservice.entity.EduCourseDescription;
import com.learn.eduservice.entity.frontvo.CourseFrontVo;
import com.learn.eduservice.entity.frontvo.CourseWebVo;
import com.learn.eduservice.entity.vo.CourseInfoVo;
import com.learn.eduservice.entity.vo.CoursePublishVo;
import com.learn.eduservice.mapper.EduCourseMapper;
import com.learn.eduservice.service.EduChapterService;
import com.learn.eduservice.service.EduCourseDescriptionService;
import com.learn.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.eduservice.service.EduVideoService;
import com.learn.servicebase.exceptionhandler.GuLiException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-03-04
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //向课程表添加课程基本信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert == 0) {
            throw new GuLiException(20001, "添加课程基本信息失败");
        }
        String cid = eduCourse.getId();
        //向课程简介表添加描述信息
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescription.setId(cid);
        eduCourseDescriptionService.save(eduCourseDescription);
        return cid;
    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse, courseInfoVo);

        EduCourseDescription courseDescription = eduCourseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());

        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if (update == 0) {
            throw new GuLiException(20001, "课程基本信息修改失败");
        }
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(courseInfoVo.getId());
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescriptionService.updateById(eduCourseDescription);

    }

    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(id);
        return publishCourseInfo;
    }

    @Override
    public void removeCourse(String courseId) {
        //根据课程id删除小节
        eduVideoService.removeVideoByCourseId(courseId);
        //根据课程id删除章节
        eduChapterService.removeChapterByCourseId(courseId);
        //根据课程id删除描述
        eduCourseDescriptionService.removeById(courseId);
        //根据课程id删除课程本身
        int result = baseMapper.deleteById(courseId);
        if (result == 0) {
            throw new GuLiException(20001, "删除失败");
        }
    }

    @Override
    public Map<String, Object> getCourseFrontList(Page<EduCourse> page, CourseFrontVo courseFrontVo) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        String subjectParentId = courseFrontVo.getSubjectParentId();
        String subjectId = courseFrontVo.getSubjectId();
        String buyCountSort = courseFrontVo.getBuyCountSort();
        String gmtCreateSort = courseFrontVo.getGmtCreateSort();
        String priceSort = courseFrontVo.getPriceSort();
        if (!StringUtils.isEmpty(subjectParentId)) {
            wrapper.eq("subject_parent_id", subjectParentId);
        }
        if (!StringUtils.isEmpty(subjectId)) {
            wrapper.eq("subject_id", subjectId);
        }
        if (!StringUtils.isEmpty(buyCountSort)) {
            wrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(gmtCreateSort)) {
            wrapper.orderByDesc("gmt_create");
        }
        if (!StringUtils.isEmpty(priceSort)) {
            wrapper.orderByDesc("price");
        }
        baseMapper.selectPage(page, wrapper);

        List<EduCourse> records = page.getRecords();
        long current = page.getCurrent();
        long pages = page.getPages();
        long size = page.getSize();
        long total = page.getTotal();
        boolean hasNext = page.hasNext();
        boolean hasPrevious = page.hasPrevious();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;
    }

    @Override
    public CourseWebVo getBaseCourseInfo(String courseId) {
        return baseMapper.getBaseCourseInfo(courseId);
    }
}
