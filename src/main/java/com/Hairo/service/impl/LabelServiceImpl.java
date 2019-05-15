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
    public Label getLabelByName(String l_name) {
        if(l_name ==null || l_name.equals("")){
            return null;
        }
        return labelMapper.getLabelById(l_name);
    }

    @Override
    public List<Label> getAllLabel(Integer state) {
        return labelMapper.getAllLabel(state);
    }

    @Override
    public Integer selectLabelCountByName(String l_name) {
        if(l_name == null || 0 == l_name.length() || "".equals(l_name)){
            return 0;
        }
        return labelMapper.selectLabelCountByName(l_name);
    }


    @Override
    public Integer delLabel(Integer labelId) {
        if(labelId == null || labelId<9999){
            return 0;
        }
        return labelMapper.delLabelById(labelId);
    }

    @Override
    public Integer addLabel(Label label) {
        if(label == null || label.getL_name() == null || "".equals(label.getL_name())){
                return 0;
        }
        return labelMapper.insertLabel(label);
    }

    @Override
    public Integer updateLabel(Label label) {
        if(label == null || label.getL_name() == null || "".equals(label.getL_name())){
            return 0;
        }
        return labelMapper.updateLabel(label);
    }

    @Override
    public Integer updateLabelCount(String l_name,int count) {
        if(l_name==null)
            return 0;
        else
            return labelMapper.updateLabelCount(l_name,count);
    }
}
