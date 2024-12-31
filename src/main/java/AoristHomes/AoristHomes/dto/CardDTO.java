package AoristHomes.AoristHomes.dto;

import lombok.Data;

@Data
public class CardDTO {
    private String id;
    private String userId;
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;
}
