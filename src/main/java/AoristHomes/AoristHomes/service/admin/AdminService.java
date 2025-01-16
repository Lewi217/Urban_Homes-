package AoristHomes.AoristHomes.service.admin;

import AoristHomes.AoristHomes.dto.AgencyDTO;
import AoristHomes.AoristHomes.model.Property;
import AoristHomes.AoristHomes.repository.AgencyRepository;
import AoristHomes.AoristHomes.repository.PropertyRepository;
import AoristHomes.AoristHomes.repository.UserInvestmentRepository;
import AoristHomes.AoristHomes.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AgencyRepository agencyRepository;
    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;
    private final UserInvestmentRepository userInvestmentRepository;

    public Map<String, Object> getDashboardMetrics(){
        Map<String, Object> metrics = new HashMap<>();

        metrics.put("totalProfit", calculateTotalProfit());
        metrics.put("totalUsers", userRepository.count());
        metrics.put("totalAgencies", agencyRepository.count());
        metrics.put("totalProperties", propertyRepository.count());

        return metrics;
    }

    public List<AgencyDTO> getAllAgencies() {
        return agencyRepository.findAll().stream().map(agency ->
                new AgencyDTO(
                        agency.getId(),
                        agency.getName(),
                        agency.getEmail(),
                        agency.getDescription(),
                        agency.getWalletBalance(),
                        agency.getPropertyIds()
                )
        ).toList();
    }

}

