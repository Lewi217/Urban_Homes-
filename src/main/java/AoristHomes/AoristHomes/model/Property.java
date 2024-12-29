package AoristHomes.AoristHomes.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Document(collection = "properties")
public class Property {
    @Id
    private String id;
    private String name;
    private String description;
    private String location;
    private BigDecimal price;
    private BigDecimal totalInvested = BigDecimal.ZERO;
    private String agencyId;
    private List<String> availableForInvestment;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
}
