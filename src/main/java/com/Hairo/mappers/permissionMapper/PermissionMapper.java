package com.Hairo.mappers.permissionMapper;

import org.apache.ibatis.annotations.Param;

public interface PermissionMapper {


    public String userPermissionQuery(@Param("userId") Integer userId);
}
