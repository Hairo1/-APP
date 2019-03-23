package com.xinrui.controller;

import com.xinrui.pojo.SysUsers;
import com.xinrui.service.SysUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    private static Logger logger = Logger.getLogger(SysUserController.class);

    @Autowired
    private SysUserService userService;
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public String s(){
        //System.out.println(userService.getAllUser());
        return userService.getAllUser().toString();
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.GET)
    public int addUser(String user_name,String user_pwd)  {
        SysUsers user =new SysUsers();
        user.setUser_name(user_name);
        user.setUser_pwd(user_pwd);
        try {
            if(userService.addUser(user)>0){
                return 1;
            }else {
                return 0;
            }
        } catch (Exception e) {
            logger.info("异常抛出："+e);
        }
        return -1;
    }
}
