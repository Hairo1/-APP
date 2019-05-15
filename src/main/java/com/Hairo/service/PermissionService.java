package com.Hairo.service;


import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)//统一事务
public interface PermissionService {


    public String PermissionQuery(Integer userId);
}
