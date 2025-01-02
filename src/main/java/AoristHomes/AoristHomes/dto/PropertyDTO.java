package AoristHomes.AoristHomes.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PropertyDTO {
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
    private BigDecimal totalInvested;
    private String agencyId;
    private List<String> availableForInvestment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}