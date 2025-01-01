package AoristHomes.AoristHomes.repository;

import AoristHomes.AoristHomes.model.UserInvestment;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInvestmentRepository extends MongoRepository<UserInvestment, String> {
    List<UserInvestment> findByUserId(String userId);
}