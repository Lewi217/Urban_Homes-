package AoristHomes.AoristHomes.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class HoldingDTO {
    private String propertyId;
    private String propertyName;
    private BigDecimal amountInvested;
    private BigDecimal totalPropertyInvestment;
    private BigDecimal sharePercentage;
}
