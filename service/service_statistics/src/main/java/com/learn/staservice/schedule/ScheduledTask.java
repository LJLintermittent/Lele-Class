package com.learn.staservice.schedule;

import com.learn.staservice.service.StatisticsDailyService;
import com.learn.staservice.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Description:
 * date: 2021/3/13 18:06
 * Package: com.learn.staservice.schedule
 *
 * @author 李佳乐
 * @version 1.0
 */
@Component
public class ScheduledTask {

    @Autowired
    private StatisticsDailyService statisticsDailyService;

    //每天凌晨一点执行
    @Scheduled(cron = "0 0 1 * * ?")
    public void task1() {
        statisticsDailyService.registerCount(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }


}
