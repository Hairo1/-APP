package com.Hairo.mappers.userMapper;

import com.Hairo.pojo.SysUsers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface SysUserMapper {
   public List<SysUsers> getAllUser(@Param("page") int page,@Param("pageSize")int pageSize,@Param("state") Integer state);
   public Integer addUser(SysUsers user);
   public SysUsers getUserByName(@Param("u_name") String u_name);
   public Integer delUser(@Param("u_name") String u_name);
   public Integer updateUser(SysUsers user);
   public Integer selectUserSum(@Param("state") Integer state);

}
