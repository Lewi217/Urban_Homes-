package AoristHomes.AoristHomes.repository;


import AoristHomes.AoristHomes.model.Agency;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgencyRepository extends MongoRepository<Agency, String> {

    Optional<Agency> findById(String id);


}
