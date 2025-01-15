package com.digest.journalApp.Repository;

import com.digest.journalApp.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class UserReproImpl {

    @Autowired
    MongoTemplate mongoTemplate;


    public List<User> getSpec(String name)
    {
        Query query=new Query();
        query.addCriteria(Criteria.where("userName").is(name));
        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    } public List<User> getUserForSA()
    {
        Query query=new Query();
        query.addCriteria(Criteria.where("email").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"));
        query.addCriteria(Criteria.where("Sentiment").is(true));
        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }
}
