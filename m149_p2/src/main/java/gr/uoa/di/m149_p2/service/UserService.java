package gr.uoa.di.m149_p2.service;

import gr.uoa.di.m149_p2.dal.UserDal;
import gr.uoa.di.m149_p2.dal.UsersRepository;
import gr.uoa.di.m149_p2.models.User;
import gr.uoa.di.m149_p2.models.queries.MostActiveUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    UserDal userDal;

    public void deleteAll() {
        usersRepository.deleteAll();
    }

    public User createUser(User user) {
        Long id = usersRepository.count() + 1;
        user.setId(id);
        return usersRepository.save(user);
    }

    public List<MostActiveUsers> getMostActiveUsers() throws Exception {
        return userDal.getMostActiveUsers();
    }

    public List<Integer> getVotedWards(String name) throws Exception {
        return userDal.getVotedWards(name);
    }

}
