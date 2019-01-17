package gr.uoa.di.m149_p2.service;

import gr.uoa.di.m149_p2.dal.UsersRepository;
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

    @Autowired
    UsersRepository usersRepository;

    public void deleteAll() {
        usersRepository.deleteAll();
    }

    public User createUser(User user) {
        Long id = usersRepository.count() + 1;
        user.setId(id);
        return usersRepository.save(user);
    }
}
