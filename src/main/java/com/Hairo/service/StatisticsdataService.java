package com.Hairo.service;

import com.Hairo.pojo.Statisticsdata;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)//统一事务
public interface StatisticsdataService {


    public Statisticsdata getStatisticsdata();
}
