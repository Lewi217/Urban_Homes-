package AoristHomes.AoristHomes.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "transactions")
public class Transaction {
    @Id
    private String id;
    private String userId;
    private String agencyId;
    private String propertyId;
    private BigDecimal amount;
    private String transactionType;
    private LocalDateTime createdAt = LocalDateTime.now();
}
