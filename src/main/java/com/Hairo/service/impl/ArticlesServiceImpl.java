package com.Hairo.service.impl;

import com.Hairo.mappers.articlesMapper.ArticlesMapper;
import com.Hairo.pojo.Articles;
import com.Hairo.service.ArticlesService;
import com.Hairo.service.LabelService;
import com.Hairo.service.RandomImgService;
import com.Hairo.service.RedisService;
import com.Hairo.util.HairoUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/4/12 18:19
 * 作用描述:
 */
@Service
@SuppressWarnings("all")
public class ArticlesServiceImpl implements ArticlesService {
    private static Logger log = Logger.getLogger(ArticlesServiceImpl.class);
    @Autowired
    private ArticlesMapper articlesMapper;
    @Autowired
    private RedisService redisService;
    @Autowired
    private LabelService labelService;
    @Autowired
    private RandomImgService randomImgService;
    /**
     * 从MYSQL中获取前5页数据缓存到Redis
     * 如大于第五页则从数据库中获取
     * @param page 页数(page * 15/条)
     * @return
     */
    @Override
    public Object selectAllArticles(Integer page,Integer state) {
        if(page == 0 || page == null )return null;
        //页面前5页数据从Redis中获取
        if(page <= 5 ){
            outLogger("从Redis中获取文章列表从第"+(page*HairoUtil.PAGESIZE-HairoUtil.PAGESIZE)+"条开始获取"+HairoUtil.PAGESIZE+"条");
            Object o =redisService.getHValue("ARTICLE_LIST_HKEY","ARTICLE_PAGE_"+page);
            if(o == null || "".equals(o)){
                outLogger("从Redis中获取文章列表为空,从MYSQL中获取文章,从第"+(page*HairoUtil.PAGESIZE-HairoUtil.PAGESIZE)+"条开始获取"+HairoUtil.PAGESIZE+"条");
                o =articlesMapper.selectAllArticles((page-1)*HairoUtil.PAGESIZE,HairoUtil.PAGESIZE,state);
            }
            return o;
        }else {
            outLogger("从MYSQL中获取文章,从第"+(page*HairoUtil.PAGESIZE-HairoUtil.PAGESIZE)+"条开始获取"+HairoUtil.PAGESIZE+"条");
            return articlesMapper.selectAllArticles((page-1)*HairoUtil.PAGESIZE,HairoUtil.PAGESIZE,state);
        }
    }

    /**
     * 根据文章ID从Redis获取数据,如果为空则从数据库获取,缓存至Redis(24小时过期),
     * @param articleId 文章ID
     * @return
     */
    @Override
    public Object selectArticlesById(Integer articleId) {
        if(articleId <= 9999 || articleId == null || "".equals(articleId)){
            return null;
        }
        outLogger("从Redis获取文章详情数据,文章id："+articleId);
        Object o = redisService.getValue("ArticleInfo_"+articleId);
        if(o == null || "".equals(o)){
            outLogger("从Redis获取文章ID为:"+articleId+"的详情数据为空,去数据库获取缓存至Redis");
            o =  articlesMapper.selectArticlesById(articleId);
            redisService.set("ArticleInfo_"+articleId,o,60*60);
        }
        return o;
    }

    /**
     * 从Redis中获取指定作者下的文章
     * @param author 作者
     * @return
     */
    @Override
    public Object selectAllArticlesByAuthor(String author,Integer page) {
        if (author==null || page==null || page==0 || "".equals(author)){
            return null;
        }
        Object authorArticles = redisService.getValue(author+"_"+page);
        Object authorArticleSum =  redisService.getValue(author+"_ARTICLE_SUM");
        if(authorArticles == null || "".equals(authorArticles)){
            authorArticles =  articlesMapper.selectAllArticlesByAuthor(author,HairoUtil.PAGESIZE*(page-1),HairoUtil.PAGESIZE);
            redisService.set(author+"_"+page,authorArticles,60*60*24);
            if(authorArticleSum ==null || "".equals(authorArticleSum)){
                redisService.set(author+"_ARTICLE_SUM",articlesMapper.selectArticleCountByAuthor(author));
            }
        }
        return authorArticles;
    }

    /**
     * 获取指定标签下的文章列表
     * @param label 标签
     * @param page
     * @return
     */
    @Override
    public Object selectAllArticlesByLabel(String label, Integer page) {
        if (label==null || page==null || page==0 || "".equals(label)){
            return null;
        }
        Object labelArticles = redisService.getValue(label+"_"+page);
        Object labelArticleSum =  redisService.getValue(label+"_ARTICLE_SUM");
        if(labelArticles == null || "".equals(labelArticles)){
            labelArticles =  articlesMapper.selectAllArticlesByLabel(label,HairoUtil.PAGESIZE*(page-1),HairoUtil.PAGESIZE);
            redisService.set(label+"_"+page,labelArticles,60*60*24);
            if(labelArticleSum ==null || "".equals(labelArticleSum)){
                redisService.set(label+"_ARTICLE_SUM",articlesMapper.selectArticleCountByLabel(label));
            }
        }
        return labelArticles;
    }

