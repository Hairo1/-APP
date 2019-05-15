package com.Hairo.service.impl;

import com.Hairo.mappers.permissionMapper.PermissionMapper;
import com.Hairo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/5/12 18:09
 * 作用描述:
 */

@Service
public class PermissionServiceImpl  implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public String PermissionQuery(Integer userId) {
        if(userId == null || "" .equals(userId)){
            return null;
        }
        return permissionMapper.userPermissionQuery(userId);
    }
}
