package com.Hairo.service.impl;

import com.Hairo.mappers.roleMapper.RoleMapper;
import com.Hairo.pojo.Role;
import com.Hairo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/5/6 17:15
 * 作用描述:
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<String> getUserRole(Integer userId) {
        if(userId == null || userId<9999){
            return null;
        }
        return roleMapper.selectUserRole(userId);
    }
}
