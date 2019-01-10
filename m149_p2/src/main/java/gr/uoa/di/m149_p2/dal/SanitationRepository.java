package gr.uoa.di.m149_p2.dal;

import gr.uoa.di.m149_p2.models.Sanitation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SanitationRepository extends MongoRepository<Sanitation, String> {
}
