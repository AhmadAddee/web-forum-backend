package com.example.webforum.util;

import com.example.webforum.db.UserRepository;
import com.example.webforum.db.User;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AppStartupEvent implements ApplicationListener<ApplicationReadyEvent> {
    private final UserRepository userRepository;

    public AppStartupEvent(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Iterable<User> theUsers = this.userRepository.findAll();
        theUsers.forEach(System.out::println);
    }
}
