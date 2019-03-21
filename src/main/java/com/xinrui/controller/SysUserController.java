package com.xinrui.controller;

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
    @RequestMapping("/get")
    public String s(){
        return "NAIS";
    }
}
