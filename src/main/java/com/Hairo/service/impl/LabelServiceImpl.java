package com.Hairo.service.impl;

import com.Hairo.mappers.labelMapper.LabelMapper;
import com.Hairo.pojo.Label;
import com.Hairo.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/4/14 14:29
 * 作用描述:
 */
@Service
public class LabelServiceImpl implements LabelService {
    @Autowired
    private LabelMapper labelMapper;
    @Override
    public Label getLabelById(String l_name) {
        return labelMapper.getLabelById(l_name);
    }

    @Override
    public List<Label> getAllLabel() {
        return labelMapper.getAllLabel();
    }

    @Override
    public Integer selectLabelCountByName(String l_name) {
        if(l_name == null || 0 == l_name.length() || "".equals(l_name)){
            return -1;
        }
        return labelMapper.selectLabelCountByName(l_name);
    }
}
