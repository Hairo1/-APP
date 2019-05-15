package com.Hairo.controller;

import com.Hairo.pojo.Articles;
import com.Hairo.pojo.SysUsers;
import com.Hairo.service.*;
import com.Hairo.util.HairoUtil;
import io.swagger.annotations.*;
import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/4/12 18:28
 * 作用描述:
 * 文章控制器
 */
@Api(value = "文章controller",tags = {"文章操作接口"})
@RestController
@RequestMapping("/api/article/")
@SuppressWarnings("all")
public class ArticlesController {
    @Autowired
    private ArticlesService articlesService;
    @Autowired
    private  SysUserService sysUserService;
    @Autowired
    private LabelService labelService;
    @Autowired
    private TimedTaskService timedTaskService;
    @Autowired
    private SolrClient solrClient;
    @Autowired
    private PermissionService permissionService;
    private final Integer DEFAULT_PAGESIZE = 15;//默认每页显示15条数据

    /**
     * 获取所有文章
     * 分页
     * @param page 当前页数 每页显示15条
     * @param response
     * @return
     */
    @ApiOperation(value = "获取所有文章",notes = "获取所有文章,每页显示15条数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "获取第page页数据",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "state",value = "文章状态(0审核 1通过)",dataType = "Integer",required = true)
    })
    @RequestMapping(value = "/page/{page}/", method = RequestMethod.GET)
    public Object getAllArticles( HttpServletResponse response,@PathVariable("page") Integer page,Integer state){
        if(state == null || state < 0 || state > 1 ){
            state = 1;
        }
        return  articlesService.selectAllArticles(page,state);
    }

    /**
     * 获取指定文章作者下的文章
     * @param response
     * @param author 作者名称
     * @param page 分页
     * @return
     */
    @ApiOperation(value = "获取指定作者下的所有文章",notes = "获取指定作者的文章,每页显示15条数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "获取第page页数据",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "author",value = "作者昵称",dataType = "String",required = true)
    })
    @RequestMapping(value = "/author/{author}/page/{page}/", method = RequestMethod.GET)
    public Object getArticlesByAuthor( HttpServletResponse response,@PathVariable("author") String author ,@PathVariable("page") Integer page){
        return articlesService.selectAllArticlesByAuthor(author,page);
    }

    /**
     * 获取所有文章数量
     * @return
     */
    @ApiOperation(value = "获取文章的总数量",notes = "分页")
    @RequestMapping(value = "/count/", method = RequestMethod.GET)
    public Object getArticleCount(){
        return articlesService.selectArticleCount();
    }

    /**
     * 获取指定作者下的所有文章数量
     * @return
     */
    @ApiOperation(value = "获取指定作者下的文章总数量",notes = "用于分页")
    @ApiImplicitParam(name = "author",value = "作者昵称",dataType = "String",required = true)
    @RequestMapping(value = "/author/{author}/count/", method = RequestMethod.GET)
    public Object getArticleCountByAuthor(@PathVariable("author") String author){
        return articlesService.selectArticleCountByAuthor(author);
    }


    /**
     * 获取指定标签下的所有文章
     * @return
     */
    @ApiOperation(value = "获取指定标签下的所有文章",notes = "获取指定标签的文章,每页显示15条数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "获取第page页数据",dataType = "Integer",required = true),
            @ApiImplicitParam(name = "label",value = "作者昵称",dataType = "String",required = true)
    })
    @RequestMapping(value = "/label/{label}/page/{page}/", method = RequestMethod.GET)
    public Object getArticleByLabel(@PathVariable("label") String label,@PathVariable("page") Integer page){
        return articlesService.selectAllArticlesByLabel(label,page);
    }


    /**
     * 获取指定标签下的所有文章数量
     * @return
     */
    @ApiOperation(value = "获取指定标签下的文章总数量",notes = "用于分页")
    @ApiImplicitParam(name = "label",value = "标签昵称",dataType = "String",required = true)
    @RequestMapping(value = "/label/{label}/count/", method = RequestMethod.GET)
    public Object getArticleCountByLabel(@PathVariable("label") String label){
        return articlesService.selectArticleCountByLabel(label);
    }

    /**
     * 根据文章ID获取指定文章
     * @param response
     * @param articleId
     * @return
     */
    @ApiOperation(value = "查看文章详情",notes = "根据文章ID查看文章详情信息")
    @ApiImplicitParam(name = "articleId",value = "文章Id",dataType = "Integer",required = true)
    @RequestMapping(value = "/{articleId}/",method = RequestMethod.GET)
    public Object detail( HttpServletResponse response,@PathVariable("articleId") Integer articleId){

        Object article= articlesService.selectArticlesById(articleId);
        if(article==null || "".equals(article)){
            return "404";
        }
        //文章浏览数+1
        articlesService.addArticleBrowse(articleId);
        return article;
    }

    /**
     * 添加文章
     * @param response
     * @param article
     * @return
     */
    @ApiOperation(value = "添加文章",notes = "只有登陆的用户才能添加文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "article",value = "文章实体",dataType = "Articles",required = true),
            @ApiImplicitParam(name = "sysUsers",value = "添加文章的用户实体",dataType = "SysUsers",required = true)
    })
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Object insert( HttpServletResponse response,@PathVariable Articles article,@PathVariable  SysUsers sysUsers){
        SysUsers user = HairoUtil.verifyUser(sysUsers);
        if(sysUsers==null || !sysUsers.getU_password().equals(user.getU_password())){
            return HairoUtil.result(2);
        }
        if(articlesService.insertArticle(article)>0){
            labelService.updateLabelCount(article.getL_name(),1);
            return HairoUtil.result(1);
        }else {
            return HairoUtil.result(0);
        }
    }

    /**
     * 修改文章
     * @param articles 文章pojo
     * @param sysUsers 修改文章的用户
     * @return
     */
    @ApiOperation(value = "修改文章",notes = "只有登陆的用户才能修改文章,且拥有管理权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "article",value = "文章实体",dataType = "Articles",required = true),
            @ApiImplicitParam(name = "sysUsers",value = "修改文章的用户实体",dataType = "SysUsers",required = true)
    })
    @RequestMapping(value = "/",method = RequestMethod.PUT)
    public Object updateArticles(@PathVariable Articles articles,@PathVariable SysUsers sysUsers){
        SysUsers user = HairoUtil.verifyUser(sysUsers);
        if(user!=null && user.getU_password().equals(sysUsers.getU_password())){
            if(articles == null || articles.getA_id()==null || articles.getL_name()==null||articles.getA_content()==null){
                //非法请求
                return  HairoUtil.result(-1);
            }else{
                //判断权限
                if(HairoUtil.permissionQuery(user.getU_id())){
                    if(articlesService.updateActicle(articles)>0){
                        //操作成功
                        return  HairoUtil.result(1);
                    }else{
                        //操作失败
                        return  HairoUtil.result(0);
                    }
                }else{
                    //权限不足
                    return  HairoUtil.result(2);
                }
            }
        }else{
            //非法用户
            return  HairoUtil.result(3);
        }

    }

    /**
     * 删除文章
     * @param articles 文章pojo
     * @param sysUsers 删除文章的用户
     * @return
     */
    @ApiOperation(value = "删除文章",notes = "只有登陆的用户才能删除文章,且拥有管理权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "article",value = "文章实体",dataType = "Articles",required = true),
            @ApiImplicitParam(name = "articleId",value = "文章ID",dataType = "Integer",required = true)
    })
    @RequestMapping(value = "/{articleId}",method = RequestMethod.DELETE)
    public Object delARticles(@PathVariable("articleId") Integer articleId,@PathVariable SysUsers sysUsers){
        SysUsers user= HairoUtil.verifyUser(sysUsers);
        if(user!=null &&user.getU_password().equals(sysUsers.getU_password())){
            if(articleId == null || articleId<=9999){
                //非法请求
                return  HairoUtil.result(-1);
            }else{
                //判断权限
                if(HairoUtil.permissionQuery(user.getU_id())){
                    Object o =articlesService.selectArticlesById(articleId);
                    if(articlesService.delectActicle(articleId)>0){
                        if(o!=null){
                            Articles article = (Articles)o;
                            labelService.updateLabelCount(((Articles) o).getL_name(),-1);
                        }
                        //操作成功
                        return  HairoUtil.result(1);
                    }else{
                        //操作失败
                        return  HairoUtil.result(0);
                    }
                }else{
                    //权限不足
                    return  HairoUtil.result(2);
                }
            }
        }else {
            //非法用户
            return HairoUtil.result(3);
        }
    }

    /**
     * 获取热门文章
     * @return
     */
    @ApiOperation(value = "获取热门文章",notes = "获取浏览量和评论最高的前8编文章作为热门文章")
    @RequestMapping(value = "/hot/",method = RequestMethod.GET)
    public Object getHotArticle(){
        return  articlesService.getHotArticle();
    }

    /**
     * 获取随机文章
     * @return
     */
    @ApiOperation(value = "获取随机文章",notes = "随机获取8编文章,已优化")
    @RequestMapping(value = "/random/",method = RequestMethod.GET)
    public Object randomArticle(){
     return articlesService.getRandomArticle();
    }


    /**
     * 文章点赞
     * @param articleId 文章ID
     * @param iP 根据IP判定不能重复点赞(48小时内)
     * @return
     */
    @ApiOperation(value = "文章点赞",notes = "每个用户每48小时内同一个IP和同一文章只能点赞一次")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleId",value = "文章ID",required = true,dataType ="Integer" ),
            @ApiImplicitParam(name = "iP",value = "客户端IP",required = true,dataType ="String" )
    })
    @RequestMapping(value = "/articlePraise/{articleId}/{ip}/",method = RequestMethod.PUT)
    public Object articlePraise(@PathVariable("articleId") Integer articleId, @PathVariable("ip") String iP){
        Map map = new HashMap();
        if(articleId == null || iP==null){
            return HairoUtil.result(-1);
        }
        if( articlesService.updateArticlePraise(articleId,iP)>0){
            map.put("success",1);
            map.put("message","点赞成功,感谢您的👍");
        }else{
            map.put("success",0);
            map.put("message","您已经对该文章点过赞啦 😄😊");
        }
        return map;
    }


}
