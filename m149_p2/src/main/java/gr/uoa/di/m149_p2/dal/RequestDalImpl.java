package gr.uoa.di.m149_p2.dal;

import com.mongodb.BasicDBObject;
import gr.uoa.di.m149_p2.models.Request;
import gr.uoa.di.m149_p2.models.User;
import gr.uoa.di.m149_p2.models.queries.*;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Box;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class RequestDalImpl implements RequestDal{

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void resetUpvotes() {
        Query resetUpVotes = new Query();
        resetUpVotes.addCriteria(Criteria.where("upVotes").ne(0));
        Update update = new Update();
        update.set("upVotes", 0);
        mongoTemplate.updateMulti(resetUpVotes, update, Request.class);
    }

    @Override
    public Request upvoteRequest(long id) {
        Query upVote = new Query();
        upVote.addCriteria(Criteria.where("id").is(id));
        Update update = new Update();
        update.inc("upVotes", 1);
        return mongoTemplate.findAndModify(upVote, update, Request.class);
    }

    @Override
    public Long getUpvotedRequestsCount() {
        Query count = new Query();
        count.addCriteria(Criteria.where("upVotes").gt(0));
        return mongoTemplate.count(count, Request.class);
    }

    @Override
    public List<TotalTypeRequests> getTotalTypeRequests(Date startDate, Date endDate) {
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("creationDate").gte(startDate).lte(endDate)),
                Aggregation.group("typeOfServiceRequest").count().as("count"),
                Aggregation.project("count").and("typeOfServiceRequest").previousOperation(),
                Aggregation.sort(Sort.Direction.DESC, "count")
        );
        AggregationResults<TotalTypeRequests> results = mongoTemplate.aggregate(agg, Request.class, TotalTypeRequests.class);
        return results.getMappedResults();
    }

    @Override
    public List<DailyRequests> getDailyRequests(String type, Date startDate, Date endDate) {
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("creationDate").gte(startDate).lte(endDate).and("typeOfServiceRequest").is(type)),
                Aggregation.group("creationDate").count().as("count"),
                Aggregation.project("count").and("creationDate").previousOperation()
        );
        AggregationResults<DailyRequests> results = mongoTemplate.aggregate(agg, Request.class, DailyRequests.class);
        return results.getMappedResults();
    }

    @Override
    public List<MostCommonTypes> getMostCommonTypes(Date date) {
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("creationDate").is(date).and("zipCode").exists(true)),
                Aggregation.group("zipCode", "typeOfServiceRequest").count().as("count"),
                Aggregation.project("zipCode", "typeOfServiceRequest", "count"),
                Aggregation.sort(Sort.Direction.DESC, "count"),
                Aggregation.group("zipCode").
                        push(new BasicDBObject("typeOfServiceRequest", "$typeOfServiceRequest").
                                append("count", "$count")).as("types"),
                Aggregation.project().and("zipCode").previousOperation().and("types").slice(3)
        );
        AggregationResults<MostCommonTypes> results = mongoTemplate.aggregate(agg, Request.class, MostCommonTypes.class);
        return results.getMappedResults();
    }

    @Override
    public List<LeastCommonWards> getLeastCommonWards(String type) {
        Aggregation agg = Aggregation.newAggregation(Aggregation.match(Criteria.where("typeOfServiceRequest").is(type).
                and("ward").exists(true)), Aggregation.group("ward").count().as("count"),
                Aggregation.project("count").and("ward").previousOperation(),
                Aggregation.sort(Sort.Direction.ASC, "count"), Aggregation.limit(3));
        AggregationResults<LeastCommonWards> results = mongoTemplate.aggregate(agg, Request.class, LeastCommonWards.class);
        return results.getMappedResults();
    }

    @Override
    public List<AvgRequestCompletion> getAvgRequestCompletion(Date startDate, Date endDate) {
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("creationDate").exists(true).
                gte(startDate).lte(endDate).and("completionDate").gte(startDate).lte(endDate)),
                Aggregation.project("typeOfServiceRequest").
                        andExpression("(completionDate-creationDate)/(24*60*60*1000)").as("completion"),
                Aggregation.group("typeOfServiceRequest").
                        avg("completion").as("avg"),
                Aggregation.project("avg").and("typeOfServiceRequest").previousOperation());
        AggregationResults<AvgRequestCompletion> results = mongoTemplate.
                aggregate(agg, Request.class, AvgRequestCompletion.class);
        return results.getMappedResults();
    }

    @Override
    public TotalTypeRequests getMostCommonRequest(Date date, GeoJsonPoint p1, GeoJsonPoint p2) {
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("creationDate").is(date).and("point").within(new Box(p1, p2))),
                Aggregation.group("typeOfServiceRequest").count().as("count"),
                Aggregation.project("count").and("typeOfServiceRequest").previousOperation(),
                Aggregation.sort(Sort.Direction.DESC, "count"),
                Aggregation.limit(1)
        );
        AggregationResults<TotalTypeRequests> results = mongoTemplate.aggregate(agg, Request.class, TotalTypeRequests.class);
        return results.getUniqueMappedResult();
    }

    @Override
    public List<MostVotedRequests> getMostVotedRequests(Date date) {
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("creationDate").is(date)),
                Aggregation.project("id", "typeOfServiceRequest", "upVotes"),
                Aggregation.sort(Sort.Direction.DESC, "upVotes"),
                Aggregation.limit(50)
        );
        AggregationResults<MostVotedRequests> results = mongoTemplate.aggregate(agg, Request.class, MostVotedRequests.class);
        return results.getMappedResults();
    }


}
