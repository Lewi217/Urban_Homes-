package AoristHomes.AoristHomes.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserInvestmentDTO {
    private String id;
    private String userId;
    private String propertyId;
    private BigDecimal investmentAmount;
}