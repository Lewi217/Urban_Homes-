package AoristHomes.AoristHomes.repository;

import AoristHomes.AoristHomes.model.Card;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends MongoRepository<Card, String> {

    Optional<Card> findByUserId(String userId);
    boolean existsByUserId(String userId);
}
