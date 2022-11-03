package com.example.webforum.util;

import com.example.webforum.db.dbo.Message;
import com.example.webforum.db.dbo.Post;
import com.example.webforum.db.repositories.MessageRepository;
import com.example.webforum.db.repositories.PostRepository;
import com.example.webforum.db.repositories.UserRepository;
import com.example.webforum.db.dbo.User;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final MessageRepository messageRepository;

    public AppStartupEvent(UserRepository userRepository, PostRepository postRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Iterable<User> theUsers = this.userRepository.findAll();
        theUsers.forEach(System.out::println);
        Iterable<Post> posts = this.postRepository.findAll();
        posts.forEach(System.out::println);
        Iterable<Message> messages = this.messageRepository.findAll();
        messages.forEach(System.out::println);
    }
}
