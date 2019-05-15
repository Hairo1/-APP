package com.Hairo.pojo;

import java.io.Serializable;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/4/12 18:11
 * 作用描述:
 * 标签云po
 */

public class Label implements Serializable {
    private static final long serialVersionUID = -52632415280489L;
    private String l_name;//标签云昵称
    private Integer l_count;//文章个数
    private Integer  l_start;//标签状态 0禁用 1启用

    @Override
    public String toString() {
        return "Label{" +
                "l_name='" + l_name + '\'' +
                ", l_count=" + l_count +
                ", l_start=" + l_start +
                '}';
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public void setL_count(Integer l_count) {
        this.l_count = l_count;
    }

    public void setL_start(Integer l_start) {
        this.l_start = l_start;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getL_name() {
        return l_name;
    }

    public Integer getL_count() {
        return l_count;
    }

    public Integer getL_start() {
        return l_start;
    }
}
