package com.xinrui.service;

import com.xinrui.pojo.SysUsers;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
@Transactional(rollbackFor = Exception.class)//统一事务
public interface SysUserService {
    public List<SysUsers> getAllUser();
    public Integer addUser(SysUsers user);
}
