package com.learn.staservice.controller;


import com.learn.commonutils.R;
import com.learn.staservice.service.StatisticsDailyService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-03-13
 */
@Api(description = "统计分析模块")
@RestController
@RequestMapping("/staservice/sta")
@CrossOrigin
public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService statisticsDailyService;

    @PostMapping("registerCount/{day}")
    public R registerCount(@PathVariable String day) {
        statisticsDailyService.registerCount(day);
        return R.ok();
    }

    @GetMapping("showData/{type}/{begin}/{end}")
    public R showData(@PathVariable String type,
                      @PathVariable String begin,
                      @PathVariable String end) {
        Map<String, Object> map = statisticsDailyService.getShowData(type, begin, end);
        return R.ok().data(map);

    }

}

