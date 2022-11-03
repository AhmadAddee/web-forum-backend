package com.example.webforum.db.repositories;

import com.example.webforum.db.dbo.UserDb;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserDb, Integer> {
    public UserDb getByUsername(String username);
}
