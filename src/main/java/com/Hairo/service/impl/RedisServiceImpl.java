package com.Hairo.service.impl;
import com.Hairo.mappers.articlesMapper.ArticlesMapper;
import com.Hairo.pojo.Articles;
import com.Hairo.service.ArticlesService;
import com.Hairo.service.RedisService;
import com.Hairo.util.ApplicationContextHolder;
import com.Hairo.util.HairoUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/4/24 17:27
 * 作用描述:
 * 操作Redis
 * 声明：

 */
@Service
@SuppressWarnings("all")
public class RedisServiceImpl implements RedisService {


    private static Logger log = Logger.getLogger(RedisServiceImpl.class);
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
   // private static RedisTemplate<Object, Object> redisTemplate;




    @Override
    public boolean delectKey(String key) {
        if(isNull(key)==false) return false;
        return getRedisTemplate().delete(key);
    }

    @Override
    public boolean hashKey(Object key) {
        if(isNull(key)==false) return false;
        return  getRedisTemplate().hasKey(key);
    }

    @Override
    public Object getValue(Object key) {
        if(isNull(key)==false) return null;
        return getRedisTemplate().opsForValue().get(key);
    }

    @Override
    public Integer set(Object key,Object val) {
        if(hashKey(key)){
            return 0;
        }
        getRedisTemplate().opsForValue().set(key,val);
        return 1;
    }

    @Override
    public Integer set(Object key,Object val, long l) {
        if(hashKey(key)){
            return -1;
        }
        getRedisTemplate().opsForValue().set(key,val,l,TimeUnit.SECONDS);
        return 1;
    }

    @Override
    public void put(Object h, Object hk, Object hv) {
            getRedisTemplate().opsForHash().put(h,hk,hv);
    }

    @Override
    public long hsize(Object h) {
        return getRedisTemplate().opsForHash().size(h);
    }


    @Override
    public Object getHValue(Object h,Object hkey) {
        return getRedisTemplate().opsForHash().get(h,hkey);
    }

    @Override
    public Object getHKey(Object hkey) {
        return getRedisTemplate().opsForHash().keys(hkey);
    }

    @Override
    public Map<Object, Object> getEntries(Object hkey) {
        return getRedisTemplate().opsForHash().entries(hkey);
    }

    @Override
    public long delectHash(Object h, Object... objects) {
        return getRedisTemplate().opsForHash().delete(h,objects);
    }


    private boolean isNull(Object o){
        if(o == null || "".equals(o)){
            return false;
        }else{
            return true;
        }
    }
    /**
     * 获取Redis实例
     * @return
     */
    private synchronized RedisTemplate getRedisTemplate() {
            RedisTemplate<Object, Object> redisTemplate = ApplicationContextHolder.getBean("redisTemplate");
            redisTemplate.setKeySerializer(new JdkSerializationRedisSerializer());
            redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
           // LettuceConnectionFactory jedisConnectionFactory = (LettuceConnectionFactory) redisTemplate.getConnectionFactory();
           // jedisConnectionFactory.setDatabase(3);//选择第九个数据库作为其他缓存,防止mybatis刷新缓存
           // redisTemplate.setConnectionFactory(jedisConnectionFactory);
           // jedisConnectionFactory.resetConnection();//重置数据库
        return redisTemplate;
    }

    /**
     * 输出日志
     * @param text 日志内容
     */
    private  void outLogger(String text){
        log.info("\n\n******************************************"+text+"******************************************\n");
    }
}
