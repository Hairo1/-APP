package com.Hairo.service;

import com.Hairo.pojo.Articles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(rollbackFor = Exception.class)//统一事务
public interface PublicService {

    /**
     * 获取热门文章(热门文章->浏览次数最高)
     * @return
     */
    public Object getHotArticle();

    /**
     *
     * 获取推荐文章(站长推荐)
     * @return
     */
    public Object getSuggestedArticle();


    /**
     * 设置热门文章
     * @return
     */
    public void setHotArticle();

    /**
     *
     * 设置推荐文章列表
     * @return
     */
    public void setSuggestedArticle();

    /**
     * 文章浏览数+1
     * @param
     * @param articleId 文章ID
     * @return
     */
    public void articleBrowsePlus1(Integer articleId);

    /**
     * 文章点赞+
     * @param articleId 文章ID
     * @return
     */
    public void articleLikePlus1(Integer articleId);

    /**
     * 从redis获取文章浏览次数(每两个小时获取一次(定时器).然后同步到数据库)
     * @return
     */
    public List<Integer> articleBrowse();

    /**
     * 从redis获取文章点赞列表(每两个小时获取一次(定时器).然后同步到数据库)
     * @return
     */
    public List<Integer> articleLike();

    /**
     * 获取随机文章
     * @return
     */
    public Object getRandomArticle();

    /**
     * 设置随机文章
     * @return
     */
    public void setRandomArticle();
}
