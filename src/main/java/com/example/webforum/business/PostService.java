package com.example.webforum.business;

import com.example.webforum.db.dbo.PostDb;
import com.example.webforum.db.dbo.UserDb;
import com.example.webforum.db.repositories.PostRepository;
import com.example.webforum.db.repositories.UserRepository;
import com.example.webforum.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService implements IPostService {
    @Autowired
    private final PostRepository postRepository;
    @Autowired
    private final UserRepository userRepository;

    protected PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
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
            return "User Cannot be null";
        else if (this.userRepository.getByUsername(user.getUsername()) != null)
            return "Username is taken, try with another one!";
        UserDb userDb = new UserDb();
        userDb.setUsername(user.getUsername());
        userDb.setFull_name(user.getFullName());
        userDb.setAge(user.getAge());
        userDb.setPassword(user.getPassword());
        userDb.setPostDbList(new ArrayList<>());
        this.userRepository.save(userDb);
        return "Added successfully";
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
        postDb.setCreatedDate( timestamp);
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
