package com.Hairo.mappers.articleReviewMapper;

import com.Hairo.pojo.ArticleReview;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleReviewMapper {


    public List<ArticleReview> selectArticleReview(@Param("page") Integer page,@Param("pageSize") Integer pageSize,@Param("articleId") Integer articleId);

    public ArticleReview selectArticleReviewUser(@Param("userName") String userName);

    public Integer delectArticleReview(@Param("reviewId") Integer reviewId);

    public Integer insertArticleReview(ArticleReview articleReview);
}
