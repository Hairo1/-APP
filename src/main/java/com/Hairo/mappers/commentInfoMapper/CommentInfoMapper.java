package com.Hairo.mappers.commentInfoMapper;

import com.Hairo.pojo.CommentInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentInfoMapper {


    /**
     * 查询所有留言消息
     * @return
     */
    public List<CommentInfo> selectAllMessageBoardCommentInfo();


    /**
     * 查询指定文章评论列表
     * @return
     */
    public List<CommentInfo> selectArticleComments(@Param("articleId") Integer articleId);

    /**
     * 插入评论信息
     * @param commentInfo
     * @return
     */
    public Integer insertCommentInfo(CommentInfo commentInfo);

    public Integer delCommentInfo(Integer commentId);

    public List<CommentInfo> getCommentInfoByText(@Param("text") String text);
}
