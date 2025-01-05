package AoristHomes.AoristHomes.service.property;

import AoristHomes.AoristHomes.dto.PropertyDTO;

public interface IPropertyService {
    PropertyDTO addProperty(PropertyDTO propertyDTO);
    PropertyDTO getPropertyById(String id);
    PropertyDTO updateProperty(String id, PropertyDTO propertyDTO);
    boolean deleteProperty(String id);
}
