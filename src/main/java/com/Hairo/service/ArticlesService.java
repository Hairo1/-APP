package com.Hairo.service;


import com.Hairo.pojo.Articles;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 文章服务层接口
 */
@Transactional(rollbackFor = Exception.class)//统一事务
public interface ArticlesService {
    /**
     * 获取所有文章
     * 分页
     * @param page 当前页
     * @param state 文章状态(0-审核-1通过)
     * @return
     */

    public Object selectAllArticles(Integer page, Integer state);

    /**
     * 根据ID获取文章
     * @param articlesId
     * @return
     */
    public Object selectArticlesById(@Param("articlesId") Integer articlesId);

    /**
     * 获取指定作者下的所有文章
     * @return
     */
    public Object selectAllArticlesByAuthor(String author,Integer pageNum);

    /**
     * 获取指定标签下的所有文章
     * @param label
     * @param pageNum
     * @return
     */
    public Object selectAllArticlesByLabel( String label, Integer pageNum);
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

    public Integer selectArticleCountByLabel(String label);

    public Object getHotArticle();

    public Object getRandomArticle();

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

    /**
     * 更新文章点赞数量
     * @param articleId
     * @return
     */
    public Integer updateArticlePraise(Integer articleId,String ip);

    public Integer addArticleBrowse(Integer articleId);

    /**
     * 批量更新文章浏览量
     * @param map
     * @return
     */
    public Integer updateArticleBrowse(Map<Object,Object> map);
}
