package com.xinrui.util;

import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 项目名称： xinrui
 * 作 者   ： Hairo
 * 创建时间: 2019/3/22 15:08
 * 作用描述:
 *  实现Mybatis二级缓存
 */

public class RedisCache implements Cache {



    /** The ReadWriteLock. */
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static RedisTemplate<Object, Object> redisTemplate;
    //private static final long EXPIRE_TIME_IN_MINUTES = 30; // redis过期时间


    private String id;
    public RedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
       // logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>RedisCache:id="+id);
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void putObject(Object key, Object value) {
        RedisTemplate redisTemplate = getRedisTemplate();
        ValueOperations opsForValue = redisTemplate.opsForValue();
        if(value.toString().length()<=2){return;}
      //  logger.debug("######################################################################\n\t---------->redis缓存中存储数据:"+key+"\n######################################################################");
        opsForValue.set(key, value);
        System.out.println("****************缓存写入Redis************");
    }

    @Override
    public Object getObject(Object key) {
        RedisTemplate redisTemplate = getRedisTemplate();
        ValueOperations opsForValue = redisTemplate.opsForValue();
        Object value = opsForValue.get(key);
        System.out.println(value==null?"\t---------->Redis获取结果为空,去数据库查询":"\t****************从Redis中读取数据************");
        //logger.info("######################################################################");
       // logger.info(value==null?"\t---------->获取结果为空,去数据库查询":"\t---------->从缓存中读取："+key);
       // logger.info("######################################################################");
        return value;
    }

    @Override
    public Object removeObject(Object key) {
        RedisTemplate redisTemplate = getRedisTemplate();
        return redisTemplate.delete(key);
    }

    @Override
    public void clear() {
        RedisTemplate redisTemplate = getRedisTemplate();
        redisTemplate.execute((RedisCallback) connection -> {
            connection.flushDb();
            return null;
        });
    }

    @Override
    public int getSize() {
//        RedisTemplate redisTemplate = getRedisTemplate();
//        ValueOperations opsForValue = redisTemplate.opsForValue();
//        return Integer.valueOf(opsForValue.s().toString());
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }
    private RedisTemplate getRedisTemplate() {
        if (redisTemplate == null) {
            redisTemplate = ApplicationContextHolder.getBean("redisTemplate");
        }
        return redisTemplate;
    }

}
