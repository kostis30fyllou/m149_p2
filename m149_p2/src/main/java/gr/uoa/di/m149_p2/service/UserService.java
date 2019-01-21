package gr.uoa.di.m149_p2.service;

import gr.uoa.di.m149_p2.dal.RequestDal;
import gr.uoa.di.m149_p2.dal.UserDal;
import gr.uoa.di.m149_p2.dal.UsersRepository;
import gr.uoa.di.m149_p2.models.Request;
import gr.uoa.di.m149_p2.models.User;
import gr.uoa.di.m149_p2.models.queries.MostActiveUsers;
import gr.uoa.di.m149_p2.models.queries.TopUsersByWards;
import gr.uoa.di.m149_p2.models.queries.VotedWards;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    UserDal userDal;

    @Autowired
    RequestDal requestDal;

    public void deleteAll() {
        usersRepository.deleteAll();
    }

    public User createUser(User user) {
        Long id = usersRepository.count() + 1;
        user.setId(id);
        return usersRepository.save(user);
    }

    public boolean upVoteRequest(String name, String telephone, long id) {
        User user = userDal.getUser(name, telephone);
        if(user == null) {
            user = createUser(new User(name, telephone, null, new ArrayList<>()));
            Request request = requestDal.upvoteRequest(id);
            if(request != null) {
                user.upVoteRequest(request);
            }
            usersRepository.save(user);
            return true;
        }
        else if(!userDal.requestUpVoted(name, telephone, id)) {
            Request request = requestDal.upvoteRequest(id);
            if(request != null) {
                user.upVoteRequest(request);
            }
            usersRepository.save(user);
            return true;
        }
        else return false;
    }

    public List<MostActiveUsers> getMostActiveUsers() throws Exception {
        return userDal.getMostActiveUsers();
    }

    public List<TopUsersByWards> getTopUsersByWards() {
        return userDal.getTopUsersByWards();
    }

    public List<VotedWards> getVotedWards(String name) throws Exception {
        return userDal.getVotedWards(name);
    }


}
