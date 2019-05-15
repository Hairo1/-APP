package com.Hairo.controller;

import com.Hairo.pojo.ArticleReview;
import com.Hairo.pojo.SysUsers;
import com.Hairo.service.ArticleReviewService;
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

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/5/14 1:31
 * 作用描述:
 */
@Api(value = "文章评论controller",tags = {"文章评论操作接口"})
@RestController
@RequestMapping("/api/articleReview/")
public class ArticleReviewController {

    @Autowired
    private ArticleReviewService articleReviewService;
    @ApiOperation(value = "根据文章ID获取文章评论-每页显示15条评论",notes = "暂未开启分页,但预留了分页操作,待后期开发")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "获取第page页内容", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "articleId", value = "指定文章ID的评论", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/article/{articleId}/page{page}",method = RequestMethod.GET)
    public Object getArticleReview(@PathVariable("page") Integer page,@PathVariable("articleId") Integer articleId){
            if(page ==null || articleId ==0 || articleId <=9999){
                return HairoUtil.result(-1);
            }
            return articleReviewService.getArticleReview(page,articleId);
    }

    @ApiOperation(value = "根据评论用户昵称获取评论",notes = "主要用来删除评论")
    @ApiImplicitParam(name = "userName",value = "评论用户的昵称",required = true,dataType = "String")
    @RequestMapping(value = "/user/{userName}",method = RequestMethod.GET)
    public Object getArticleReviewUser(@PathVariable("userName") String userName){
        if(userName == null || "".equals(userName) ){
            return HairoUtil.result(-1);
        }
        return articleReviewService.getArticleReviewUser(userName);
    }

    @ApiOperation(value = "文章添加评论",notes = "根据实体POJO的文章ID添加到指定文章评论")
    @ApiImplicitParam(name = "articleReview",value = "文章实体POJO",required = true,dataType = "ArticleReview")
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Object addArticleReview(ArticleReview articleReview){
        if(articleReview == null || articleReview.getReviewUserName()==null ||articleReview.getArticleId() == null || articleReview.getArticleId()<=9999){
            return HairoUtil.result(-1);
        }
        return HairoUtil.result(articleReviewService.addArticleReview(articleReview));
    }


    @ApiOperation(value = "删除文章评论",notes = "只有管理员才能进行删除评论操作,根据文章评论ID进行删除")
    @ApiImplicitParams(
            {
                  @ApiImplicitParam (name = "reviewId",value = "评论文章的自增ID",required = true,dataType = "Integer"),
                    @ApiImplicitParam (name = "sysUsers",value = "管理员信息",required = true,dataType = "SysUsers")
            }
    )
    @RequestMapping(value = "/",method = RequestMethod.DELETE)
    public  Object delArticleReview(@PathVariable("reviewId") Integer reviewId, SysUsers sysUsers){
        SysUsers user = HairoUtil.verifyUser(sysUsers);

        if(reviewId == null ||  user == null || !user.getU_password().equals(sysUsers.getU_password())){
            return HairoUtil.result(3);
        }
        if(HairoUtil.permissionQuery(user.getU_id())){
            return HairoUtil.result(articleReviewService.delArticleReview(reviewId));
        }
        return HairoUtil.result(2);
    }
}
