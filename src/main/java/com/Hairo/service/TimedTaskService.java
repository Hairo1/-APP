package com.Hairo.service;

import com.Hairo.pojo.Articles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 定时任务服务
 */
@Transactional(rollbackFor = Exception.class)//统一事务
public interface TimedTaskService {


    /**
     * 从MYSQL中获取数据到redis缓存
     */
    public void setArticleTORedis();


    /**
     * 设置热门文章
     * @return
     */
    public Integer setHotArticle();


    /**
     * 设置随机文章
     * @return
     */
    public Integer setRandomArticle();

    /**
     * 获取文章浏览数量,每1小时从redis获取一次,同步到数据库
     * @return
     */
    public Integer getArticleBrowse();


    /**
     * 从redis获取文章点赞列表(每1个小时获取一次(定时器).然后同步到数据库)
     * @return
     */
    // public List<Integer> articlePraiseList();


}
