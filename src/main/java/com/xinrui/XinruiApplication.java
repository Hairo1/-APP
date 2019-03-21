package com.xinrui;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value="com.xinrui.mappers")//扫描mapper包
@EnableAutoConfiguration//自动载入应用程序所需的所有Bean，当使用Exclude这个属性时，是禁止自动配置某个类
public class XinruiApplication {
    public static void main(String[] args) {
        SpringApplication.run(XinruiApplication.class, args);
    }

}
