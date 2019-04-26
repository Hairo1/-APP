package com.Hairo.service.impl;

import com.Hairo.mappers.BlogrollMapper.BlogrollMapper;
import com.Hairo.pojo.Blogroll;
import com.Hairo.service.BlogrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/4/18 2:34
 * 作用描述:
 */
@Service
public class BlogrollServiceImpl implements BlogrollService {

    @Autowired
    private BlogrollMapper blogrollMapper;

    @Override
    public List<Blogroll> selectAllBlogrolls() {
        return blogrollMapper.selectAllBlogrolls();
    }

    @Override
    public Integer insertBlogroll(Blogroll blogroll) {
        return blogrollMapper.insertBlogroll(blogroll);
    }

    @Override
    public Integer updateBlogroll(Blogroll blogroll) {
        return blogrollMapper.updateBlogroll(blogroll);
    }

    @Override
    public Integer selectBlogrollById(Integer blogrollId) {
        return blogrollMapper.selectBlogrollById(blogrollId);
    }

    @Override
    public Integer delectBlogrollById(Integer blogrollId) {
        return blogrollMapper.delectBlogrollById(blogrollId);
    }
}
