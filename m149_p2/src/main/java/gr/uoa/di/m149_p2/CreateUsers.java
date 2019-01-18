package gr.uoa.di.m149_p2;

import com.github.javafaker.Faker;

import com.mongodb.client.result.UpdateResult;
import gr.uoa.di.m149_p2.dal.UsersRepository;
import gr.uoa.di.m149_p2.models.Request;
import gr.uoa.di.m149_p2.models.User;
import gr.uoa.di.m149_p2.models.queries.TotalTypeRequests;
import gr.uoa.di.m149_p2.service.RequestService;
import gr.uoa.di.m149_p2.service.UserService;
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
    private RequestService requestService;

    @Autowired
    private UserService userService;


    public static void main(String[] args) {
        SpringApplication.run(CreateUsers.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //Create();
        System.out.println("done");
    }

    public void Create() {
        System.out.println("Creating Users for DB");

        Random random = new Random();

        Long count = requestService.getCount();
        System.out.println(count);

        userService.deleteAll();
        requestService.resetUpvotes();

        Faker faker = new Faker();
        while (requestService.getUpvotedRequestCount() < count/3){
            String name = faker.name().fullName();
            String telephone = faker.phoneNumber().phoneNumber();
            String address = faker.address().fullAddress();

            long[] array = new long[count.intValue()];
            ArrayList<Request> upVoted = new ArrayList<Request>();

            int randomUpdates = random.nextInt(950) + 50;
            for (int j = 0; j < randomUpdates; j++) {
                int id = random.nextInt(count.intValue()) + 1;
                if (array[id-1] == 0) {
                    array[id-1] = id;
                    Request request = requestService.upvoteRequest(id);
                    upVoted.add(request);
                }
            }
            User user = new User(name, telephone, address, upVoted);
            user = userService.createUser(user);
            System.out.println("Created user " + user.getId());
        }
    }
}
