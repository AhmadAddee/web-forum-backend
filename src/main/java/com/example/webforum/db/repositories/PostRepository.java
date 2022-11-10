package com.example.webforum.db.repositories;

import com.example.webforum.db.dbo.PostDb;
import com.example.webforum.db.dbo.UserDb;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<PostDb, Integer> {
    Iterable<PostDb> findPostByCreator(UserDb creator);
    PostDb findById(int id);
}
