package com.Hairo.service.impl;

import com.Hairo.mappers.statisticsDataMapper.StatisticsDataMapper;
import com.Hairo.pojo.Statisticsdata;
import com.Hairo.service.StatisticsdataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/4/26 14:57
 * 作用描述:
 */
@Service
public class StatisticsdataServiceImpl implements StatisticsdataService {
    @Autowired
    private StatisticsDataMapper statisticsDataMapper;

    @Override
    public Statisticsdata getStatisticsdata() {
        return statisticsDataMapper.selectStatisticsdata();
    }
}
