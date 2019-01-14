package gr.uoa.di.m149_p2.dal;

import gr.uoa.di.m149_p2.models.Request;
import gr.uoa.di.m149_p2.models.queries.TotalTypeRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class RequestDalImpl implements RequestDal{

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<TotalTypeRequests> getTotalTypeRequests(Date startDate, Date endDate) {
        Aggregation agg = Aggregation.newAggregation(Aggregation.match(Criteria.where("creationDate")
                        .gte(startDate).lte(endDate)), Aggregation.group("typeOfServiceRequest").
                count().as("count"), Aggregation.project("count").and("typeOfServiceRequest").previousOperation(),
                Aggregation.sort(Sort.Direction.DESC, "count"));
        AggregationResults<TotalTypeRequests> results = mongoTemplate.aggregate(agg, Request.class, TotalTypeRequests.class);
        return results.getMappedResults();
    }
}
