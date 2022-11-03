package com.example.webforum.db.repositories;

import com.example.webforum.db.dbo.MessageDb;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends CrudRepository<MessageDb, Integer> {
}
