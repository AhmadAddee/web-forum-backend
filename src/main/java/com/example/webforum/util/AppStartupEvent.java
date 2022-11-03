package com.example.webforum.util;

import com.example.webforum.business.Post;
import com.example.webforum.business.PostService;
import com.example.webforum.db.dbo.MessageDb;
import com.example.webforum.db.dbo.PostDb;
import com.example.webforum.db.repositories.MessageRepository;
import com.example.webforum.db.repositories.PostRepository;
import com.example.webforum.db.repositories.UserRepository;
import com.example.webforum.db.dbo.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PostRepository postRepository;
    @Autowired
    private final MessageRepository messageRepository;

    private final PostService postService;

    public AppStartupEvent(UserRepository userRepository, PostRepository postRepository, MessageRepository messageRepository, PostService postService) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.messageRepository = messageRepository;
        this.postService = postService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Iterable<UserDb> theUsers = this.userRepository.findAll();
        theUsers.forEach(System.out::println);
        Iterable<PostDb> posts = this.postRepository.findAll();
        posts.forEach(postDb -> {
            System.out.println("Test:: " + DateUtils.createDateFromDateString(postDb.getCreatedDate().toString()));
        });
        Iterable<MessageDb> messages = this.messageRepository.findAll();
        messages.forEach(System.out::println);
        List<Post> postList = postService.getPostsForUser();
        postList.forEach(System.out::println);
    }
}
