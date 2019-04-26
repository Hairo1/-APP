package com.Hairo.pojo;

import java.io.Serializable;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/4/12 17:54
 * 作用描述:
 *  友情链接pojo
 */

public class Blogroll implements Serializable {
    private static final long serialVersionUID = -52632944915280489L;
    private Integer b_id;//友情链接ID
    private String b_name;//友情链接名称
    private String b_url;//友情链接
    private String b_email;//友情链接邮箱

    @Override
    public String toString() {
        return "Blogroll{" +
                "b_id=" + b_id +
                ", b_name='" + b_name + '\'' +
                ", b_url='" + b_url + '\'' +
                ", b_email='" + b_email + '\'' +
                '}';
    }

    public void setB_id(Integer b_id) {
        this.b_id = b_id;
    }

    public void setB_name(String b_name) {
        this.b_name = b_name;
    }

    public void setB_url(String b_url) {
        this.b_url = b_url;
    }

    public void setB_email(String b_email) {
        this.b_email = b_email;
    }

    public Integer getB_id() {
        return b_id;
    }

    public String getB_name() {
        return b_name;
    }

    public String getB_url() {
        return b_url;
    }

    public String getB_email() {
        return b_email;
    }
}
