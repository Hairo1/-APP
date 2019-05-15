package com.Hairo.service.impl;

import com.Hairo.mappers.articleReviewMapper.ArticleReviewMapper;
import com.Hairo.pojo.ArticleReview;
import com.Hairo.service.ArticleReviewService;
import com.Hairo.util.HairoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/5/14 1:24
 * 作用描述:
 */

@Service
public class ArticleReviewServiceImpl implements ArticleReviewService {
    @Autowired
    private ArticleReviewMapper articleReviewMapper;


    @Override
    public List<ArticleReview> getArticleReview(Integer page,Integer articleId) {
        if(page == null || page < 0 || articleId == null || articleId <=9999){
            return null;
        }
        //return articleReviewMapper.selectAllArcicleReview((page-1)* HairoUtil.PAGESIZE,HairoUtil.PAGESIZE);
        return articleReviewMapper.selectArticleReview(0,30,articleId);
    }

    @Override
    public ArticleReview getArticleReviewUser(String userName) {
        if(userName == null || "".equals(userName)){
            return null;
        }
        return articleReviewMapper.selectArticleReviewUser(userName);
    }

    @Override
    public Integer delArticleReview(Integer reviewId) {
        if(reviewId == null || reviewId <=0){
            return 0;
        }
        return articleReviewMapper.delectArticleReview(reviewId);
    }

    @Override
    public Integer addArticleReview(ArticleReview articleReview) {
        if(articleReview == null || articleReview.getReviewContent()==null || articleReview.getReviewUserName() ==null){
            return -1;
        }
        return articleReviewMapper.insertArticleReview(articleReview);
    }
}
