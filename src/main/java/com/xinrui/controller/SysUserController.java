package com.xinrui.controller;

import com.xinrui.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目名称： xinrui
 * 作 者   ： Hairo
 * 创建时间: 2019/3/21 16:38
 * 作用描述:
 */
@RestController
@RequestMapping("api")
public class SysUserController {
    @Autowired
    private SysUserService userService;
    @RequestMapping("/get")
    public String s(){
        //System.out.println(userService.getAllUser());
        return userService.getAllUser().toString();
    }
}
