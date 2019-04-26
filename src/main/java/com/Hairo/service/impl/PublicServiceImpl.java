package com.Hairo.service.impl;

import com.Hairo.mappers.articlesMapper.ArticlesMapper;
import com.Hairo.pojo.Articles;
import com.Hairo.service.ArticlesService;
import com.Hairo.service.PublicService;
import com.Hairo.util.ApplicationContextHolder;
import com.Hairo.util.SerializeUtil;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private static RedisTemplate<Object, Object> redisTemplate;
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
        redisTemplate.opsForList().rightPush("likePlus1", articleId);
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


    /**
     * 获取Redis实例
     * @return
     */
    private synchronized RedisTemplate getRedisTemplate() {
        if (redisTemplate == null) {
            redisTemplate = ApplicationContextHolder.getBean("redisTemplate");
            redisTemplate.setKeySerializer(new JdkSerializationRedisSerializer());
            redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
//            LettuceConnectionFactory jedisConnectionFactory = (LettuceConnectionFactory) redisTemplate.getConnectionFactory();
//            //序列化
//            RedisSerializer<String> redisSerializer = new StringRedisSerializer();
//            redisTemplate.setKeySerializer(redisSerializer);
//            redisTemplate.setValueSerializer(redisSerializer);
//            //不设置默认会把JSON转换为hasMap
//            redisTemplate.setHashKeySerializer(redisSerializer);
//
//            Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.setVisibility(PropertyAccessor.ALL,JsonAutoDetect.Visibility.ANY);
//            objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//            jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
//            redisTemplate.setHashValueSerializer(redisSerializer);
            //jedisConnectionFactory.setDatabase(3);//选择第3个数据库作为数据缓存DB
            //redisTemplate.setConnectionFactory(jedisConnectionFactory);
            //jedisConnectionFactory.resetConnection();//重置数据库
            //System.out.println("切换redisDB3");
        }
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
