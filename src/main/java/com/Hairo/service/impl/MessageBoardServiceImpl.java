package com.Hairo.service.impl;

import com.Hairo.mappers.messageBoardMapper.MessageBoardMapper;
import com.Hairo.pojo.MessageBoard;
import com.Hairo.service.MessageBoardService;
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
public class MessageBoardServiceImpl implements MessageBoardService {

    @Autowired
    private MessageBoardMapper messageBoardMapper;
    @Override
    public List<MessageBoard> getAllMessageBoard() {
        return messageBoardMapper.selectAllMessageBoard();
    }

    @Override
    public Integer addMessage(MessageBoard messageBoard) {
        return messageBoardMapper.insertMessage(messageBoard);
    }
}
