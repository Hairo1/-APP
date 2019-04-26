package com.Hairo.controller;

import com.Hairo.pojo.Blogroll;
import com.Hairo.service.BlogrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/4/18 2:38
 * 作用描述:
 * 友情链接控制器
 */
@RestController
@RequestMapping("/api/blogroll/")
public class BlogrollController {

    @Autowired
    private BlogrollService blogrollService;

    /**
     * 获取所有友情链接
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Object selectAllBlogroll(){
        return blogrollService.selectAllBlogrolls();
    }

    /**
     * 根据ID获取友情链接
     * @return
     */
    @RequestMapping(value = "/{blogrollId}/",method = RequestMethod.GET)
    public Object selectBlogrollById(@PathVariable("blogrollId") Integer blogrollId){
        return blogrollService.selectBlogrollById(blogrollId);
    }

    /**
     * 添加友情链接
     * @param blogroll
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Integer addBlogroll(@RequestParam("blogroll") Blogroll blogroll){
            return blogrollService.insertBlogroll(blogroll);
    }

    /**
     * 修改友情链接
     * @param blogroll
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public Integer updateBlogroll(@RequestParam("blogroll") Blogroll blogroll){
        return blogrollService.updateBlogroll(blogroll);
    }

    /**
     * 删除友情链接
     * @param blogrollId
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public Integer updateBlogroll(@RequestParam("blogrollId") Integer blogrollId){
        return blogrollService.delectBlogrollById(blogrollId);
    }

}
