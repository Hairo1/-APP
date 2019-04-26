package com.Hairo.mappers.articlesMapper;

import com.Hairo.pojo.Articles;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章接口
 */
@Mapper
public interface ArticlesMapper {

    /**
     * 获取所有文章
     * 分页
     * @param pageNum 当前页数
     * @param pageSize 每页显示条数
     * @return
     */
    public List<Articles> selectAllArticles(@Param("pageNum")Integer pageNum,@Param("pageSize") Integer pageSize);

    /**
     * 根据ID获取文章
     * @param articlesId
     * @return
     */
    public Articles selectArticlesById(@Param("articlesId") Integer articlesId);

    /**
     * 获取指定用户名下的所有文章
     * @return
     */
    public List<Articles> selectAllArticlesByAuthor(@Param("author") String author,@Param("pageNum") Integer pageNum,@Param("pageSize") Integer pageSize);

    /**
     * 根据云标签类型获取所有文章
     * @param label
     * @param pageNum
     * @param pageSize
     * @return
     */
    public List<Articles> selectAllArticlesByLabel(@Param("label") String label,@Param("pageNum") Integer pageNum,@Param("pageSize") Integer pageSize);

    /**L
     * 获取文章总数量-分页
     * @return
     */
    public Integer selectArticleCount();

    /**
     * 获取指定作者下的文章数量
     * @return
     */
    public Integer selectArticleCountByAuthor(@Param("author") String author);

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
     * 获取文章热榜前8条数据条件--》浏览数和回复数和点赞数最高的八条
     * @return
     */
    public List<Articles> selectArticleHot8();

    /**
     * 获取随机文章
     * @return
     */
    public List<Articles> selectRandomArticle();
}