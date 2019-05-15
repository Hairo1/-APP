package com.Hairo.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/4/26 16:17
 * 作用描述:
 * 留言板块实体
 */

public class CommentInfo implements Serializable {
    private Integer m_id;
    private String   m_userName;
    private String m_email;
    private String   m_url;
    private String   m_content;
    private Date  m_date;
    private  String  m_portraitUrl;
    private  Integer m_articleId;

    @Override
    public String toString() {
        return "CommentInfo{" +
                "m_id=" + m_id +
                ", m_userName='" + m_userName + '\'' +
                ", m_email='" + m_email + '\'' +
                ", m_url='" + m_url + '\'' +
                ", m_content='" + m_content + '\'' +
                ", m_date=" + m_date +
                ", m_portraitUrl='" + m_portraitUrl + '\'' +
                ", m_articleId=" + m_articleId +
                '}';
    }

    public void setM_id(Integer m_id) {
        this.m_id = m_id;
    }

    public void setM_userName(String m_userName) {
        this.m_userName = m_userName;
    }

    public void setM_email(String m_email) {
        this.m_email = m_email;
    }

    public void setM_url(String m_url) {
        this.m_url = m_url;
    }

    public void setM_content(String m_content) {
        this.m_content = m_content;
    }

    public void setM_date(Date m_date) {
        this.m_date = m_date;
    }

    public void setM_portraitUrl(String m_portraitUrl) {
        this.m_portraitUrl = m_portraitUrl;
    }

    public void setM_articleId(Integer m_articleId) {
        this.m_articleId = m_articleId;
    }

    public Integer getM_id() {
        return m_id;
    }

    public String getM_userName() {
        return m_userName;
    }

    public String getM_email() {
        return m_email;
    }

    public String getM_url() {
        return m_url;
    }

    public String getM_content() {
        return m_content;
    }

    public Date getM_date() {
        return m_date;
    }

    public String getM_portraitUrl() {
        return m_portraitUrl;
    }

    public Integer getM_articleId() {
        return m_articleId;
    }
}
