package gr.uoa.di.m149_p2.dal;

import gr.uoa.di.m149_p2.models.queries.MostActiveUsers;
import gr.uoa.di.m149_p2.models.queries.VotedWards;

import java.util.List;

public interface UserDal {
    public List<MostActiveUsers> getMostActiveUsers();

    public List<VotedWards> getVotedWards(String name);
}
