package AoristHomes.AoristHomes.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class WalletDTO {
    private String id;
    private String userId;
    private String agencyId;
    private BigDecimal balance;
    private BigDecimal amount;
}
