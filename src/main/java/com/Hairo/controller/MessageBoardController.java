package com.Hairo.controller;

import com.Hairo.pojo.MessageBoard;
import com.Hairo.service.MessageBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项目名称： Hairo
 * 作 者   ： Hairo
 * 创建时间: 2019/4/26 16:25
 * 作用描述:
 */
@RestController
@RequestMapping("/api/messageBoard/")
public class MessageBoardController {


    @Autowired
    private MessageBoardService messageBoardService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public Object getAllMessageBoard(){
        return messageBoardService.getAllMessageBoard();
    }


    @RequestMapping(value = "/",method = RequestMethod.POST)
    public Object insertMessage(MessageBoard messageBoard){
        return messageBoardService.addMessage(messageBoard);
    }

}
