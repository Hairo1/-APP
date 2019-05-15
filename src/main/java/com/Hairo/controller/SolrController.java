package com.Hairo.controller;

import com.Hairo.pojo.Articles;
import com.Hairo.util.HairoUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.HighlightOptions;
import org.springframework.data.solr.core.query.SimpleFilterQuery;
import org.springframework.data.solr.core.query.SimpleHighlightQuery;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/5/5 18:37
 * 作用描述:
 */
@Api(value = "solr-Controller",tags = "搜索访问接口")
@RestController
@RequestMapping("/api/search/")
public class SolrController {

    @Autowired
    private SolrClient solrClient;
     @ApiOperation(value = "solr数据搜索")
     @ApiImplicitParams({
             @ApiImplicitParam(name = "q",value = "查询的关键字",required = true,dataType = "String"),
             @ApiImplicitParam(name = "page",value = "分页",required = true,dataType = "Integer")
     })
    @RequestMapping(value = "/{q}/{page}", method = RequestMethod.GET)
    public Object getSearchResult (@PathVariable("q") String q, @PathVariable("page") Integer page) throws IOException, SolrServerException {

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.set("q", "articleTitle:" + q + " OR articleContent:" + q);//关键字搜索
        solrQuery.setHighlight(true);//开启高亮显示
        solrQuery.addHighlightField("articleTitle");//高亮字段
        solrQuery.addHighlightField("articleContent");//高亮字段
//        solrQuery.setParam("hl.fl", "articleTitle");
        solrQuery.setHighlightSimplePre("<span style='color:red'>");//前缀
        solrQuery.setHighlightSimplePost("</span>");//后缀
        solrQuery.addSort("articlePubDate", SolrQuery.ORDER.desc);//根据发布时间排序
        /**
         * hl.snippets
         * hl.snippets参数是返回高亮摘要的段数，因为我们的文本一般都比较长，含有搜索关键字的地方有多处，如果hl.snippets的值大于1的话，
         * 会返回多个摘要信息，即文本中含有关键字的几段话，默认值为1，返回含关键字最多的一段描述。solr会对多个段进行排序。
         * hl.fragsize
         * hl.fragsize参数是摘要信息的长度。默认值是100，这个长度是出现关键字的位置向前移6个字符，再往后100个字符，取这一段文本。
         */
        solrQuery.setHighlightFragsize(200);
        solrQuery.setStart((page-1)*HairoUtil.PAGESIZE);
        solrQuery.setRows(HairoUtil.PAGESIZE);
        QueryResponse response = solrClient.query(solrQuery);//高量字段

        SolrDocumentList results = response.getResults();//普通结果

        Map<String, Map<String, List<String>>> highlightresult = response.getHighlighting();
       List arrayList = new ArrayList();
        for (SolrDocument solrDocument : results) {
            String id = solrDocument.get("id") + "";
            String articleTitle = "";
            System.out.println("id：" + id);
            List<String> list = highlightresult.get(id).get("articleTitle");
            if (list != null && list.size() > 0) {
                articleTitle = list.get(0);

            } else {
                articleTitle = solrDocument.get("articleTitle").toString();
            }
            String articleContent = "";
            List<String> content = highlightresult.get(id).get("articleContent");
            if (content != null && content.size() > 0) {
                articleContent = content.get(0);
            } else {
                articleContent = solrDocument.get("articleContent").toString();
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("a_title",articleTitle);
            jsonObject.put("a_content",articleContent);
            jsonObject.put("u_name",solrDocument.get("articleAuthor").toString());
            jsonObject.put("a_likeCount",solrDocument.get("articleLikeCount").toString());
            jsonObject.put("a_browseCount",solrDocument.get("articleBrowseCount").toString());
            jsonObject.put("a_id",solrDocument.get("articleId").toString());
            jsonObject.put("a_commentCount",solrDocument.get("articleCommentCount").toString());
            jsonObject.put("l_name",solrDocument.get("articleLabelName").toString());
            jsonObject.put("a_pubDate",solrDocument.get("articlePubDate").toString());
            jsonObject.put("count", results.getNumFound());
           arrayList.add(jsonObject);
        }
        return arrayList;
    }

    @ApiOperation(value = "获取搜索关键词的结果数量")
    @ApiImplicitParam(name = "q",value = "搜索的关键字",required = true,dataType ="String" )
    @RequestMapping(value = "/{q}/count", method = RequestMethod.GET)
    public long getSearchResultCount(@PathVariable("q") String q) throws IOException, SolrServerException {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.set("q", "articleTitle:" + q + " OR articleContent:" + q);//关键字搜索
        QueryResponse response = solrClient.query(solrQuery);//高量字段
        SolrDocumentList results = response.getResults();//普通结果
        return  results.getNumFound();
    }


}
