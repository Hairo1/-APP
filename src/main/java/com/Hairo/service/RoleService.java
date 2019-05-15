package com.Hairo.service;

import com.Hairo.pojo.Role;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class)//统一事务
public interface RoleService {


    /**
     * 根据用户ID获取用户权限
     * @param userId
     * @return
     */
    public List<String > getUserRole(Integer userId);
}
