package com.Hairo.service;


import com.Hairo.pojo.Articles;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 文章服务层接口
 */
@Transactional(rollbackFor = Exception.class)//统一事务
public interface ArticlesService {
    /**
     * 获取所有文章
     * 分页
     * @param pageNum 当前页
     * @param pageSize 没页显示条数
     * @return
     */


    public List<Articles> selectAllArticles(Integer pageNum, Integer pageSize);

    /**
     * 根据ID获取文章
     * @param articlesId
     * @return
     */
    public Articles selectArticlesById(@Param("articlesId") Integer articlesId);

    /**
     * 获取指定作者下的所有文章
     * @return
     */
    public List<Articles> selectAllArticlesByAuthor(String author,Integer pageNum,Integer pageSize);

    /**
     * 获取指定标签下的所有文章
     * @param label
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<Articles> selectAllArticlesByLabel( String label, Integer pageNum, Integer pageSize);
    /**
     * 获取文章总数量-分页
     * @return
     */
    public Integer selectArticleCount();

    /**
     * 获取指定作者下的文章数量
     * @return
     */
    public Integer selectArticleCountByAuthor(String author);

    /**
     * 添加文章
     * @param article
     * @return
     */
    public Integer insertArticle(Articles article);

    /**
     * 根据ID删除文章
     * @param articleId
     * @return
     */
    public Integer delectActicle(@Param("articleId") Integer articleId);

    /**
     * 修改文章
     * @param article
     * @return
     */
    public Integer updateActicle(Articles article);

}
