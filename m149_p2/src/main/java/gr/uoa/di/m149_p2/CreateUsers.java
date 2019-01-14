package gr.uoa.di.m149_p2;

import com.github.javafaker.Faker;

import gr.uoa.di.m149_p2.dal.UsersRepository;
import gr.uoa.di.m149_p2.models.Request;
import gr.uoa.di.m149_p2.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.SampleOperation;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;


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
        //Create();



//        Query query2 = new Query();
//        query.with(new Sort(Sort.Direction.ASC, "_id"));
//        List<User> users = mongoTemplate.find(query,User.class);


    }

    public void Create() {
        System.out.println("Creating Users for DB");

        Faker faker = new Faker();
        usersRepository.deleteAll();
        for (int i = 0; i < 100; i++) {
            String name = faker.name().fullName();
            String telephone = faker.phoneNumber().phoneNumber();
            String address = faker.address().fullAddress();

            System.out.println(String.format("%s\n%s\n%s",
                    name,
                    telephone,
                    address));

            User document = new User(name, telephone, address, null);
            usersRepository.save(document);
        }

        Query query = new Query();
        query.with(new Sort(Sort.Direction.ASC, "_id"));
        List<User> users = mongoTemplate.find(query,User.class);

        for (User user :
                users) {
            System.out.println(user.getId());
            System.out.println(user.getName());
            System.out.println(user.getAddress());
        }

        SampleOperation matchStage = Aggregation.sample(1);
        Aggregation aggregation= Aggregation.newAggregation(matchStage);
        AggregationResults<User> output = mongoTemplate.aggregate(aggregation, "user", User.class);

        System.out.println(output.getRawResults());
        System.out.println(output.getRawResults().values());
        System.out.println(output.getMappedResults().get(0).getName());
        AggregationResults<Request> output2 = mongoTemplate.aggregate(aggregation, "request", Request.class);

    }
}
