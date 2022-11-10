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

    //private final IPostService postService;

    public AppStartupEvent(UserRepository userRepository, PostRepository postRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.messageRepository = messageRepository;
        //this.postService = postService;
    }

    // TEST
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        PostDb post = postRepository.findById(-1);
        //System.out.println(DateUtils.calculateTimeAgo(post.getCreatedDate()));
        System.out.println(DateUtils.calculateTimeAgo(new Date(System.currentTimeMillis() - (3 * 31590000000L))));//year
        System.out.println(DateUtils.calculateTimeAgo((new Date(System.currentTimeMillis() - (61 * 86400000L) - (24 * 60 * 60 * 1000)))));//month
        System.out.println(DateUtils.calculateTimeAgo(new Date(System.currentTimeMillis() - (29 * 86400000L) - (24 * 60 * 60 * 1000))));//days
        System.out.println(DateUtils.calculateTimeAgo(new Date(System.currentTimeMillis() - (4 * 60 * 60 * 1000))));//hours
        System.out.println(DateUtils.calculateTimeAgo(new Date(System.currentTimeMillis() - (60 * 60 * 1000))));//min
        System.out.println(DateUtils.calculateTimeAgo(new Date(System.currentTimeMillis() - (44 * 60 * 1000) - (5 * 1000))));//min
        System.out.println(DateUtils.calculateTimeAgo(new Date(System.currentTimeMillis() - (5 * 1000))));//sec
        java.sql.Date date = new java.sql.Date(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        java.util.Date uDate = new Date();

        java.sql.Time sqlTime = new Time(uDate.getTime());
        java.sql.Date sqlDate = new java.sql.Date(uDate.getTime());
        System.out.println("utilDate: " + uDate);
        System.out.println("sqlDate: " + sqlDate + " sqlTime: " + sqlTime);

    }
}
