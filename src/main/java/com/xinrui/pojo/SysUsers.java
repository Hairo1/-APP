package com.xinrui.pojo;

import java.io.Serializable;

/**
 * 项目名称： xinrui
 * 作 者   ： Hairo
 * 创建时间: 2019/3/21 20:09
 * 作用描述:
 */

public class SysUsers implements Serializable {

    private Integer user_id;
    private String user_name;
    private String user_pwd;

    public Integer getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_pwd() {
        return user_pwd;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_pwd(String user_pwd) {
        this.user_pwd = user_pwd;
    }

    @Override
    public String toString() {
        return "SysUsers{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_pwd='" + user_pwd + '\'' +
                '}';
    }
}
