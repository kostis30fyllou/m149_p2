package gr.uoa.di.m149_p2;

import com.github.javafaker.Faker;

import gr.uoa.di.m149_p2.models.Request;
import gr.uoa.di.m149_p2.models.User;
import gr.uoa.di.m149_p2.service.RequestService;
import gr.uoa.di.m149_p2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
        Create();
    }

    public void Create() {
        System.out.println("Creating Users for DB");

        Random random = new Random();

        Long count = requestService.getCount();
//        System.out.println(count);

        userService.deleteAll();
        requestService.resetUpvotes();

        Faker faker = new Faker();
        for (int i = 0; i < 30000; i++) {
            if (i%100 == 0) {
                if (requestService.getUpvotedRequestCount() > count / 3)
                    break;
            }
            String name = faker.name().fullName();
            String telephone = faker.phoneNumber().phoneNumber();
            String address = faker.address().fullAddress();

            int[] array = new int[count.intValue()];
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