    /**
     * 获取文章总数量
     * @return
     */
    @Override
    public Integer selectArticleCount() {
        Object o = redisService.getValue("ARTICLE_SUM");
        if (o == null || "".equals(o)){
            o =articlesMapper.selectArticleCount();
        }
        return Integer.valueOf(o.toString());
    }

    /**
     * 获取指定作者的文章数量
     * @param author
     * @return
     */
    @Override
    public Integer selectArticleCountByAuthor(String author) {
        if (author == null || "".equals(author)){
            return 0;
        }
        Object o = redisService.getValue(author+"_ARTICLE_SUM");
        if (o == null || "".equals(o)){
            o = articlesMapper.selectArticleCountByAuthor(author);
            redisService.set(author+"_ARTICLE_SUM",o,60*60*2);
        }
        return Integer.valueOf(o.toString());
    }

    /**
     * 获取指定标签的文章数量
     * @param label
     * @return
     */
    @Override
    public Integer selectArticleCountByLabel(String label) {
        if (label == null || "".equals(label)){
            return 0;
        }
        Object o = redisService.getValue(label+"_ARTICLE_SUM");
        if (o == null || "".equals(o)){
            o = articlesMapper.selectArticleCountByLabel(label);
            redisService.set(label+"_ARTICLE_SUM",o,60*60*2);
        }
        return Integer.valueOf(o.toString());
    }

    /**
     * 获取热门文章
     * @return
     */
    @Override
    public Object getHotArticle() {
        Object o = redisService.getValue("HotArticle");
        if(o == null || "".equals(o)){
            o = articlesMapper.selectArticleHot8();
            redisService.set("HotArticle",o,60*60*24);
        }
        return o;
    }

    /**
     * 获取随机文章
     * @return
     */
    @Override
    public Object getRandomArticle() {
        Object o = redisService.getValue("RandomArticle");
        if(o == null || "".equals(o)){
            o = articlesMapper.selectRandomArticle();
            redisService.set("RandomArticle",o,60*60*24);
        }
        return o;
    }

    /**
     * 插入文章，重新在数据库中获取第一页数据覆盖Redis缓存中的第一页,以便显示新增的文章
     * @param article
     * @return
     */
    @Override
    public Integer insertArticle(Articles article) {
        if(article == null){
            return 0;
        }
        //获取文章随机图片
        article.setA_articleImgUrl(randomImgService.selectRandomImg(1));
        if(articlesMapper.insertArticle(article)>0){
            redisService.put("ARTICLE_LIST_HKEY","ARTICLE_PAGE_1",articlesMapper.selectAllArticles(0,HairoUtil.PAGESIZE,1));
            return 1;
        }
        return 0;
    }

    /**
     * 删除文章
     * @param articleId
     * @return
     */
    @Override
    public Integer delectActicle(Integer articleId) {
        if(articleId==null || articleId <= 9999){return 0;}
        if(articlesMapper.delectActicle(articleId)>0){
            redisService.delectKey("ArticleInfo_"+articleId);
            return 1;
        }

        return 0;
    }

    /**
     * 修改文章
     * @param article
     * @return
     */
    @Override
    public Integer updateActicle(Articles article) {
        if(article==null){return 0;}
        if(articlesMapper.updateActicle(article)>0){
            redisService.delectKey("ArticleInfo_"+article.getA_id());
            return 1;
        }

        return 0;
    }

    /**
     * 文章点赞
     * @param articleId 文章ID
     * @param iP 客户端ID(48小时内只能点击一次赞)
     * @return
     */
    @Override
    public Integer updateArticlePraise(Integer articleId,String iP) {
        //IP不正确|文章ID有误
        if(articleId == null || articleId <=9999 || HairoUtil.isIP(iP)==false){
            return -1;
        }
        Object o = redisService.getValue(articleId + ":" + iP);
        //48小时内已经对当前文章点过赞
        if(o != null){
            return 0;
        }
        //文章点赞更新
        articlesMapper.updateArticlePraise(articleId);
        //记录点赞文章和IP,保存48小时60*60*48
        redisService.set(articleId+":"+iP, 1,60*60*48);
        //根据文章ID移除在Redis中的文章
        redisService.delectKey("ArticleInfo_"+articleId);
        return 1;
    }

    /**
     * 添加文章浏览量
     * @param articleId 文章ID
     * @return
     */
    @Override
    public Integer addArticleBrowse(Integer articleId) {
        if(articleId==null || articleId <=9999){return 0;}
        Object o =  redisService.getHValue("ARTICLE_BROWSE",articleId);
        if(o == null){
            redisService.put("ARTICLE_BROWSE",articleId,1);
        }else{
            redisService.put("ARTICLE_BROWSE",articleId,Integer.valueOf(o.toString())+1);
        }
        return 1;
    }

    /**
     * 批量更新文章浏览量
     * @param map 文章ID
     * @return
     */
    @Override
    public Integer updateArticleBrowse(Map<Object,Object> map) {
        return articlesMapper.updateArticlePageView(map);
    }

    /**
     * 输出日志
     * @param text 日志内容
     */
    private  void outLogger(String text){
        log.info("\n\n******************************************"+text+"******************************************\n");
    }
}
