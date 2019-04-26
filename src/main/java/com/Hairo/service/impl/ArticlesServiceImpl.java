package com.Hairo.service.impl;

import com.Hairo.mappers.articlesMapper.ArticlesMapper;
import com.Hairo.pojo.Articles;
import com.Hairo.service.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/4/12 18:19
 * 作用描述:
 */
@Service
public class ArticlesServiceImpl implements ArticlesService {

    @Autowired
    private ArticlesMapper articlesMapper;
    @Override
    public List<Articles> selectAllArticles(Integer pageNum, Integer pageSize) {
        if(pageNum <= 0 || pageNum == null){return null;}
        return articlesMapper.selectAllArticles(( pageNum -1 ) * 15,pageSize);
    }

    @Override
    public Articles selectArticlesById(Integer articlesId) {
        if (articlesId == 0 || articlesMapper == null){return null;};
        return articlesMapper.selectArticlesById(articlesId);
    }

    @Override
    public List<Articles> selectAllArticlesByAuthor(String author,Integer pageNum,Integer pageSize) {
        if(author==null || "".equals(author) || author.length() == 0){return null;}
        if(pageNum==null ||  pageNum <= 0){return null;}
        return articlesMapper.selectAllArticlesByAuthor(author,( pageNum -1 ) * 15,pageSize);
    }

    @Override
    public List<Articles> selectAllArticlesByLabel(String label, Integer pageNum, Integer pageSize) {
        if(label==null || "".equals(label) || 0 ==  label.length()){return null;}
        if(pageNum==null ||  pageNum <= 0){return null;}
        return articlesMapper.selectAllArticlesByLabel(label,( pageNum -1 ) * 15,pageSize);
    }

    @Override
    public Integer selectArticleCount() {
        return articlesMapper.selectArticleCount();
    }

    @Override
    public Integer selectArticleCountByAuthor(String author) {
        return articlesMapper.selectArticleCountByAuthor(author);
    }

    @Override
    public Integer insertArticle(Articles article) {
        return articlesMapper.insertArticle(article);
    }

    @Override
    public Integer delectActicle(Integer articleId) {
        if(articleId==null || articleId<= 0){return -1;}
        return articlesMapper.delectActicle(articleId);
    }

    @Override
    public Integer updateActicle(Articles article) {
        if(article==null){return -1;}
        return articlesMapper.updateActicle(article);
    }
}
