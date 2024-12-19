package com.digest.journalApp.Repository;

import com.digest.journalApp.Entity.JournlEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepo extends MongoRepository<JournlEntry, ObjectId>
{
}
