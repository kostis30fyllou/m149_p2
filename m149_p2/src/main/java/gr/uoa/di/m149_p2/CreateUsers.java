package gr.uoa.di.m149_p2;

import com.github.javafaker.Faker;

import com.mongodb.client.result.UpdateResult;
import gr.uoa.di.m149_p2.dal.UsersRepository;
import gr.uoa.di.m149_p2.models.Request;
import gr.uoa.di.m149_p2.models.User;
import gr.uoa.di.m149_p2.models.queries.TotalTypeRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.SampleOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.Random;


@SpringBootApplication
public class CreateUsers implements CommandLineRunner {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UsersRepository usersRepository;

    public static void main(String[] args) {
        SpringApplication.run(CreateUsers.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Create();
        System.out.println("done");


//        Query query2 = new Query();
//        query.with(new Sort(Sort.Direction.ASC, "_id"));
//        List<User> users = mongoTemplate.find(query,User.class);


    }

    public void Create() {
        System.out.println("Creating Users for DB");

        Random random = new Random();

        Query requestCount = new Query();
        Long count = mongoTemplate.count(requestCount, Request.class);
        System.out.println(count);

        usersRepository.deleteAll();
        Query resetUpVotes = new Query();
        resetUpVotes.addCriteria(Criteria.where("upVotes").ne(0));
        Update update = new Update();
        update.set("upVotes", 0);
        mongoTemplate.updateMulti(requestCount, update, Request.class);

        Faker faker = new Faker();
        for (int i = 0; i < 20000; i++) {
            String name = faker.name().fullName();
            String telephone = faker.phoneNumber().phoneNumber();
            String address = faker.address().fullAddress();

//            System.out.println(String.format("%s\n%s\n%s",
//                    name,
//                    telephone,
//                    address));

//            System.out.println(count);

            long[] array = new long[count.intValue()];
//            for (int j = 0; j < array.length; j++) {
//                if (array[j] != 0)
//                    System.out.println("Boom");
//            }
//            System.out.println(array.length);
            ArrayList<Request> upVoted = new ArrayList<Request>();
//            SampleOperation matchStage = Aggregation.sample(1);
//            Aggregation aggregation= Aggregation.newAggregation(matchStage);

            int randomUpdates = random.nextInt(100) + 50;
            for (int j = 0; j < randomUpdates; j++) {

                int id = random.nextInt(count.intValue()) + 1;

                Query upVote = new Query();
                upVote.addCriteria(Criteria.where("id").is(id));
                Update update1 = new Update();
                update1.inc("upVotes", 1);
                Request request = mongoTemplate.findAndModify(upVote, update1, Request.class);

//                AggregationResults<Request> output2 = mongoTemplate.aggregate(aggregation, Request.class, Request.class);
//                Long id = output2.getMappedResults().get(0).getId();
                if (array[id-1] == 0) {
                    array[id-1] = id;
                    upVoted.add(request);
                }

            }

            User document = new User(name, telephone, address, upVoted);
            usersRepository.save(document);
            System.out.println("Created user " + i);
        }

//        Query query = new Query();
//        query.with(new Sort(Sort.Direction.ASC, "_id"));
//        List<User> users = mongoTemplate.find(query,User.class);

//        for (User user :
//                users) {
//            System.out.println(user.getId());
//            System.out.println(user.getName());
//            System.out.println(user.getAddress());
//        }

//        SampleOperation matchStage = Aggregation.sample(1);
//        Aggregation aggregation= Aggregation.newAggregation(matchStage);
//        AggregationResults<User> output = mongoTemplate.aggregate(aggregation, "user", User.class);
//
//        System.out.println(output.getRawResults());
//        System.out.println(output.getRawResults().values());
//        System.out.println(output.getMappedResults().get(0).getName());

//        Aggregation agg = Aggregation.newAggregation(Aggregation.sort(Sort.Direction.DESC, "id"));
//        AggregationResults<Request> results = mongoTemplate.aggregate(agg, Request.class, Request.class);
//
//        System.out.println(results.getMappedResults().get(1));

//        AggregationResults<Request> output2 = mongoTemplate.aggregate(aggregation, Request.class, Request.class);
//        System.out.println(output2.getMappedResults().get(0).getId());
    }
}
