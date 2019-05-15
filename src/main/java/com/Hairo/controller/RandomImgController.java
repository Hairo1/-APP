package com.Hairo.controller;

import com.Hairo.mappers.permissionMapper.PermissionMapper;
import com.Hairo.pojo.SysUsers;
import com.Hairo.service.PermissionService;
import com.Hairo.service.RandomImgService;
import com.Hairo.service.SysUserService;
import com.Hairo.util.HairoUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.Map;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/5/12 16:55
 * 作用描述:
 */
@Api(value = "随机图片Controller",tags = {"随机图片操作接口"})
@RestController
@RequestMapping("/api/randomImg")
public class RandomImgController {

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private RandomImgService randomImgService;
    @Autowired
    private PermissionService permissionService;

    @ApiOperation(value = "删除随机图片",notes = "主有登陆的用户且拥有管理权限才能删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "随机图片ID",required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "fileUrl",value = "图片的url用于删除fadsdfs的图片/文件",required = true,dataType = "String"),
            @ApiImplicitParam(name = "user",value = "操作的用户",required = true,dataType = "SysUsers")
    })
    @RequestMapping(value = "/{id}/{fileUrl}/",method = RequestMethod.DELETE)
    public Object delRandomImg(@PathVariable("id") Integer id, @PathVariable("fileUrl") String fileUrl, SysUsers user){
        SysUsers sysUsers = HairoUtil.verifyUser(user);
        if(sysUsers == null || !sysUsers.getU_password().equals(sysUsers.getU_password())){
           return HairoUtil.result(3);
        }
        if(HairoUtil.permissionQuery(user.getU_id())) {
            if (randomImgService.removeRandomImgById(id, fileUrl) > 0) {
                return HairoUtil.result(1);
            } else {
                return HairoUtil.result(0);
            }
        }else{
            return HairoUtil.result(2);
        }
    }

    @ApiOperation(value = "获取所有随机图片",notes = "无权限")

    @RequestMapping(value = "/page/{page}/",method = RequestMethod.GET)
    public Object getAllRandomImg(@PathVariable("page") Integer page){
        if(page == null || page == 0 || "".equals(page)){
           return HairoUtil.result(-1);
        }
       return randomImgService.getAllRandomImg(page,HairoUtil.PAGESIZE);
    }




}
