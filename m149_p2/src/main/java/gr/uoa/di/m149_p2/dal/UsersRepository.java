package gr.uoa.di.m149_p2.dal;

import gr.uoa.di.m149_p2.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends MongoRepository<User, String> {
}
