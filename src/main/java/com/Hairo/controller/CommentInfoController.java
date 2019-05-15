package com.Hairo.controller;

import com.Hairo.pojo.CommentInfo;
import com.Hairo.pojo.SysUsers;
import com.Hairo.service.CommentInfoService;
import com.Hairo.util.HairoUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/4/26 16:25
 * 作用描述:
 */

@RestController
@RequestMapping("/api/comment/")
public class CommentInfoController {


    @Autowired
    private CommentInfoService commentInfoService;


    @RequestMapping(value = "/messageBoard/",method = RequestMethod.GET)
    public Object getAllMessageBoardCommentInfo(){
        return commentInfoService.getAllMessageBoardCommentInfo();
    }


    @ApiOperation(value = "添加留言",notes = "所有用户都可以添加")
    @ApiImplicitParam(name = "commentInfo",value = "留言实体",required = true,dataType = "CommentInfo")
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Object insertCommentInfo(CommentInfo commentInfo){
        if(commentInfo==null){
            return HairoUtil.result(-1);
        }
        if(commentInfo.getM_articleId()==null||commentInfo.getM_articleId().equals("")){
            commentInfo.setM_articleId(0);
        }
        return commentInfoService.insertCommentInfo(commentInfo);
    }

    @RequestMapping(value = "/article/{articleId}/",method = RequestMethod.GET)
    public Object getArticleCommentInfoa(@PathVariable("articleId") Integer articleId){
        if(articleId== null || articleId==0){

            return HairoUtil.result(-1);
        }
        return commentInfoService.getArticleCommentInfo(articleId);
    }

    @RequestMapping(value = "/{commentId}",method = RequestMethod.DELETE)
    public Object delCommentInfo(@PathVariable("commentId") Integer commentId,@PathVariable SysUsers sysUsers){
        if(commentId==null || commentId ==0){
            return HairoUtil.result(-1);
        }
        SysUsers user = HairoUtil.verifyUser(sysUsers);
        if(user == null || !user.getU_password().equals(sysUsers.getU_password())){
            return HairoUtil.result(3);
        }else{
            if(HairoUtil.permissionQuery(sysUsers.getU_id())){
                if(commentInfoService.delCommentInfo(commentId)>0){
                    return HairoUtil.result(1);
                }else {
                    return HairoUtil.result(0);
                }
            }else {
                return HairoUtil.result(2);
            }
        }
    }
    @RequestMapping(value = "/{text}",method = RequestMethod.GET)
    public Object selectCommentInfo(@PathVariable("text") String text){
        if(text == null || "".equals(text)){
            return null;
        }
        return commentInfoService.getCommentInfoByText(text);
    }
}
