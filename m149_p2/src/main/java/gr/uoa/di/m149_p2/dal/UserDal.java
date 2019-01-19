package gr.uoa.di.m149_p2.dal;

import gr.uoa.di.m149_p2.models.User;
import gr.uoa.di.m149_p2.models.queries.MostActiveUsers;
import gr.uoa.di.m149_p2.models.queries.TopUsersByWards;
import gr.uoa.di.m149_p2.models.queries.VotedWards;

import java.util.List;

public interface UserDal {
    public List<MostActiveUsers> getMostActiveUsers();

    public List<TopUsersByWards> getTopUsersByWards();

    public List<VotedWards> getVotedWards(String name);

    public boolean requestUpVoted(String name, String telephone, long id);

    public User getUser(String name, String telephone);
}
