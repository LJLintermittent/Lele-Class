package com.learn.staservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.learn.commonutils.R;
import com.learn.staservice.client.UcenterClient;
import com.learn.staservice.entity.StatisticsDaily;
import com.learn.staservice.mapper.StatisticsDailyMapper;
import com.learn.staservice.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-03-13
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public void registerCount(String day) {
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated", day);
        baseMapper.delete(wrapper);
        R registerR = ucenterClient.countRegister(day);
        Integer countRegister = (Integer) registerR.getData().get("countRegister");
        StatisticsDaily sta = new StatisticsDaily();
        sta.setRegisterNum(countRegister);
        sta.setDateCalculated(day);
        sta.setVideoViewNum(RandomUtils.nextInt(100, 200));
        sta.setLoginNum(RandomUtils.nextInt(100, 200));
        sta.setCourseNum(RandomUtils.nextInt(100, 200));
        baseMapper.insert(sta);
    }

    @Override
    public Map<String, Object> getShowData(String type, String begin, String end) {
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.between("date_calculated", begin, end);
        wrapper.orderByAsc("date_calculated");
        wrapper.select("date_calculated", type);
        List<StatisticsDaily> list = baseMapper.selectList(wrapper);
        ArrayList<String> date_calculatedList = new ArrayList<>();
        ArrayList<Integer> numDataList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            StatisticsDaily statisticsDaily = list.get(i);
            date_calculatedList.add(statisticsDaily.getDateCalculated());
            switch (type) {
                case "login_num":
                    numDataList.add(statisticsDaily.getLoginNum());
                    break;
                case "register_num":
                    numDataList.add(statisticsDaily.getRegisterNum());
                    break;
                case "video_view_num":
                    numDataList.add(statisticsDaily.getVideoViewNum());
                    break;
                case "course_num":
                    numDataList.add(statisticsDaily.getCourseNum());
                    break;
                default:
                    break;
            }
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("date_calculatedList", date_calculatedList);
        map.put("numDataList", numDataList);
        return map;
    }
}
