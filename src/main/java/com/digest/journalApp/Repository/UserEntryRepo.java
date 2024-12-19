package com.digest.journalApp.Repository;

import com.digest.journalApp.Entity.JournlEntry;
import com.digest.journalApp.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserEntryRepo extends MongoRepository<User, ObjectId>
{
    User findByuserName(String username);
    void deleteByuserName(String username);
}
