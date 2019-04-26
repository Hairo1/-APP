package com.Hairo.mappers.messageBoardMapper;

import com.Hairo.pojo.MessageBoard;

import java.util.List;

public interface MessageBoardMapper {


    /**
     * 查询所有留言消息
     * @return
     */
    public List<MessageBoard> selectAllMessageBoard();

    /**
     * 插入留言信息
     * @param messageBoard
     * @return
     */
    public Integer insertMessage(MessageBoard messageBoard);
}
