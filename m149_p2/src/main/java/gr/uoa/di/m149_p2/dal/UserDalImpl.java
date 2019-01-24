package gr.uoa.di.m149_p2.dal;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import gr.uoa.di.m149_p2.models.User;
import gr.uoa.di.m149_p2.models.queries.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOptions;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregationOptions;

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

    @Override
    public List<TopUsersByWards> getTopUsersByWards() {
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.unwind("upVoted"),
                Aggregation.group("id").first("name").as("name").addToSet("upVoted.ward").as("ward"),
                Aggregation.project("id", "name").and("ward").size().as("wards"),
                Aggregation.sort(Sort.Direction.DESC, "wards"),
                Aggregation.limit(50)
        );
        AggregationResults<TopUsersByWards> results = mongoTemplate.aggregate(agg, User.class, TopUsersByWards.class);
        return results.getMappedResults();
    }

    @Override
    public List<VotedWards> getVotedWards(String name) {
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("name").is(name)),
                Aggregation.unwind("upVoted"),
                Aggregation.group("upVoted.ward"),
                Aggregation.sort(Sort.Direction.ASC, "_id")
        );
        AggregationResults<VotedWards> results = mongoTemplate.aggregate(agg, User.class, VotedWards.class);
        return results.getMappedResults();
    }

    @Override
    public boolean requestUpVoted(String name, String telephone, long id) {
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("name").is(name).and("telephone").is(telephone)),
                Aggregation.unwind("upVoted"),
                Aggregation.match(Criteria.where("upVoted.id").is(id))
        );
        AggregationResults<User> results = mongoTemplate.aggregate(agg, User.class, User.class);
        if(results.getUniqueMappedResult() == null) {
            return false;
        }
        else return true;
    }

    @Override
    public User getUser(String name, String telephone) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name).and("telephone").is(telephone));
        return mongoTemplate.findOne(query, User.class);
    }


    @Override
    public List<MultiTelephones> getMultiTelephones() {
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.group("telephone").addToSet("name").as("names").count().as("count"),
                Aggregation.match(Criteria.where("count").gt(1)),
                Aggregation.project("names", "count").and("telephone").previousOperation()
        );
        AggregationResults<MultiTelephones> results = mongoTemplate.aggregate(agg, User.class, MultiTelephones.class);
        return results.getMappedResults();
    }

    @Override
    public List<MultiTelephones> getMultiTelephonesSingle() {
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.project("name", "telephone").and("$upVoted.id").as("incidents"),
                Aggregation.unwind("incidents"),
                Aggregation.group("$telephone", "$incidents").addToSet("name").as("names"),
                Aggregation.project("$telephone", "names").and("names").size().as("count"),
                Aggregation.match(Criteria.where("count").gt(1)),
                Aggregation.sort(Sort.Direction.ASC, "count")
        ).withOptions(newAggregationOptions().allowDiskUse(true).build());
        AggregationResults<MultiTelephones> results = mongoTemplate.aggregate(agg, User.class, MultiTelephones.class);
        return results.getMappedResults();
    }

    @Override
    public List<Long> getTotalUpVotes(String name, String telephone) {
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("name").is(name).and("telephone").is(telephone)),
                Aggregation.unwind("upVoted"),
                Aggregation.group().addToSet("upVoted.id").as("upvotes"),
                Aggregation.project("upvotes")
        );
        AggregationResults<TotalUpVotes> results = mongoTemplate.aggregate(agg, User.class, TotalUpVotes.class);
        return results.getUniqueMappedResult().getUpvotes();
    }

}
