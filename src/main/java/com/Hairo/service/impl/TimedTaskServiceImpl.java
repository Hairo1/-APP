package com.Hairo.service.impl;

import com.Hairo.mappers.articlesMapper.ArticlesMapper;
import com.Hairo.pojo.Articles;
import com.Hairo.service.ArticlesService;
import com.Hairo.service.RedisService;
import com.Hairo.service.TimedTaskService;
import com.Hairo.util.HairoUtil;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/4/30 15:57
 * 作用描述:
 *
 *  *   @Scheduled(cron = "0 0 * * * ?")
 *  * 第一位，表示秒，取值0-59
 *
 *  * 第二位，表示分，取值0-59
 *
 *  * 第三位，表示小时，取值0-23
 *
 *  * 第四位，日期天/日，取值1-31
 *
 *  * 第五位，日期月份，取值1-12
 *
 *  * 第六位，星期，取值1-7，星期一，星期二...，注：不是第1周，第二周的意思
 *
 *  * 另外：1表示星期天，2表示星期一。
 *
 *  * 第7为，年份，可以留空，取值1970-2099
 *  *  @Scheduled(cron = "0 0/50 * * * ?")
 */
@Service
@EnableScheduling
@SuppressWarnings("all")
public class TimedTaskServiceImpl implements TimedTaskService {
    private static Logger log = Logger.getLogger(TimedTaskServiceImpl.class);
    @Autowired
    private RedisService redisService;
    @Autowired
    private ArticlesMapper articlesMapper;

    /**
     * 从MYSQL 获取数据到Redis
     * @param page
     */

    @Override
    @Scheduled(cron = "0 0/50 * * * ?")
    public void setArticleTORedis() {
        //从数据库中获取75编文章(每页HairoUtil.PAGESIZE，5页)
        for (int i = 0; i < 5; i++){
            Object o = articlesMapper.selectAllArticles(i*HairoUtil.PAGESIZE,HairoUtil.PAGESIZE,1);
            if(o == null || "".equals(o) || o.toString().length()==2){
                return;
            }else {
                System.out.println(o.toString().length());
                outLogger("从数据库中获取第"+(i+1)+"页数据缓存至Redis");
                redisService.put("ARTICLE_LIST_HKEY","ARTICLE_PAGE_"+(i+1),o);
            }
        }
        //从MYSQL中获取文章总数量，缓存至Redis
        redisService.set("ARTICLE_SUM",articlesMapper.selectArticleCount());
    }

    /**
     * 从MYSQL中获取热门文章至Redis
     * @return
     */
    @Override
    @Scheduled(cron = "0 0/58 * * * ?")
    public Integer setHotArticle() {
        return redisService.set("HotArticle",articlesMapper.selectArticleHot8());
    }

    @Override
    @Scheduled(cron = "0 0/59 * * * ?")
    public Integer setRandomArticle() {
        return redisService.set("RandomArticle",articlesMapper.selectRandomArticle());
    }

    /**
     * 批量更新文章浏览量
     * @return
     */
    @Override
    @Scheduled(cron = "0 0/10 * * * ?")
    public Integer getArticleBrowse() {
        Map<Object, Object> map = redisService.getEntries("ARTICLE_BROWSE");
        if (map ==null || map.size() == 0){

            return 0;
        }else {
            articlesMapper.updateArticlePageView(map);
            outLogger("批量更新文章浏览量完成!");
            redisService.delectKey("ARTICLE_BROWSE");
            outLogger("从Redis删除文章浏览记录完毕");
            return 1;
        }
    }




    /**
     * 输出日志
     * @param text 日志内容
     */
    private  void outLogger(String text){
        log.info("\n\n******************************************"+text+"******************************************\n");
    }
}


