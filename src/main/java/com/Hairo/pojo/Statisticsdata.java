package com.Hairo.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/4/26 14:50
 * 作用描述:
 * 统计数据实体
 */

public class Statisticsdata implements Serializable {

    private Integer s_articleSum;
    private Integer s_browseSum;
    private Integer s_labelSum;
    private Integer s_userSum;
    private Date s_runDaysSum;

    @Override
    public String toString() {
        return "Statisticsdata{" +
                "s_articleSum=" + s_articleSum +
                ", s_browseSum=" + s_browseSum +
                ", s_labelSum=" + s_labelSum +
                ", s_userSum=" + s_userSum +
                ", s_runDaysSum=" + s_runDaysSum +
                '}';
    }

    public void setS_articleSum(Integer s_articleSum) {
        this.s_articleSum = s_articleSum;
    }

    public void setS_browseSum(Integer s_browseSum) {
        this.s_browseSum = s_browseSum;
    }

    public void setS_labelSum(Integer s_labelSum) {
        this.s_labelSum = s_labelSum;
    }

    public void setS_userSum(Integer s_userSum) {
        this.s_userSum = s_userSum;
    }

    public void setS_runDaysSum(Date s_runDaysSum) {
        this.s_runDaysSum = s_runDaysSum;
    }

    public Integer getS_articleSum() {
        return s_articleSum;
    }

    public Integer getS_browseSum() {
        return s_browseSum;
    }

    public Integer getS_labelSum() {
        return s_labelSum;
    }

    public Integer getS_userSum() {
        return s_userSum;
    }

    public Date getS_runDaysSum() {
        return s_runDaysSum;
    }
}
