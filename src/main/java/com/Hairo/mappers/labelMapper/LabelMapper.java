package com.Hairo.mappers.labelMapper;

import com.Hairo.pojo.Label;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LabelMapper {
    Label getLabelById(@Param("l_name") String l_name);

    List<Label> getAllLabel();

    Integer selectLabelCountByName(@Param("l_name") String l_name);
}
