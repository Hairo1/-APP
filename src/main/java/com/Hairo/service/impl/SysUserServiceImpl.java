package com.Hairo.service.impl;

import com.Hairo.mappers.userMapper.SysUserMapper;
import com.Hairo.pojo.SysUsers;
import com.Hairo.service.SysUserService;
import com.Hairo.util.HairoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    @Override
    public List<SysUsers> getAllUser() {
        return userMapper.getAllUser();
    }

    @Override
    public Integer addUser(SysUsers user) {
        if(user==null){return 0;}
        if(user.getU_name()=="" || user.getU_name()==null || user.getU_name().length()<2 || user.getU_name().length()>10 ){return -1 ;}
        if(user.getU_password()=="" || user.getU_password()==null || user.getU_password().length()!=32 ){return -2 ;}
        if(user.getU_contact()=="" || user.getU_contact()==null || user.getU_contact().length()<5 || user.getU_contact().length()>20 ){return -3 ;}
        if(HairoUtil.checkEmail(user.getU_email())==false){return -4;}
        if( userMapper.getUserByName(user.getU_name())!=null){return -5;}
        return userMapper.addUser(user);

    }

    @Override
    public SysUsers getUserByName(String  userName) {
        if(userName=="" || userName==null || userName.length()<2 || userName.length()>10 ){return null ;}
        return userMapper.getUserByName(userName);
    }

}
