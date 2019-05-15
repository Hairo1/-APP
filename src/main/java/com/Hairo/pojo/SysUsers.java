package com.Hairo.pojo;

import java.io.Serializable;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/3/21 20:09
 * 作用描述:
 */

public class SysUsers implements Serializable {
    private static final long serialVersionUID = -526324944280489L;
    private Integer u_id;
    private String u_name;
    private String u_password;
    private String u_email;
    private String u_contact;
    private Integer u_state;


    @Override
    public String toString() {
        return "SysUsers{" +
                "u_id=" + u_id +
                ", u_name='" + u_name + '\'' +
                ", u_password='" + u_password + '\'' +
                ", u_email='" + u_email + '\'' +
                ", u_contact='" + u_contact + '\'' +
                ", u_state=" + u_state +
                '}';
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public void setU_password(String u_password) {
        this.u_password = u_password;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    public void setU_contact(String u_contact) {
        this.u_contact = u_contact;
    }

    public void setU_state(Integer u_state) {
        this.u_state = u_state;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getU_id() {
        return u_id;
    }

    public String getU_name() {
        return u_name;
    }

    public String getU_password() {
        return u_password;
    }

    public String getU_email() {
        return u_email;
    }

    public String getU_contact() {
        return u_contact;
    }

    public Integer getU_state() {
        return u_state;
    }
}
