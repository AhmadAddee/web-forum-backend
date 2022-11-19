package com.example.webforum.business.services;

import com.example.webforum.business.IPostService;
import com.example.webforum.business.bo.Message;
import com.example.webforum.business.bo.Post;
import com.example.webforum.business.bo.User;
import com.example.webforum.db.dbo.MessageDb;
import com.example.webforum.db.dbo.PostDb;
import com.example.webforum.db.dbo.UserDb;
import com.example.webforum.db.repositories.MessageRepository;
import com.example.webforum.db.repositories.PostRepository;
import com.example.webforum.db.repositories.UserRepository;
import com.example.webforum.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostService implements IPostService {
    @Autowired
    private final PostRepository postRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final MessageRepository messageRepository;

    protected PostService(PostRepository postRepository, UserRepository userRepository, MessageRepository messageRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Post> getPosts(){
        Iterable<PostDb> postDbs = this.postRepository.findAll();
        List<Post> postList = new ArrayList<>();
        postDbs.forEach(postDb -> postList.add(postDbToPost(postDb)));
        return postList;
    }

    @Override
    public List<User> getUsers() {
        Iterable<UserDb> userDbs = this.userRepository.findAll();
        List<User> userList = new ArrayList<>();
        userDbs.forEach(userDb -> userList.add(userDbToUser(userDb)));
        return userList;
    }

    @Override
    public List<Post> getPostByCreator(String creator){
        UserDb userDb = this.userRepository.getByUsername(creator);
        if (userDb == null) return null;
        Iterable<PostDb> postDbs = this.postRepository.findPostByCreator(userDb);
        List<Post> postList = new ArrayList<>();
        postDbs.forEach(postDb -> postList.add(postDbToPost(postDb)));
        return postList;
    }

    @Override
    public User getUserByUsername(String username) {
        if (username == null)
            throw new RuntimeException("Post cannot be null");
        UserDb userDb = this.userRepository.getByUsername(username);
        return userDb != null? userDbToUser(userDb) : null;
    }

    @Override
    public String createPost(Post post) {
        if (post.getCreator() == null || post.getContent() == null)
            return "Post cannot be null!";
        UserDb userDb = this.userRepository.getByUsername(post.getCreator());
        if (userDb == null)
            return "The creator of the post cannot be null!";
        PostDb postDb = postToPostDB(post);
        postDb.setCreator(userDb);

        this.postRepository.save(postDb);
        return "Created successfully!";
    }

    @Override
    public String addUser(User user) {
        if (user.getUsername() == null || user.getFullName() == null ||
            user.getAge() == 0 || user.getPassword() == null)
            return null;
        else if (this.userRepository.getByUsername(user.getUsername()) != null)
            return null;
        UserDb userDb = new UserDb();
        userDb.setUsername(user.getUsername());
        userDb.setFull_name(user.getFullName());
        userDb.setAge(user.getAge());
        userDb.setPassword(user.getPassword());
        userDb.setPostDbList(new ArrayList<>());
        this.userRepository.save(userDb);
        return user.getUsername();
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

    @Override
    public User logIn(User user) {
        if (user == null)
            throw new RuntimeException("Post cannot be null");
        UserDb userDb = this.userRepository.getByUsername(user.getUsername());
        return userDb == null? null : userDb.getPassword().equals(user.getPassword())? userDbToUser(userDb) : null;
    }

    private Post postDbToPost(PostDb postDb) {
        Post post =  new Post();
            post.setId(postDb.getId());
            post.setCreator(postDb.getCreator().getUsername());
            post.setContent(postDb.getContent());
            Date date = new Date(postDb.getCreatedDate().getTime());
            post.setCreatedDate(date);
            post.setTimeAgo(DateUtils.calculateTimeAgo(post.getCreatedDate()));
        return post;
    }

    private PostDb postToPostDB(Post post) {
        java.util.Date now = new Date();
        Timestamp timestamp = new Timestamp(now.getTime());


        PostDb postDb = new PostDb();
        postDb.setContent(post.getContent());
        postDb.setCreatedDate(timestamp);
        return postDb;
    }

    private User userDbToUser(UserDb userDb) {
        User user = new User();
        user.setUsername(userDb.getUsername());
        user.setFullName(userDb.getFull_name());
        user.setAge(userDb.getAge());
        user.setPassword(userDb.getPassword());
        user.setPostList(new ArrayList<>());
        userDb.getPostDbList().forEach(postDb -> user.getPostList().add(postDbToPost(postDb)));
        return user;
    }
}
