package com.example.webforum.db.repositories;

import com.example.webforum.db.dbo.PostDb;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<PostDb, Integer> {
    //Iterable<Post> findPostsByUsername(String username);
}
