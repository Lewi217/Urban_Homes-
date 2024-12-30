package AoristHomes.AoristHomes.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "cards")
public class Card {
    @Id
    private String id;
    private String userId;
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;
}
