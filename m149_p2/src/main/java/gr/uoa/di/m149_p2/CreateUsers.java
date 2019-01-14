package gr.uoa.di.m149_p2;

import com.github.javafaker.Faker;

import com.mongodb.BasicDBObject;
import gr.uoa.di.m149_p2.dal.UsersRepository;
import gr.uoa.di.m149_p2.dto.CustomAggregationOperation;
import gr.uoa.di.m149_p2.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;


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
        /*System.out.println("Creating Users for DB");

        Faker faker = new Faker();
        usersRepository.deleteAll();
        for (int i = 0; i < 10; i++) {
            String name = faker.name().fullName();
            String telephone = faker.phoneNumber().phoneNumber();
            String address = faker.address().fullAddress();

            System.out.println(String.format("%s\n%s\n%s",
                    name,
                    telephone,
                    address));

            User document = new User(name, telephone, address, null);
            usersRepository.save(document);
        }*/

        Query query = new Query();
        query.with(new Sort(Sort.Direction.ASC, "_id"));
        List<User> users = mongoTemplate.find(query,User.class);

        for (User user :
                users) {
            System.out.println(user.getId());
            System.out.println(user.getName());
            System.out.println(user.getAddress());
        }

        /*Aggregation aggregation = newAggregation(
                new CustomAggregationOperation(
                        new BasicDBObject(
                                "$sample",
                                new BasicDBObject( "size", 1 )
                        )
                )
        );
        System.out.println(aggregation);

        AggregationResults<User> result = mongoTemplate.aggregate(aggregation,User.class, User.class);
        System.out.println(result);*/
    }
}
