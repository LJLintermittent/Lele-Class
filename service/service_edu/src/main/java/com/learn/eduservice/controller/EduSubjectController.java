package com.learn.eduservice.controller;


import com.learn.commonutils.R;
import com.learn.eduservice.entity.subject.OneSubject;
import com.learn.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-03
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
@Api(description = "课程分类管理")
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping("addSubject")
    public R addSubject(MultipartFile file) {
        eduSubjectService.saveSubject(file, eduSubjectService);
        return R.ok();
    }

    @GetMapping("getAllSubject")
    public R getAllSubject() {
        List<OneSubject> list = eduSubjectService.getAllOneTwoSubject();
        return R.ok().data("list", list);
    }
}

