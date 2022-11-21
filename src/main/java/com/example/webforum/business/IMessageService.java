package com.example.webforum.business;

import com.example.webforum.business.bo.Message;

import java.util.List;

public interface IMessageService {

    String sendMessage(Message message);
    Message getMessageById(int id);
    List<Message> findMessageByReceiver(String receiver);
}
