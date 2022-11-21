package com.example.webforum.business.services;

import com.example.webforum.business.IMessageService;
import com.example.webforum.business.bo.Message;
import com.example.webforum.db.dbo.MessageDb;
import com.example.webforum.db.dbo.UserDb;
import com.example.webforum.db.repositories.MessageRepository;
import com.example.webforum.db.repositories.UserRepository;
import com.example.webforum.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MessageService implements IMessageService {
    @Autowired
    private final MessageRepository messageRepository;
    @Autowired
    private final UserRepository userRepository;

    public MessageService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String sendMessage(Message message) {
        java.util.Date now = new Date();
        Timestamp timestamp = new Timestamp(now.getTime());
        UserDb sender = userRepository.getByUsername(message.getSender());
        UserDb receiver = userRepository.getByUsername(message.getReceiver());
        MessageDb messageDb = new MessageDb();
        messageDb.setSentDate(timestamp);
        messageDb.setContent(message.getContent());
        messageDb.setReceiver(receiver);
        messageDb.setSender(sender);
        messageRepository.save(messageDb);
        return "Successfully added!";
    }

    @Override
    public Message getMessageById(int id) {
        MessageDb messageDb = messageRepository.findById(id);
        Message message = new Message();
        message.setId(messageDb.getId());
        message.setSentDate(new Date(messageDb.getSentDate().getTime()));
        message.setTimeAgo(DateUtils.calculateTimeAgo(message.getSentDate()));
        message.setContent(messageDb.getContent());
        message.setReceiver(messageDb.getReceiver().getUsername());
        message.setSender(messageDb.getSender().getUsername());
        return message;
    }

    @Override
    public List<Message> findMessageByReceiver(String receiver) {
        UserDb receiverDb = userRepository.getByUsername(receiver);
        List<Message> messages = new ArrayList<>();
        Iterable<MessageDb> messageDbs = messageRepository.findMessageByReceiver(receiverDb);
        messageDbs.forEach(messageDb -> {
            Message message = new Message();
            message.setId(messageDb.getId());
            message.setSentDate(new Date(messageDb.getSentDate().getTime()));
            message.setTimeAgo(DateUtils.calculateTimeAgo(message.getSentDate()));
            message.setContent(messageDb.getContent());
            message.setReceiver(messageDb.getReceiver().getUsername());
            message.setSender(messageDb.getSender().getUsername());
            messages.add(message);
        });
        return messages;
    }
}
