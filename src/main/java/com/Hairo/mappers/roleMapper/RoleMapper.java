package com.Hairo.mappers.roleMapper;

import com.Hairo.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {

    public List<String> selectUserRole(@Param("userId") Integer userId);
}
