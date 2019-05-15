package com.Hairo.service;

import com.Hairo.pojo.Articles;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

/**
 *
 */
@Transactional(rollbackFor = Exception.class)//统一事务
public interface RedisService {




    /**
     * 从Redis中删除指定KEY
     * @param key
     * @return
     */
    public boolean delectKey(String key);

    /**
     * 如果KEY存在则返回true
     * @param key
     * @return
     */
    public boolean hashKey(Object key);

    /**
     * 根据KEY获取VALUE(String)
     * @param key
     * @return
     */
    public Object getValue(Object key);

    public Integer set(Object key,Object val);

    public Integer set(Object key,Object val,long l);

    /**
     * 指定h增加/覆盖指定键值对
     * @param h 指定hash
     * @param hk 键
     * @param hv 值
     */
    public void put(Object h,Object hk,Object hv);

    public long hsize(Object h);

    /**
     * 根据HKEY获取VALUES(hash)
     * @param hkey
     * @return
     */
    public Object getHValue(Object h,Object hkey);

    /**
     *根据Hkey获取所有KEY(hash)
     * @param hkey
     * @return
     */
    public Object getHKey(Object hkey);

    /**
     * 根据HKEY获取键值对列表
     * @param hkey
     * @return
     */
    public Map<Object,Object> getEntries(Object hkey);

    /**
     * 根据h(hash)删除objects(hash里面的KEY)
     * @param h 指定hash
     * @param objects 删除的KEY
     * @return
     */
    public long delectHash(Object h,Object ...objects);
}
