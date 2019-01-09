package gr.uoa.di.m149_p2.dal;

import gr.uoa.di.m149_p2.models.Request;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends MongoRepository<Request, String > {
}
