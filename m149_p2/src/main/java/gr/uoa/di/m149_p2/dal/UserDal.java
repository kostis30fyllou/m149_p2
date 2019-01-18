package gr.uoa.di.m149_p2.dal;

import gr.uoa.di.m149_p2.models.queries.MostActiveUsers;

import java.util.List;

public interface UserDal {
    public List<MostActiveUsers> getMostActiveUsers();
}
