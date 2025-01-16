package AoristHomes.AoristHomes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedBy;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class AgencyDTO {
    private String id;
    private String name;
    private String email;
    private String description;
    private BigDecimal walletBalance;
    private List<String> propertyIds;

}
