package com.Hairo.controller;

import com.Hairo.pojo.Label;
import com.Hairo.pojo.SysUsers;
import com.Hairo.service.LabelService;
import com.Hairo.service.PermissionService;
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
 * 创建时间: 2019/4/16 15:24
 * 作用描述:
 * 标签云
 */
@Api(value = "标签Controller",tags = {"标签操作接口"})
@RestController
@RequestMapping("/api/label/")
public class  LabelController {

    @Autowired
    private LabelService labelService;

    @ApiOperation(value = "获取所有标签",notes = "获取所有标签,未开启分页,但预留了分页参数")
    @ApiImplicitParam(name = "state",value = "标签的状态(0不启用,1启用)",required = false,dataType = "Integer")
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public Object getAllLabel(Integer state){
        if(state == null || state <0 || state>1){
            state = 1;
        }
        return labelService.getAllLabel(state);
    }

    @ApiOperation(value = "获取指定标签数量",notes = "*")
    @ApiImplicitParam(name = "l_name",value = "标签昵称",required = true,dataType = "String")
    @RequestMapping(value = "/{l_name}/count/",method = RequestMethod.GET)
    public Object getLabelCountByName(@PathVariable("l_name") String l_name){
        return labelService.selectLabelCountByName(l_name);
    }


    /**
     * 修改标签,暂未开发
     * @param label
     * @param sysUsers
     * @return
     */
    @ApiOperation(value = "修改标签",notes = "未开发,已实现")
    @RequestMapping(value = "/",method = RequestMethod.PUT)
    private Object updateLabel(@PathVariable Label label, @PathVariable SysUsers sysUsers){
        SysUsers user = HairoUtil.verifyUser(sysUsers);
        if(user!=null && user.getU_password().equals(sysUsers.getU_password())){
            if(HairoUtil.permissionQuery(user.getU_id())){
                if(labelService.updateLabel(label)>0){
                    //操作成功
                    return HairoUtil.result(1);
                }else {
                    //操作失败
                    return HairoUtil.result(0);
                }
            }else {
                //权限不足
                return HairoUtil.result(2);
            }
        }else{
            return HairoUtil.result(3);
        }

    }


    @ApiOperation(value = "添加标签",notes = "只有登陆的用户且拥有管理权限才能操作")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "label",value = "标签实体",required = true,dataType = "Label"),
            @ApiImplicitParam(name = "sysUsers",value = "操作的管理员",required = true,dataType = "SysUsers")
    })
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Object addLabel(@PathVariable Label label,@PathVariable SysUsers sysUsers){
        if(label == null || label.getL_name()==null){
            //非法操作
            return HairoUtil.result(-1);
        }
        SysUsers user = HairoUtil.verifyUser(sysUsers);
        if(user == null || !user.getU_password().equals(sysUsers.getU_password())){
            return HairoUtil.result(3);
        }

        if(HairoUtil.permissionQuery(user.getU_id())){
            if(labelService.addLabel(label)>0){
                return HairoUtil.result(1);
            }else{
                return HairoUtil.result(0);
            }
        }else{
           return HairoUtil.result(2);
        }

    }

}
