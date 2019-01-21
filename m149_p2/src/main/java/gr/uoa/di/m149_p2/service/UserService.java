package gr.uoa.di.m149_p2.service;

import gr.uoa.di.m149_p2.dal.RequestDal;
import gr.uoa.di.m149_p2.dal.UserDal;
import gr.uoa.di.m149_p2.dal.UsersRepository;
import gr.uoa.di.m149_p2.models.Request;
import gr.uoa.di.m149_p2.models.User;
import gr.uoa.di.m149_p2.models.queries.MostActiveUsers;
import gr.uoa.di.m149_p2.models.queries.MultiTelephones;
import gr.uoa.di.m149_p2.models.queries.TopUsersByWards;
import gr.uoa.di.m149_p2.models.queries.VotedWards;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


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

    public static <T> Set<T> getCommonElements(Collection<? extends Collection<T>> collections) {

        Set<T> common = new LinkedHashSet<>();
        if (!collections.isEmpty()) {
            Iterator<? extends Collection<T>> iterator = collections.iterator();
            common.addAll(iterator.next());
            while (iterator.hasNext()) {
                common.retainAll(iterator.next());
            }
        }
        return common;
    }

    public List<Long> getUpVotedFromMultiTelephones() {
        List<MultiTelephones> multiTelephones = userDal.getMultiTelephones();
        List<Long> results = new ArrayList<>();
        for(MultiTelephones multiTelephone : multiTelephones) {
            List<List<Long>> all = new ArrayList<>();
            for(String name : multiTelephone.getNames()) {
                all.add(userDal.getTotalUpVotes(name, multiTelephone.getTelephone()));
            }
            results.addAll(getCommonElements(all));
        }
        return results;
    }
}
