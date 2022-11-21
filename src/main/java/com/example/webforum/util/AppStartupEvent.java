package com.example.webforum.util;

import com.example.webforum.business.IPostService;
import com.example.webforum.db.dbo.PostDb;
import com.example.webforum.db.repositories.MessageRepository;
import com.example.webforum.db.repositories.PostRepository;
import com.example.webforum.db.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PostRepository postRepository;
    @Autowired
    private final MessageRepository messageRepository;

    public AppStartupEvent(UserRepository userRepository, PostRepository postRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.messageRepository = messageRepository;

    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        System.out.println("*****************Welcome*************");
    }
}
