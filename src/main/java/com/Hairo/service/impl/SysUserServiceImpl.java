package com.Hairo.service.impl;

import com.Hairo.mappers.userMapper.SysUserMapper;
import com.Hairo.pojo.SysUsers;
import com.Hairo.service.RandomImgService;
import com.Hairo.service.SysUserService;
import com.Hairo.util.HairoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/3/21 20:36
 * 作用描述:
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private RandomImgService randomImgService;
    @Override
    public List<SysUsers> getAllUser(int page,Integer state) {
        if(page<=0){
            return null;
        }
        return userMapper.getAllUser((page-1)* HairoUtil.PAGESIZE,HairoUtil.PAGESIZE,state);
    }

    @Override
    public Integer addUser(SysUsers user) {
        if(user == null){
            return -1;
        }

       return  userMapper.addUser(user);
    }

    @Override
    public SysUsers getUserByName(String  userName) {
        if(userName=="" || userName==null || userName.length()<2 || userName.length()>10 ){return null ;}
        return userMapper.getUserByName(userName);
    }

    @Override
    public Integer delUser(String u_name) {
        if(u_name == null || u_name.equals("")){
            return -1;
        }
        return userMapper.delUser(u_name);
    }

    @Override
    public Integer updateUser(SysUsers user) {
        if(user == null){
            return -1;
        }
        return userMapper.updateUser(user);
    }

    @Override
    public Integer getUserSum(Integer state) {

        return userMapper.selectUserSum(state);
    }

}
