package com.cqu.edustatistics.service;

import com.cqu.edustatistics.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author CGT
 * @since 2021-07-15
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void createStatisticsByDay(String day);

    Map<String, Object> getShowData(String type, String begin, String end);

    void updateLoginNum(String day);

    void updateRegisterNum(String day);

    void updateVideo(String day);

    void updateCourse(String day);

    Map<String, Object> getChartData(String begin, String end, String type);

    Map<String, List<Integer>> getFifteen(String begin,String end);
}
