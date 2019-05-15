package com.Hairo.service.impl;

import com.Hairo.mappers.commentInfoMapper.CommentInfoMapper;
import com.Hairo.pojo.CommentInfo;
import com.Hairo.service.CommentInfoService;
import com.Hairo.service.RandomImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/4/26 16:24
 * 作用描述:
 */
@Service
public class CommentInfoServiceImpl implements CommentInfoService {

    @Autowired
    private CommentInfoMapper commentInfoMapper;
    @Autowired
    private RandomImgService randomImgService;

    @Override
    public List<CommentInfo> getAllMessageBoardCommentInfo() {
        return commentInfoMapper.selectAllMessageBoardCommentInfo();
    }


    @Override
    public List<CommentInfo> getArticleCommentInfo(Integer articleId) {
        if(articleId == null || articleId == 0){
            return null;
        }
        return commentInfoMapper.selectArticleComments(articleId);
    }

    /**
     * 插入留言信息,文章ID为空则插入到留言板块
     * @param commentInfo
     * @return
     */
    @Override
    public Integer insertCommentInfo(CommentInfo commentInfo) {
        //获取随机图片
        commentInfo.setM_portraitUrl(randomImgService.selectRandomImg(0));
        return commentInfoMapper.insertCommentInfo(commentInfo);
    }

    @Override
    public Integer delCommentInfo(Integer commentId) {
        if(commentId == null || commentId == 0){
            return 0;
        }
        return commentInfoMapper.delCommentInfo(commentId);
    }

    @Override
    public List<CommentInfo> getCommentInfoByText(String text) {
        if(text == null || text.equals("")||text.length()<1){
            return null;
        }
        return commentInfoMapper.getCommentInfoByText(text);
    }
}
