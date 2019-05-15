package com.Hairo.mappers.labelMapper;

import com.Hairo.pojo.Label;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LabelMapper {
    Label getLabelById(@Param("l_name") String l_name);

    List<Label> getAllLabel(@Param("state") Integer state);

    Integer selectLabelCountByName(@Param("l_name") String l_name);

    Integer delLabelById(@Param("labelId") Integer labelId );

    Integer insertLabel(Label label);

    Integer updateLabel(Label label);

    Integer updateLabelCount(@Param("l_name") String l_name,@Param("count") int count);

 }
