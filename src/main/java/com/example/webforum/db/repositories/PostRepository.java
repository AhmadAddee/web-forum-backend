package com.example.webforum.db.repositories;

import com.example.webforum.db.dbo.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Integer> {
    //Iterable<Post> findPostsByUsername(String username);
}
