package com.xinrui.mappers.userMapper;

import com.xinrui.pojo.SysUsers;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface SysUserMapper {
   public List<SysUsers> getAllUser();


}
