package com.Hairo.controller;

import com.Hairo.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/4/16 15:24
 * 作用描述:
 * 标签云
 */
@RestController
@RequestMapping("/api/label/")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public Object getAllLabel(){
        return labelService.getAllLabel();
    }


    @RequestMapping(value = "/{l_name}/count/",method = RequestMethod.GET)
    public Object getLabelCountByName(@PathVariable("l_name") String l_name){
        return labelService.selectLabelCountByName(l_name);
    }
}
