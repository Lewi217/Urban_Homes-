package AoristHomes.AoristHomes.service.property;

import AoristHomes.AoristHomes.dto.PropertyDTO;
import AoristHomes.AoristHomes.model.Property;
import AoristHomes.AoristHomes.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PropertyService implements IPropertyService{
    private final PropertyRepository propertyRepository;

    @Override
    public PropertyDTO addProperty(PropertyDTO propertyDTO) {
        Property property = mapToEntity(propertyDTO);
        propertyRepository.save(property);
        return mapToDTO(property);
    }

    @Override
    public PropertyDTO getPropertyById(String id) {
        Optional<Property> propertyOptional = propertyRepository.findById(id);
        return propertyOptional.map(this::mapToDTO).orElse(null);
    }

    @Override
    public PropertyDTO updateProperty(String id, PropertyDTO propertyDTO) {
        Optional<Property> propertyOptional = propertyRepository.findById(id);
        if (propertyOptional.isPresent()) {
            Property property = propertyOptional.get();
            property.setName(propertyDTO.getName());
            property.setDescription(propertyDTO.getDescription());
            property.setLocation(propertyDTO.getLocation());
            property.setPrice(propertyDTO.getPrice());
            property.setCoverPhoto(propertyDTO.getCoverPhoto());
            property.setCoverVideo(propertyDTO.getCoverVideo());
            property.setPanoramas(propertyDTO.getPanoramas());
            property.setTitle(propertyDTO.getTitle());
            property.setArea(propertyDTO.getArea());
            property.setBedrooms(propertyDTO.getBedrooms());
            property.setBathrooms(propertyDTO.getBathrooms());
            property.setPhotos(propertyDTO.getPhotos());
            property.setAmenities(propertyDTO.getAmenities());
            property.setFurnishingStatus(propertyDTO.getFurnishingStatus());
            property.setAvailability(propertyDTO.getAvailability());
            property.setTotalInvested(propertyDTO.getTotalInvested());
            property.setAgencyId(propertyDTO.getAgencyId());
            property.setAvailableForInvestment(propertyDTO.getAvailableForInvestment());
            property.setUpdatedAt(LocalDateTime.now());
            propertyRepository.save(property);
            return mapToDTO(property);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteProperty(String id) {
        if (propertyRepository.existsById(id)) {
            propertyRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private Property mapToEntity(PropertyDTO propertyDTO) {
        Property property = new Property();
        property.setId(propertyDTO.getId());
        property.setName(propertyDTO.getName());
        property.setDescription(propertyDTO.getDescription());
        property.setLocation(propertyDTO.getLocation());
        property.setPrice(propertyDTO.getPrice());
        property.setCoverPhoto(propertyDTO.getCoverPhoto());
        property.setCoverVideo(propertyDTO.getCoverVideo());
        property.setPanoramas(propertyDTO.getPanoramas());
        property.setTitle(propertyDTO.getTitle());
        property.setArea(propertyDTO.getArea());
        property.setBedrooms(propertyDTO.getBedrooms());
        property.setBathrooms(propertyDTO.getBathrooms());
        property.setPhotos(propertyDTO.getPhotos());
        property.setAmenities(propertyDTO.getAmenities());
        property.setFurnishingStatus(propertyDTO.getFurnishingStatus());
        property.setAvailability(propertyDTO.getAvailability());
        property.setTotalInvested(propertyDTO.getTotalInvested());
        property.setAgencyId(propertyDTO.getAgencyId());
        property.setAvailableForInvestment(propertyDTO.getAvailableForInvestment());
        property.setCreatedAt(propertyDTO.getCreatedAt());
        property.setUpdatedAt(propertyDTO.getUpdatedAt());
        return property;
    }

    private PropertyDTO mapToDTO(Property property) {
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setId(property.getId());
        propertyDTO.setName(property.getName());
        propertyDTO.setDescription(property.getDescription());
        propertyDTO.setLocation(property.getLocation());
        propertyDTO.setPrice(property.getPrice());
        propertyDTO.setCoverPhoto(property.getCoverPhoto());
        propertyDTO.setCoverVideo(property.getCoverVideo());
        propertyDTO.setPanoramas(property.getPanoramas());
        propertyDTO.setTitle(property.getTitle());
        propertyDTO.setArea(property.getArea());
        propertyDTO.setBedrooms(property.getBedrooms());
        propertyDTO.setBathrooms(property.getBathrooms());
        propertyDTO.setPhotos(property.getPhotos());
        propertyDTO.setAmenities(property.getAmenities());
        propertyDTO.setFurnishingStatus(property.getFurnishingStatus());
        propertyDTO.setAvailability(property.getAvailability());
        propertyDTO.setTotalInvested(property.getTotalInvested());
        propertyDTO.setAgencyId(property.getAgencyId());
        propertyDTO.setAvailableForInvestment(property.getAvailableForInvestment());
        propertyDTO.setCreatedAt(property.getCreatedAt());
        propertyDTO.setUpdatedAt(property.getUpdatedAt());
        return propertyDTO;
    }
}