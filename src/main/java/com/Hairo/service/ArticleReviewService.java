package com.Hairo.service;

import com.Hairo.pojo.ArticleReview;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class)
public interface ArticleReviewService {


    public List<ArticleReview> getArticleReview(Integer page,Integer articleId);

    public ArticleReview  getArticleReviewUser( String userName);

    public Integer delArticleReview( Integer reviewId);

    public Integer addArticleReview(ArticleReview articleReview);

}
