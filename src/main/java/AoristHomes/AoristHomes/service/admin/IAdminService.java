package AoristHomes.AoristHomes.service.admin;

import AoristHomes.AoristHomes.dto.AgencyDTO;
import AoristHomes.AoristHomes.dto.PropertyDTO;
import AoristHomes.AoristHomes.dto.UserInvestmentDTO;

import java.util.List;
import java.util.Map;

public interface IAdminService {
    Map<String, Object> getDashboardMetrics();
    List<AgencyDTO> getAllAgencies();
    void deleteAgency(String id);
    List<PropertyDTO> getAllProperties();
    void deleteProperty(String id);
    List<UserInvestmentDTO> getInvestments(String userId, String propertyId);

}
