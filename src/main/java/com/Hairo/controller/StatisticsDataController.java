package com.Hairo.controller;

import com.Hairo.service.StatisticsdataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/4/26 14:58
 * 作用描述:
 */
@RestController
@RequestMapping("/api/statisticsData/")
public class StatisticsDataController {

    @Autowired
    private StatisticsdataService statisticsdataService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public Object getStatisticsData(){

        return statisticsdataService.getStatisticsdata();
    }
}
