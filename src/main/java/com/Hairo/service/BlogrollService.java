package com.Hairo.service;


import com.Hairo.pojo.Blogroll;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 友情链接服务接口
 */
@Transactional(rollbackFor = Exception.class)//统一事务
public interface BlogrollService {


    /**
     * 获取所有友情链接
     * @return
     */
    public List<Blogroll> selectAllBlogrolls();


    /**
     * 添加友情链接
     * @param blogroll 友情链接实体
     * @return
     */
    public Integer insertBlogroll(Blogroll blogroll);

    /**
     * 修改友情链接
     * @param blogroll 友情链接实体
     * @return
     */
    public Integer updateBlogroll(Blogroll blogroll);

    /**
     * 根据Id获取指定友情链接
     * @param blogrollId
     * @return
     */
    public Integer selectBlogrollById( Integer blogrollId);

    /**
     * 根据Id删除 友情链接
     * @param blogrollId
     * @return
     */
    public Integer delectBlogrollById( Integer blogrollId);
}
