package com.Hairo.controller;

import com.Hairo.pojo.SysUsers;
import com.Hairo.service.SysUserService;
import com.Hairo.util.HairoUtil;
import com.sun.corba.se.spi.ior.ObjectKey;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/3/21 16:38
 * 作用描述:
 */
@Api(value = "用户Controller",tags = "用户操作接口")
@RestController
@RequestMapping("/api/sysUser")
public class SysUserController {
    private static Logger logger = Logger.getLogger(SysUserController.class);
    @Autowired
    private SysUserService userService;

    @ApiOperation(value = "获取所有用户",notes = "只有登陆的管理员才能操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sysUsers",value = "操作的管理员",required = true,dataType ="SysUsers" ),
            @ApiImplicitParam(name = "page",value = "获取第N页数据",required = true,dataType ="Integer" ),
            @ApiImplicitParam(name = "state",value = "获取用户状态(1启动-2禁用-3黑名单)",required = true,dataType ="Integer" )
    })
    @RequestMapping(value = "/page/{page}",method = RequestMethod.GET)
    public Object getUers(@PathVariable SysUsers sysUsers,@PathVariable("page") Integer page,Integer state){
        if(state==null || state <0 || state>4){
            state = 1;
        }
        SysUsers user =HairoUtil.verifyUser(sysUsers);
        if(user == null || !user.getU_password().equals(sysUsers.getU_password())){
            return HairoUtil.result(3);
        }
        if(HairoUtil.permissionQuery(user.getU_id())){
            return userService.getAllUser(page,state);
        }else{
            return HairoUtil.result(2);
        }

    }

    @ApiOperation(value = "获取用户总数量")
    @ApiImplicitParam(name = "state",value = "用户状态1启用 2禁用 3黑名单",required = false,dataType = "Integer")
    @RequestMapping(value = "/count/",method = RequestMethod.GET)
    public Object getUserSum(Integer state){
        if(state == null || state <=0 || state >3){
            state = 1;
        }
            return userService.getUserSum(state);
    }

    /**
     * 获取作者信息
     * @param author
     * @return
     */

    @ApiOperation(value = "获取指定用户",notes = "-------------")
    @ApiImplicitParam(name = "author",value = "用户昵称",required = true,dataType ="String" )
    @RequestMapping(value = "/author/{author}",method = RequestMethod.GET)
    public Object getAuthorByauthorName(@PathVariable("author") String author){
        if(author ==null || author.equals("")){
            return HairoUtil.result(-1);
        }
        return userService.getUserByName(author);
    }

    @ApiOperation(value = "修改用户",notes = "只有登陆的用户才能操作")
    @ApiImplicitParam(name = "sysUsers",value = "修改的用户",required = true,dataType ="SysUsers" )
    @RequestMapping(value = "/",method = RequestMethod.PUT)
    private Object updateAuthor(SysUsers sysUsers){
        SysUsers user = HairoUtil.verifyUser(sysUsers);
        if(user != null && user.getU_password().equals(sysUsers.getU_password())){
            return HairoUtil.result(userService.updateUser(user));
        }else {
            return HairoUtil.result(3);
        }
    }

    /**
     * 新用户
     * @param sysUser
     * @return
     */
    @ApiOperation(value = "添加新用户",notes = "无权限")
    @ApiImplicitParam(name = "sysUser",value = "新用户实体",required = true,dataType = "SysUsers")
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Object newUser( SysUsers sysUser)  {
        if(sysUser==null || sysUser.getU_password()==null||sysUser.getU_name()==null ||sysUser.getU_password().length()!=32){
            return HairoUtil.result(-1);
        }
        if(userService.getUserByName(sysUser.getU_name())==null){
            return HairoUtil.result(userService.addUser(sysUser));

        }else{
            Map map = new  HashMap<>();
            map.put("success",0);
            map.put("message","注册失败,该用户已经存在...");
            return map;
        }
    }

    /**
     * 用户登陆
     * @param userName 用户名
     * @param userPwd
     * @return
     */
    @ApiOperation(value = "用户登陆",notes = "------------")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName",value = "登陆的用户名",required = true,dataType = "String"),
            @ApiImplicitParam(name = "userPwd",value = "登陆的密码",required = true,dataType = "String")
    })
    @RequestMapping(value = "/login/username/{userName}/userpwd/{userPwd}",method = RequestMethod.GET)
    public Object login(@PathVariable("userName") String userName,@PathVariable("userPwd") String userPwd){
        if(userName == null || userName.equals("") || userPwd == null || userPwd.length()<32){
            return HairoUtil.result(-1);
        }
        SysUsers user = userService.getUserByName(userName);
        if(user != null && user.getU_password().equals(userPwd)){
            return user;
        }else {
            return HairoUtil.result(3);
        }
    }



}
