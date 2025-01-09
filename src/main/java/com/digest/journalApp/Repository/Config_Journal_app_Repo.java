package com.digest.journalApp.Repository;

import com.digest.journalApp.Entity.ConfigJournalAppEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Config_Journal_app_Repo extends MongoRepository<ConfigJournalAppEntity,ObjectId> {
}
