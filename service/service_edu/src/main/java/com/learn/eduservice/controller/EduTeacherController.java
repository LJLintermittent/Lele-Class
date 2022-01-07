package com.learn.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.learn.commonutils.R;
import com.learn.eduservice.entity.EduTeacher;
import com.learn.eduservice.entity.vo.TeacherQuery;
import com.learn.eduservice.service.EduTeacherService;
import com.learn.servicebase.exceptionhandler.GuLiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-02-26
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @GetMapping("findAll")
    @ApiOperation(value = "查询所有讲师列表")
    public R findAllTeacher() {
        List<EduTeacher> teachers = eduTeacherService.list(null);
//        try {
//            int i = 10 / 0;
//        } catch (Exception e) {
//            throw new GuLiException(20001, "执行了自定义异常处理");
//        }
        return R.ok().data("items", teachers);
    }

    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "根据id逻辑删除")
    public R removeTeacher(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {
        boolean flag = eduTeacherService.removeById(id);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @GetMapping("pageTeacher/{current}/{size}")
    @ApiOperation(value = "分页查询讲师列表")
    public R pageListTeacher(
            @ApiParam(name = "current", value = "当前页", required = true)
            @PathVariable long current,
            @ApiParam(name = "size", value = "当前页显示数据的条数", required = true)
            @PathVariable long size) {
        Page<EduTeacher> page = new Page<>(current, size);
        eduTeacherService.page(page, null);
        long total = page.getTotal();
        List<EduTeacher> records = page.getRecords();
        HashMap map = new HashMap();
        map.put("total", total);
        map.put("rows", records);
        return R.ok().data(map);
//        return R.ok().data("total",total).data("rows",records);
    }

    @PostMapping("pageTeacherCondition/{current}/{size}")
    @ApiOperation(value = "条件查询带分页")
    public R pageTeacherCondition(
            @ApiParam(name = "current", value = "当前页", required = true)
            @PathVariable long current,
            @ApiParam(name = "size", value = "当前页显示数据的条数", required = true)
            @PathVariable long size,
            @RequestBody(required = false) TeacherQuery teacherQuery) {
        Page<EduTeacher> teacherPage = new Page<>(current, size);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //判断条件是否为空，如果不是空那就拼接条件
        if (!StringUtils.isEmpty(name)) {
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_create", end);
        }
        wrapper.orderByDesc("gmt_create");
        eduTeacherService.page(teacherPage, wrapper);
        long total = teacherPage.getTotal();
        List<EduTeacher> records = teacherPage.getRecords();
        return R.ok().data("total", total).data("rows", records);

    }

    @PostMapping("addTeacher")
    @ApiOperation(value = "添加讲师")
    public R addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean flag = eduTeacherService.save(eduTeacher);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @GetMapping("getTeacher/{id}")
    @ApiOperation(value = "根据ID查询讲师")
    public R getTeacher(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {
        EduTeacher teacher = eduTeacherService.getById(id);
        return R.ok().data("teacher", teacher);
    }

    @PostMapping("updateTeacher")
    @ApiOperation(value = "根据ID更新讲师信息")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean flag = eduTeacherService.updateById(eduTeacher);
        if (flag) {
            return R.ok();
        } else {
            return R.error();
        }

    }
}

