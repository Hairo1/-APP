package com.Hairo.service;

import com.Hairo.pojo.MessageBoard;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional(rollbackFor = Exception.class)//统一事务
public interface MessageBoardService {

    public List<MessageBoard> getAllMessageBoard();

    public Integer addMessage(MessageBoard messageBoard);
}
