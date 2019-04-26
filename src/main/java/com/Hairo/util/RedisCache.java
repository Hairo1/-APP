package com.Hairo.util;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/3/22 15:08
 * 作用描述:
 *  实现Mybatis二级缓存
 */

public class RedisCache implements Cache {



    /** The ReadWriteLock. */
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

  //  private static RedisTemplate<Object, Object> redisTemplate;

    private String id;
    public RedisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void  putObject(Object key, Object value) {
        RedisTemplate redisTemplate = getRedisTemplate();
        ValueOperations opsForValue = redisTemplate.opsForValue();
        //if(value.toString().length()<=2){return;}
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        opsForValue.set(key, value);
        System.out.println("****************缓存写入Redis************");
    }

    @Override
    public Object getObject(Object key) {
        RedisTemplate redisTemplate = getRedisTemplate();
        ValueOperations opsForValue = redisTemplate.opsForValue();
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        Object value = opsForValue.get(key);
        System.out.println(value==null?"\t---------->Redis获取结果为空,去数据库查询":"\t****************从Redis中读取数据************");
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
            System.out.println("\n******************************检测到-增删改-操作,刷新缓存(RedisDB->9)****************************\n");
            return null;
        });
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }


    private synchronized RedisTemplate getRedisTemplate() {
            RedisTemplate<Object, Object> redisTemplate = ApplicationContextHolder.getBean("redisTemplate");
            redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
            LettuceConnectionFactory jedisConnectionFactory = (LettuceConnectionFactory) redisTemplate.getConnectionFactory();
            jedisConnectionFactory.setDatabase(9);//选择第九个数据库作为mybatis缓存DB
            redisTemplate.setConnectionFactory(jedisConnectionFactory);
            jedisConnectionFactory.resetConnection();//重置数据库
        return redisTemplate;
    }

}
