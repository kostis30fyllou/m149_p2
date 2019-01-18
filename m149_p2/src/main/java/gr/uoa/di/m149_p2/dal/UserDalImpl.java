package gr.uoa.di.m149_p2.dal;

import gr.uoa.di.m149_p2.models.User;
import gr.uoa.di.m149_p2.models.queries.MostActiveUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDalImpl implements UserDal{

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<MostActiveUsers> getMostActiveUsers() {
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.project("id", "name").and("upVoted").size().as("upVotes"),
                Aggregation.sort(Sort.Direction.DESC, "upVotes"),
                Aggregation.limit(50)
        );
        AggregationResults<MostActiveUsers> results = mongoTemplate.aggregate(agg, User.class, MostActiveUsers.class);
        return  results.getMappedResults();
    }

}
