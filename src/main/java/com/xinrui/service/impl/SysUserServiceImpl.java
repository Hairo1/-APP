package com.xinrui.service.impl;

import com.xinrui.mappers.userMapper.SysUserMapper;
import com.xinrui.pojo.SysUsers;
import com.xinrui.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 项目名称： xinrui
 * 作 者   ： Hairo
 * 创建时间: 2019/3/21 20:36
 * 作用描述:
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper userMapper;
    @Override
    public List<SysUsers> getAllUser() {
        return userMapper.getAllUser();
    }
}
