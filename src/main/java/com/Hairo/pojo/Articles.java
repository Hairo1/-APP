package com.Hairo.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/4/12 17:49
 * 作用描述:
 *  文章pojo
 */

public class Articles implements Serializable {
    private static final long serialVersionUID = -5263249449152489L;
    private Integer a_id;//文章ID
    private String a_title;//文章标题
    private Date a_pubDate;//发布时间
    private String a_content;//文章内容
    private String u_name;//用户昵称
    private Integer a_browseCount;//浏览次数
    private Integer a_commentCount;//评论条数
    private Integer a_likeCount;//点赞个数
    private String l_name;//标签云昵称

    @Override
    public String toString() {
        return "Articles{" +
                "a_id=" + a_id +
                ", a_title='" + a_title + '\'' +
                ", a_pubDate=" + a_pubDate +
                ", a_content='" + a_content + '\'' +
                ", u_name='" + u_name + '\'' +
                ", a_browseCount=" + a_browseCount +
                ", a_commentCount=" + a_commentCount +
                ", a_likeCount=" + a_likeCount +
                ", l_name='" + l_name + '\'' +
                '}';
    }

    public void setA_id(Integer a_id) {
        this.a_id = a_id;
    }

    public void setA_title(String a_title) {
        this.a_title = a_title;
    }

    public void setA_pubDate(Date a_pubDate) {
        this.a_pubDate = a_pubDate;
    }

    public void setA_content(String a_content) {
        this.a_content = a_content;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public void setA_browseCount(Integer a_browseCount) {
        this.a_browseCount = a_browseCount;
    }

    public void setA_commentCount(Integer a_commentCount) {
        this.a_commentCount = a_commentCount;
    }

    public void setA_likeCount(Integer a_likeCount) {
        this.a_likeCount = a_likeCount;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public Integer getA_id() {
        return a_id;
    }

    public String getA_title() {
        return a_title;
    }

    public Date getA_pubDate() {
        return a_pubDate;
    }

    public String getA_content() {
        return a_content;
    }

    public String getU_name() {
        return u_name;
    }

    public Integer getA_browseCount() {
        return a_browseCount;
    }

    public Integer getA_commentCount() {
        return a_commentCount;
    }

    public Integer getA_likeCount() {
        return a_likeCount;
    }

    public String getL_name() {
        return l_name;
    }
}
