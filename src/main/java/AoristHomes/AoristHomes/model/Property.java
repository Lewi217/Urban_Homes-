package AoristHomes.AoristHomes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String coverPhoto;
    private List<String> coverVideo;
    private List<String> panoramas;
    private String title;
    private Number area;
    private Number bedrooms;
    private Number bathrooms;
    private List<String> photos;
    private List<String> amenities;
    private Boolean furnishingStatus;
    private Boolean availability;
    private BigDecimal totalInvested = BigDecimal.ZERO;

    @JsonIgnore
    private String agencyId;
    private List<String> availableForInvestment;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
}
