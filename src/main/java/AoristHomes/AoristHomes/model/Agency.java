package AoristHomes.AoristHomes.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Document(collection = "agencies")
public class Agency {
    @Id
    private String id;
    private String name;
    private String email;
    private String description;
    private BigDecimal walletBalance;
    private List<String> propertyIds;

    @CreatedDate
    private LocalDateTime createdAt = LocalDateTime.now();

    @LastModifiedDate
    private LocalDateTime updatedAt = LocalDateTime.now();
}
