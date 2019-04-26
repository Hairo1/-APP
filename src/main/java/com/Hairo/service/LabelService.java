package com.Hairo.service;

import com.Hairo.pojo.Label;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class)//统一事务
public interface LabelService {
    public Label getLabelById( String l_name );
    public   List<Label> getAllLabel();

    Integer selectLabelCountByName( String l_name);
}
