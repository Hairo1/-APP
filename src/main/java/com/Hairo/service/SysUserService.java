package com.Hairo.service;

import com.Hairo.pojo.SysUsers;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional(rollbackFor = Exception.class)//统一事务
public interface SysUserService {
    public List<SysUsers> getAllUser( int page,Integer state);
    public Integer addUser(SysUsers user);
    public SysUsers getUserByName( String userName);
    public Integer delUser( String u_name);
    public Integer updateUser(SysUsers user);
    public Integer getUserSum(Integer state);

}
