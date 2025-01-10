package AoristHomes.AoristHomes.service.agency;

import AoristHomes.AoristHomes.dto.AgencyDTO;
import AoristHomes.AoristHomes.dto.PropertyDTO;
import AoristHomes.AoristHomes.model.Agency;
import AoristHomes.AoristHomes.model.Property;
import AoristHomes.AoristHomes.repository.AgencyRepository;
import AoristHomes.AoristHomes.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AgencyService implements IAgencyService{
     private final AgencyRepository agencyRepository;
     private final PropertyRepository propertyRepository;

     @Override
     public AgencyDTO createAgency(AgencyDTO agencyDTO){
         Agency agency = mapToEntity(agencyDTO);
         agencyRepository.save(agency);
         return mapToDTO(agency);
     }

     @Override
    public AgencyDTO getAgencyById(String id) {
        Optional<Agency> agencyOptional = agencyRepository.findById(id);
        return agencyOptional.map(this::mapToDTO).orElse(null);
    }

    @Override
    public AgencyDTO updateAgency(String id, AgencyDTO agencyDTO) {
        Optional<Agency> agencyOptional = agencyRepository.findById(id);
        if (agencyOptional.isPresent()) {
            Agency agency = agencyOptional.get();
            agency.setName(agencyDTO.getName());
            agency.setDescription(agencyDTO.getDescription());
            agency.setWalletBalance(agencyDTO.getWalletBalance());
            agency.setPropertyIds(agencyDTO.getPropertyIds());
            agencyRepository.save(agency);
            return mapToDTO(agency);
        }
        return null;
    }

    @Override
    public boolean deleteAgency(String id) {
        if (agencyRepository.existsById(id)) {
            agencyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean withdrawFromWallet(String agencyId, BigDecimal amount) {
        Optional<Agency> agencyOptional = agencyRepository.findById(agencyId);
        if (agencyOptional.isPresent()) {
            Agency agency = agencyOptional.get();
            if (agency.getWalletBalance().compareTo(amount) >= 0) {
                agency.setWalletBalance(agency.getWalletBalance().subtract(amount));
                agencyRepository.save(agency);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<PropertyDTO> listPropertiesForInvestment(String agencyId) {
        Optional<Agency> agencyOptional = agencyRepository.findById(agencyId);
        if (agencyOptional.isPresent()) {
            List<String> propertyIds = agencyOptional.get().getPropertyIds();
            List<Property> properties = propertyRepository.findAllById(propertyIds);
            return properties.stream()
                    .filter(Property::getAvailability)
                    .map(this::mapToPropertyDTO)
                    .collect(Collectors.toList());
        }
        return List.of();
    }
    private Agency mapToEntity(AgencyDTO agencyDTO) {
        Agency agency = new Agency();
        agency.setId(agencyDTO.getId());
        agency.setName(agencyDTO.getName());
        agency.setDescription(agencyDTO.getDescription());
        agency.setWalletBalance(agencyDTO.getWalletBalance());
        agency.setPropertyIds(agencyDTO.getPropertyIds());
        return agency;
    }

    private AgencyDTO mapToDTO(Agency agency) {
        AgencyDTO agencyDTO = new AgencyDTO();
        agencyDTO.setId(agency.getId());
        agencyDTO.setName(agency.getName());
        agencyDTO.setDescription(agency.getDescription());
        agencyDTO.setWalletBalance(agency.getWalletBalance());
        agencyDTO.setPropertyIds(agency.getPropertyIds());
        return agencyDTO;
    }

    private PropertyDTO mapToPropertyDTO(Property property) {
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setId(property.getId());
        propertyDTO.setName(propertyDTO.getName());
        propertyDTO.setDescription(property.getDescription());
        propertyDTO.setLocation(propertyDTO.getLocation());
        propertyDTO.setPrice(propertyDTO.getPrice());
        propertyDTO.setCoverPhoto(propertyDTO.getCoverPhoto());
        propertyDTO.setCoverVideo(propertyDTO.getCoverVideo());
        propertyDTO.setPanoramas(propertyDTO.getPanoramas());
        propertyDTO.setTitle(propertyDTO.getTitle());
        propertyDTO.setArea(propertyDTO.getArea());
        propertyDTO.setBedrooms(propertyDTO.getBedrooms());
        propertyDTO.setBathrooms(propertyDTO.getBathrooms());
        propertyDTO.setPhotos(propertyDTO.getPhotos());
        propertyDTO.setAmenities(propertyDTO.getAmenities());
        propertyDTO.setFurnishingStatus(propertyDTO.getFurnishingStatus());
        propertyDTO.setAvailability(propertyDTO.getAvailability());
        propertyDTO.setTotalInvested(propertyDTO.getTotalInvested());
        propertyDTO.setAgencyId(propertyDTO.getAgencyId());
        propertyDTO.setAvailableForInvestment(propertyDTO.getAvailableForInvestment());
        propertyDTO.setCreatedAt(propertyDTO.getCreatedAt());
        propertyDTO.setUpdatedAt(propertyDTO.getUpdatedAt());
        return propertyDTO;
    }
}
