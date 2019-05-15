package com.Hairo.pojo;

import java.util.Date;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/5/13 23:29
 * 作用描述:
 */

public class ArticleReview {
    private Integer reviewId;
    private Integer articleId;
    private String reviewContent;
    private Date reviewDate;
    private String randomImgUrl;
    private String reviewUserName;
    private String reviewUserBlogroll;
    private String reviewUserEmail;
    private Integer reviewState;

    @Override
    public String toString() {
        return "ArticleReview{" +
                "reviewId=" + reviewId +
                ", articleId=" + articleId +
                ", reviewContent='" + reviewContent + '\'' +
                ", reviewDate=" + reviewDate +
                ", randomImgUrl='" + randomImgUrl + '\'' +
                ", reviewUserName='" + reviewUserName + '\'' +
                ", reviewUserBlogroll='" + reviewUserBlogroll + '\'' +
                ", reviewUserEmail='" + reviewUserEmail + '\'' +
                ", reviewState=" + reviewState +
                '}';
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public void setRandomImgUrl(String randomImgUrl) {
        this.randomImgUrl = randomImgUrl;
    }

    public void setReviewUserName(String reviewUserName) {
        this.reviewUserName = reviewUserName;
    }

    public void setReviewUserBlogroll(String reviewUserBlogroll) {
        this.reviewUserBlogroll = reviewUserBlogroll;
    }

    public void setReviewUserEmail(String reviewUserEmail) {
        this.reviewUserEmail = reviewUserEmail;
    }

    public void setReviewState(Integer reviewState) {
        this.reviewState = reviewState;
    }

    public Integer getReviewId() {
        return reviewId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public String getRandomImgUrl() {
        return randomImgUrl;
    }

    public String getReviewUserName() {
        return reviewUserName;
    }

    public String getReviewUserBlogroll() {
        return reviewUserBlogroll;
    }

    public String getReviewUserEmail() {
        return reviewUserEmail;
    }

    public Integer getReviewState() {
        return reviewState;
    }
}
