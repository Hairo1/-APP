package com.Hairo.service.impl;
import com.Hairo.mappers.articlesMapper.ArticlesMapper;
import com.Hairo.service.PublicService;
import com.Hairo.util.ApplicationContextHolder;
import com.Hairo.util.HairoUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/4/24 17:27
 * 作用描述:
 * 热么文章+推荐文章设置和获取
 */
@Service
public class PublicServiceImpl implements PublicService {
    private static Logger log = Logger.getLogger(PublicServiceImpl.class);
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
   // private static RedisTemplate<Object, Object> redisTemplate;
    @Autowired
    private ArticlesMapper articlesMapper;

    @Override
    public Object getHotArticle() {

        RedisTemplate redisTemplate = getRedisTemplate();
        Object o = redisTemplate.opsForValue().get("hotArticle");
        if( o==null){

            setHotArticle();
            o = redisTemplate.opsForValue().get("hotArticle");
        }
        outLogger("从redis获取热门文章");
        return  o;
    }

    @Override
    public Object getSuggestedArticle() {
        RedisTemplate redisTemplate = getRedisTemplate();
        ValueOperations opsForValue = redisTemplate.opsForValue();
        Object o =opsForValue.get("");
        return o;
    }

    @Override
    public void setHotArticle() {
        outLogger("从数据库中获取热门文章保存到redis");
       // System.out.println("设置热门文章到Redis");
        RedisTemplate redisTemplate = getRedisTemplate();
        redisTemplate.opsForValue().set("hotArticle",articlesMapper.selectArticleHot8());
    }

    @Override
    public void setSuggestedArticle() {

    }

    @Override
    public void articleBrowsePlus1(Integer articleId) {
        RedisTemplate redisTemplate = getRedisTemplate();
        redisTemplate.opsForList().rightPush("browsePlus1", articleId);
        //System.out.println(redisTemplate.opsForList().size("browsePlus1"));

    }

    @Override
    public void articleLikePlus1(Integer articleId) {
        getRedisTemplate().opsForList().rightPush("likePlus1", articleId);
    }

    @Override
    public List<Integer> articleBrowse() {
        return null;
    }

    @Override
    public List<Integer> articleLike() {
        return null;
    }

    @Override
    public Object getRandomArticle() {
        RedisTemplate redisTemplate = getRedisTemplate();
        Object o = redisTemplate.opsForValue().get("randomArticle");
        if( o==null){
            setRandomArticle();
            o =  redisTemplate.opsForValue().get("randomArticle");
        }
        outLogger("从redis获取随机文章");
        return o;
    }

    @Override
    public void setRandomArticle() {
        outLogger("从数据库中获取随机文章,保存到Redis");
        RedisTemplate redisTemplate = getRedisTemplate();
        redisTemplate.opsForValue().set("randomArticle",articlesMapper.selectRandomArticle());
    }

    @Override
    public Integer articlePraise(Integer articleId, String iP) {
        //IP不正确|文章ID有误
        if(articleId == null || articleId ==0 || HairoUtil.isIP(iP)==false){
            return -1;
        }
        RedisTemplate redisTemplate = getRedisTemplate();
        Object o = redisTemplate.opsForValue().get(articleId + ":" + iP);
        //System.out.println(o+"*****************************************");
        //48小时内已经对当前文章点过赞
        if(o != null){
            return 0;
        }
        //文章点赞更新
        articlesMapper.updateArticlePraise(articleId);
        //记录点赞文章和IP,保存48小时60*60*48
        redisTemplate.opsForValue().set(articleId+":"+iP, 1,60*60*48,TimeUnit.SECONDS);
        return 1;
    }


    /**
     * 获取Redis实例
     * @return
     */
    private synchronized RedisTemplate getRedisTemplate() {
            RedisTemplate<Object, Object> redisTemplate = ApplicationContextHolder.getBean("redisTemplate");
            redisTemplate.setKeySerializer(new JdkSerializationRedisSerializer());
            redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
            LettuceConnectionFactory jedisConnectionFactory = (LettuceConnectionFactory) redisTemplate.getConnectionFactory();
            jedisConnectionFactory.setDatabase(3);//选择第九个数据库作为其他缓存,防止mybatis刷新缓存
            redisTemplate.setConnectionFactory(jedisConnectionFactory);
            jedisConnectionFactory.resetConnection();//重置数据库
        return redisTemplate;
    }

    /**
     * 输出日志
     * @param text 日志内容
     */
    private  void outLogger(String text){
        log.info("\n******************************************"+text+"******************************************\n");
    }
}
