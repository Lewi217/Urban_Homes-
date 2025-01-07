package AoristHomes.AoristHomes.dto;

import lombok.Data;
import org.springframework.data.annotation.LastModifiedBy;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class AgencyDTO {
    private String id;
    private String name;
    private String description;
    private BigDecimal walletBalance;
    private List<String> propertyIds;

}
