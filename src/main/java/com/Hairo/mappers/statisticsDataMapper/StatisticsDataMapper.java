package com.Hairo.mappers.statisticsDataMapper;

import com.Hairo.pojo.Statisticsdata;
import org.springframework.stereotype.Service;

public interface StatisticsDataMapper {


    /**
     * 查询统计数据
     * @return
     */

    public Statisticsdata selectStatisticsdata();
}
