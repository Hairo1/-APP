package com.Hairo.pojo;

import java.util.Date;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/5/13 23:49
 * 作用描述:
 */

public class MessageBoard {

   private Integer  message_id;
    private String message_content;
    private Date message_date;
    private String message_userImgUrl;
    private String message_userUrl;
    private String message_userName;
    private Integer message_state;

    @Override
    public String toString() {
        return "MessageBoardMapper{" +
                "message_id=" + message_id +
                ", message_content='" + message_content + '\'' +
                ", message_date=" + message_date +
                ", message_userImgUrl='" + message_userImgUrl + '\'' +
                ", message_userUrl='" + message_userUrl + '\'' +
                ", message_userName='" + message_userName + '\'' +
                ", message_state=" + message_state +
                '}';
    }

    public void setMessage_id(Integer message_id) {
        this.message_id = message_id;
    }

    public void setMessage_content(String message_content) {
        this.message_content = message_content;
    }

    public void setMessage_date(Date message_date) {
        this.message_date = message_date;
    }

    public void setMessage_userImgUrl(String message_userImgUrl) {
        this.message_userImgUrl = message_userImgUrl;
    }

    public void setMessage_userUrl(String message_userUrl) {
        this.message_userUrl = message_userUrl;
    }

    public void setMessage_userName(String message_userName) {
        this.message_userName = message_userName;
    }

    public void setMessage_state(Integer message_state) {
        this.message_state = message_state;
    }

    public Integer getMessage_id() {
        return message_id;
    }

    public String getMessage_content() {
        return message_content;
    }

    public Date getMessage_date() {
        return message_date;
    }

    public String getMessage_userImgUrl() {
        return message_userImgUrl;
    }

    public String getMessage_userUrl() {
        return message_userUrl;
    }

    public String getMessage_userName() {
        return message_userName;
    }

    public Integer getMessage_state() {
        return message_state;
    }
}
