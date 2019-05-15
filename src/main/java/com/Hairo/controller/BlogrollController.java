package com.Hairo.controller;

import com.Hairo.pojo.Blogroll;
import com.Hairo.pojo.SysUsers;
import com.Hairo.service.BlogrollService;
import com.Hairo.util.HairoUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/4/18 2:38
 * 作用描述:
 * 友情链接控制器
 */
@Api(value = "友情链接controller",tags = {"友情链接操作接口"})
@RestController
@RequestMapping("/api/blogroll/")
public class BlogrollController {

    @Autowired
    private BlogrollService blogrollService;

    /**
     * 获取所有友情链接
     * @return
     */
    @ApiOperation(value = "获取友情链接",notes = "暂未开启分页,但预留了分页操作,待后期开发")
    @RequestMapping(method = RequestMethod.GET)
    public Object selectAllBlogroll(){
        return blogrollService.selectAllBlogrolls();
    }

    /**
     * 根据ID获取友情链接
     * @return
     */

    @ApiOperation(value = "获取指定友情链接",notes = "查看友情链接资料")
     @ApiImplicitParam(name = "blogrollId", value = "友情链接ID", required = true, dataType = "Integer")
    @RequestMapping(value = "/{blogrollId}/",method = RequestMethod.GET)
    public Object selectBlogrollById(@PathVariable("blogrollId") Integer blogrollId){
        return blogrollService.selectBlogrollById(blogrollId);
    }

    /**
     * 添加友情链接
     * @param blogroll
     * @return
     */
    @ApiOperation(value = "添加友情链接",notes = "无需权限和审核")
    @ApiImplicitParam(name = "blogroll", value = "友情链接实体", required = true, dataType = "Blogroll")
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Integer addBlogroll(@RequestParam("blogroll") Blogroll blogroll){
            return blogrollService.insertBlogroll(blogroll);
    }

    /**
     * 修改友情链接
     * @param blogroll
     * @return
     */
    @ApiOperation(value = "添加友情链接",notes = "只有管理员才能操作")
    @RequestMapping(value="/",method = RequestMethod.PUT)
    public Object updateBlogroll(@RequestParam("blogroll") Blogroll blogroll,@PathVariable SysUsers sysUsers){
        SysUsers user = HairoUtil.verifyUser(sysUsers);
        if(user == null || !user.getU_password().equals(sysUsers.getU_password())){
            //非法用户
            return HairoUtil.result(3);
        }
        if(HairoUtil.permissionQuery(user.getU_id())){
            if(blogrollService.updateBlogroll(blogroll)>0){
                return HairoUtil.result(1);
            }else{
                return HairoUtil.result(0);
            }
        }else{
            //权限不足
            return HairoUtil.result(2);
        }
    }

    /**
     * 删除友情链接
     * @param blogrollId
     * @return
     */
    @ApiOperation(value = "删除友情链接",notes = "只有管理员才能操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "blogrollId",value = "待删除的友情链接ID",required = true,dataType ="Integer" ),
            @ApiImplicitParam(name = "sysUsers",value = "操作的管理员",required = true,dataType ="SysUsers" )
    })
    @RequestMapping(method = RequestMethod.DELETE)
    public Object updateBlogroll(@RequestParam("blogrollId") Integer blogrollId, SysUsers sysUsers){
        SysUsers user = HairoUtil.verifyUser(sysUsers);
        if(user == null || !user.getU_password().equals(sysUsers.getU_password())){
            //非法用户
            return HairoUtil.result(3);
        }
        if(HairoUtil.permissionQuery(user.getU_id())){
            if(blogrollService.delectBlogrollById(blogrollId)>0){
                return HairoUtil.result(1);
            }else{
                return HairoUtil.result(0);
            }
        }else{
            //权限不足
            return HairoUtil.result(2);
        }
    }



}
