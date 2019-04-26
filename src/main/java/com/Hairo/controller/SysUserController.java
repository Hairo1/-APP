package com.Hairo.controller;

import com.Hairo.pojo.SysUsers;
import com.Hairo.service.SysUserService;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;


/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/3/21 16:38
 * 作用描述:
 */
@RestController
@RequestMapping("/api/sysUser")
public class SysUserController {
    private static Logger logger = Logger.getLogger(SysUserController.class);

    @Autowired
    private SysUserService userService;
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public String s(){
        //System.out.println(userService.getAllUser());
        return userService.getAllUser().toString();
    }

    @RequestMapping(value = "/newUser/",method = RequestMethod.POST)
    public Integer newUser(SysUsers sysUser)  {
       return userService.addUser(sysUser);
    }

    @RequestMapping(value = "/login/username/{userName}/userpwd/{userPwd}",method = RequestMethod.GET)
    public Object login(@PathVariable("userName") String userName,@PathVariable("userPwd") String userPwd)  {
        if(userName=="" || userName==null || userName.length()<2 || userName.length()>10 ){return "-1" ;}
        if(userPwd=="" || userPwd==null || userPwd.length()!=32 ){return "-2" ;}
        SysUsers user = userService.getUserByName(userName);
        if(user==null){return "-3";}
        if(!user.getU_password().equals(userPwd)){return "-4" ;}
       return user;
    }


    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @RequestMapping(value = "/db",method = RequestMethod.GET)
    public String b(){
        StringRedisTemplate redisTemplate = getRedisTemplate();
        ValueOperations opsForValue = redisTemplate.opsForValue();
        opsForValue.set("测试","测试s");
        System.out.println(opsForValue.get("测试")+"********************");
        return "OK";
    }

    /**
     * 切换Redis数据库
     * RedisDB-3作为公共缓存DB
     * @return
     */
    private StringRedisTemplate getRedisTemplate() {
        LettuceConnectionFactory jedisConnectionFactory = (LettuceConnectionFactory) stringRedisTemplate.getConnectionFactory();
        jedisConnectionFactory.setDatabase(3);
        stringRedisTemplate.setConnectionFactory(jedisConnectionFactory);
        jedisConnectionFactory.resetConnection();//重置数据库s
        return stringRedisTemplate;
    }
}
