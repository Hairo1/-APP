package com.Hairo.controller;

import com.Hairo.pojo.Articles;
import com.Hairo.service.ArticlesService;
import com.Hairo.service.LabelService;
import com.Hairo.service.PublicService;
import com.Hairo.service.SysUserService;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/4/12 18:28
 * 作用描述:
 * 文章控制器
 */

@RestController
@RequestMapping("/api/article/")
public class ArticlesController {
    @Autowired
    private ArticlesService articlesService;
    @Autowired
    private  SysUserService sysUserService;
    @Autowired
    private LabelService labelService;
    @Autowired
    private PublicService publicService;
    private final Integer DEFAULT_PAGESIZE = 15;//默认每页显示15条数据
    /**
     * 获取所有文章
     * 分页
     * @param page 当前页数 每页显示15条
     * @param response
     * @return
     */
    @RequestMapping(value = "/page/{page}/", method = RequestMethod.GET)
    public Object getAllArticles( HttpServletResponse response,@PathVariable("page") Integer page){

        return  articlesService.selectAllArticles(page, DEFAULT_PAGESIZE);
    }

    /**
     * 获取指定文章作者下的文章
     * @param response
     * @param author 作者名称
     * @param page 分页
     * @return
     */
    @RequestMapping(value = "/author/{author}/page/{page}/", method = RequestMethod.GET)
    public Object getArticlesByAuthor( HttpServletResponse response,@PathVariable("author") String author ,@PathVariable("page") Integer page){
        return articlesService.selectAllArticlesByAuthor(author,page ,DEFAULT_PAGESIZE);
    }





    /**
     * 获取所有文章数量
     * @return
     */
    @RequestMapping(value = "/count/", method = RequestMethod.GET)
    public Object getArticleCount(){
        return articlesService.selectArticleCount();
    }
    /**
     * 获取指定作者下的所有文章数量
     * @return
     */
    @RequestMapping(value = "/author/{author}/count/", method = RequestMethod.GET)
    public Object getArticleCountByAuthor(@PathVariable("author") String author){
        return articlesService.selectArticleCountByAuthor(author);
    }

    /**
     * 获取指定标签下的所有文章数量
     * @return
     */
    @RequestMapping(value = "/label/{label}/page/{page}/", method = RequestMethod.GET)
    public Object getArticleCountByLabel(@PathVariable("label") String label,@PathVariable("page") Integer page){

        return articlesService.selectAllArticlesByLabel(label,page,DEFAULT_PAGESIZE);
    }

    /**
     * 根据文章ID获取指定文章
     * @param response
     * @param articleId
     * @return
     */
    @RequestMapping(value = "/{articleId}/",method = RequestMethod.GET)
    public Object detail( HttpServletResponse response,@PathVariable("articleId") Integer articleId){
        //文章浏览数+1
       // publicService.articleBrowsePlus1(articleId);
        return articlesService.selectArticlesById(articleId);
    }

    /**
     * 添加文章
     * @param response
     * @param article
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Object insert( HttpServletResponse response, Articles article){
        return articlesService.insertArticle(article);
    }


    /**
     * 获取热门文章
     * @return
     */
    @RequestMapping(value = "/hot/",method = RequestMethod.GET)
    public Object getHotArticle(){
        return publicService.getHotArticle();
    }

    /**
     * 获取随机文章
     * @return
     */
    @RequestMapping(value = "/random/",method = RequestMethod.GET)
    public Object randomArticle(){
        return publicService.getRandomArticle();
    }

}
