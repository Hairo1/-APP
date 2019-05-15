package com.Hairo.service;

import com.Hairo.pojo.Label;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class)//统一事务
public interface LabelService {
    public Label getLabelByName( String l_name );
    public   List<Label> getAllLabel(Integer state);
    public Integer selectLabelCountByName( String l_name);
    public Integer delLabel(Integer labelId);
    public Integer addLabel(Label label);
    public Integer updateLabel(Label label);
    public Integer updateLabelCount( String l_name,int count);
}
