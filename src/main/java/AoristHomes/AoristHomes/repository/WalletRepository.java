package AoristHomes.AoristHomes.repository;

import AoristHomes.AoristHomes.model.Wallet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends MongoRepository<Wallet, String> {

    Optional<Wallet> findByUserId(String userId);
    boolean existsByUserId(String userId);
}
