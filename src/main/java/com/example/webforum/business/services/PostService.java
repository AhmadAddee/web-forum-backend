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
    public List<Post> getPostByCreator(String creator){
        UserDb userDb = this.userRepository.getByUsername(creator);
        if (userDb == null) return null;
        Iterable<PostDb> postDbs = this.postRepository.findPostByCreator(userDb);
        List<Post> postList = new ArrayList<>();
        postDbs.forEach(postDb -> postList.add(postDbToPost(postDb)));
        return postList;
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
}
