package AoristHomes.AoristHomes.service.agency;

import AoristHomes.AoristHomes.dto.AgencyDTO;
import AoristHomes.AoristHomes.dto.PropertyDTO;

import java.math.BigDecimal;
import java.util.List;

public interface IAgencyService {
    AgencyDTO createAgency(AgencyDTO agencyDTO);
    AgencyDTO getAgencyById(String id);
    AgencyDTO updateAgency(String id, AgencyDTO agencyDTO);
    boolean deleteAgency(String id);
    boolean withdrawFromWallet(String agencyId, BigDecimal amount);
    List<PropertyDTO> listPropertiesForInvestment(String agencyId);
}
