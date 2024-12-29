package AoristHomes.AoristHomes.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Setter
@Document(collection = "user_investment")
public class UserInvestment {
    @Id
    private String id;
    private String userId;
    private String propertyId;
    private BigDecimal investmentAmount;
}
