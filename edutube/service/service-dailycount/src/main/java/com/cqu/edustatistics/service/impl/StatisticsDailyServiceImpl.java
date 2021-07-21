package com.cqu.edustatistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cqu.edustatistics.entity.StatisticsDaily;
import com.cqu.edustatistics.mapper.StatisticsDailyMapper;
import com.cqu.edustatistics.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqu.edustatistics.utils.UcenterClient;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author CGT
 * @since 2021-07-15
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public void createStatisticsByDay(String day) {
        //删除已存在的统计对象
        QueryWrapper<StatisticsDaily> dayQueryWrapper = new QueryWrapper<>();
        dayQueryWrapper.eq("date_calculated", day);
        baseMapper.delete(dayQueryWrapper);
        //获取统计信息
        Integer registerNum = (Integer) ucenterClient.registerCount(day).getData().get("countRegister");
        Integer loginNum = (Integer) ucenterClient.loginCount(day).getData().get("countLogin");
        Integer videoViewNum = (Integer) ucenterClient.videoCount(day).getData().get("countVideo");
        Integer courseNum = (Integer) ucenterClient.courseCount(day).getData().get("countCourse");
        //创建统计对象
        StatisticsDaily daily = new StatisticsDaily();
        daily.setRegisterNum(registerNum);
        daily.setLoginNum(loginNum);
        daily.setVideoViewNum(videoViewNum);
        daily.setCourseNum(courseNum);
        daily.setDateCalculated(day);
        baseMapper.insert(daily);
    }

    @Override
    public Map<String, Object> getShowData(String type, String begin, String end) {

        return null;
    }

    @Override
    public void updateLoginNum(String day) {
        QueryWrapper<StatisticsDaily>wrapper=new QueryWrapper<>();
        wrapper.eq("date_calculated",day);
        StatisticsDaily daily=baseMapper.selectOne(wrapper);
        if(daily==null)
        {
            daily=new StatisticsDaily();
            daily.setDateCalculated(day);
            daily.setLoginNum(0);
            Integer loginNum=daily.getLoginNum();
            loginNum++;
            daily.setLoginNum(loginNum);
            baseMapper.insert(daily);
        }else{
            Integer loginNum=daily.getLoginNum();
            loginNum++;
            daily.setLoginNum(loginNum);
            baseMapper.updateById(daily);
        }
    }

    @Override
    public void updateRegisterNum(String day) {
        QueryWrapper<StatisticsDaily>wrapper=new QueryWrapper<>();
        wrapper.eq("date_calculated",day);
        StatisticsDaily daily=baseMapper.selectOne(wrapper);
        if(daily==null)
        {
            daily=new StatisticsDaily();
            daily.setDateCalculated(day);
            daily.setRegisterNum(0);
            int registerNum=daily.getRegisterNum();
            registerNum++;
            daily.setRegisterNum(registerNum);
            baseMapper.insert(daily);
        }
        else{
            int registerNum=daily.getRegisterNum();
            registerNum++;
            daily.setRegisterNum(registerNum);
            baseMapper.updateById(daily);
        }

    }

    @Override
    public void updateVideo(String day) {
        QueryWrapper<StatisticsDaily>wrapper=new QueryWrapper<>();
        wrapper.eq("date_calculated",day);
        StatisticsDaily daily=baseMapper.selectOne(wrapper);
        if(daily==null)
        {
            daily=new StatisticsDaily();
            daily.setDateCalculated(day);
            daily.setVideoViewNum(0);
            Integer videoViewNum=daily.getVideoViewNum();
            videoViewNum++;
            daily.setVideoViewNum(videoViewNum);
            baseMapper.insert(daily);
        }else{
            Integer viewNum=daily.getVideoViewNum();
            viewNum++;
            daily.setVideoViewNum(viewNum);
            baseMapper.updateById(daily);
        }
    }

    @Override
    public void updateCourse(String day) {
        QueryWrapper<StatisticsDaily>wrapper=new QueryWrapper<>();
        wrapper.eq("date_calculated",day);
        StatisticsDaily daily=baseMapper.selectOne(wrapper);
        if(daily==null)
        {
            daily=new StatisticsDaily();
            daily.setDateCalculated(day);
            daily.setCourseNum(0);
            Integer courseNum=daily.getCourseNum();
            courseNum++;
            daily.setCourseNum(courseNum);
            baseMapper.insert(daily);
        }else{
            Integer courseNum=daily.getCourseNum();
            courseNum++;
            daily.setCourseNum(courseNum);
            baseMapper.updateById(daily);
        }
    }

    @Override
    public Map<String, Object> getChartData(String begin, String end, String type) {

        return null;
    }

    @Override
    public Map<String, List<Integer>> getFifteen(String begin,String end) {
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.between("date_calculated",begin,end);
        wrapper.orderByAsc("date_calculated");
        List<StatisticsDaily> staList = baseMapper.selectList(wrapper);


        LinkedHashMap<String,List<Integer>> map=new LinkedHashMap<>();

        for (StatisticsDaily day:staList){
            ArrayList<Integer> list = new ArrayList<>();
            list.add(day.getRegisterNum());
            list.add(day.getLoginNum());
            list.add(day.getVideoViewNum());
            list.add(day.getCourseNum());

            map.put(day.getDateCalculated(),list);
        }
        return map;
    }
}
