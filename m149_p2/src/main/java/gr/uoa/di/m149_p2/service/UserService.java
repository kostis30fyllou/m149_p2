package gr.uoa.di.m149_p2.service;

import gr.uoa.di.m149_p2.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<User> getUsers() {
        Query query = new Query();
        query.with(new Sort(Sort.Direction.ASC, "_id"));
        List<User> users = mongoTemplate.find(query,User.class);
        return users;
    }
}
