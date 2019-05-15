package com.Hairo.service;

import com.Hairo.pojo.CommentInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional(rollbackFor = Exception.class)//统一事务
public interface CommentInfoService {

    /**
     * 获取所有留言信息
     * @return
     */
    public List<CommentInfo> getAllMessageBoardCommentInfo();

    /**
     * 获取指定位置下的评论信息
     * @param articleId
     * @return
     */
    public List<CommentInfo> getArticleCommentInfo(Integer articleId);

    public Integer insertCommentInfo(CommentInfo commentInfo);
    public Integer delCommentInfo(Integer commentId);

    public List<CommentInfo> getCommentInfoByText(String text);
}
