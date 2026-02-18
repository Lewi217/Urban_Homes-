package AoristHomes.AoristHomes.service.admin;

import AoristHomes.AoristHomes.dto.*;
import AoristHomes.AoristHomes.model.Admin;
import AoristHomes.AoristHomes.model.UserInvestment;
import AoristHomes.AoristHomes.repository.*;
import AoristHomes.AoristHomes.security.JwtUtil;
import AoristHomes.AoristHomes.utils.exceptions.CustomExceptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminService implements IAdminService{

    private final AgencyRepository agencyRepository;
    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;
    private final UserInvestmentRepository userInvestmentRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public AdminLoginResponse loginAdmin(LoginRequest request) {
        Admin admin = adminRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new CustomExceptionResponse("Admin not found"));
        if (!passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
            throw new CustomExceptionResponse("Invalid credentials");
        }
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(admin.getEmail())
                .password(admin.getPassword())
                .roles("ADMIN")
                .build();
        String token        = jwtUtil.generateToken(userDetails);
        String refreshToken = jwtUtil.generateRefreshToken(new HashMap<>(), userDetails);
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setId(admin.getId());
        adminDTO.setFullName(admin.getFullName());
        adminDTO.setEmail(admin.getEmail());
        adminDTO.setRole(admin.getRole());

        return AdminLoginResponse.builder()
                .token(token)
                .refreshToken(refreshToken)
                .admin(adminDTO)
                .build();
    }
    @Override
    public Map<String, Object> getDashboardMetrics(){
        Map<String, Object> metrics = new HashMap<>();

        metrics.put("totalProfit", calculateTotalProfit());
        metrics.put("totalUsers", userRepository.count());
        metrics.put("totalAgencies", agencyRepository.count());
        metrics.put("totalProperties", propertyRepository.count());

        return metrics;
    }

    @Override
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

    @Override
    public void deleteAgency(String id) {
        agencyRepository.deleteById(id);
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        return propertyRepository.findAll().stream().map(property -> {
            PropertyDTO dto = new PropertyDTO();
            dto.setId(property.getId());
            dto.setName(property.getName());
            dto.setDescription(property.getDescription());
            dto.setLocation(property.getLocation());
            dto.setPrice(property.getPrice());
            dto.setCoverPhoto(property.getCoverPhoto());
            dto.setCoverVideo(property.getCoverVideo());
            dto.setPanoramas(property.getPanoramas());
            dto.setTitle(property.getTitle());
            dto.setArea(property.getArea());
            dto.setBedrooms(property.getBedrooms());
            dto.setBathrooms(property.getBathrooms());
            dto.setPhotos(property.getPhotos());
            dto.setAmenities(property.getAmenities());
            dto.setFurnishingStatus(property.getFurnishingStatus());
            dto.setAvailability(property.getAvailability());
            dto.setTotalInvested(property.getTotalInvested());
            dto.setAgencyId(property.getAgencyId());
            dto.setAvailableForInvestment(property.getAvailableForInvestment());
            dto.setCreatedAt(property.getCreatedAt());
            dto.setUpdatedAt(property.getUpdatedAt());
            return dto;
        }).toList();
    }

    @Override
    public void deleteProperty(String id) {
        propertyRepository.deleteById(id);
    }

    @Override
    public List<UserInvestmentDTO> getInvestments(String userId, String propertyId) {
        List<UserInvestment> userInvestments;

        if (userId != null && propertyId != null) {
            userInvestments = userInvestmentRepository.findByUserIdAndPropertyId(userId, propertyId);
        } else if (userId != null) {
            userInvestments = userInvestmentRepository.findByUserId(userId);
        } else if (propertyId != null) {
            userInvestments = userInvestmentRepository.findByPropertyId(propertyId);
        } else {
            userInvestments = userInvestmentRepository.findAll();
        }


        return userInvestments.stream().map(investment -> {
            UserInvestmentDTO dto = new UserInvestmentDTO();
            dto.setId(investment.getId());
            dto.setUserId(investment.getUserId());
            dto.setPropertyId(investment.getPropertyId());
            dto.setInvestmentAmount(investment.getInvestmentAmount());
            return dto;
        }).toList();
    }

    private BigDecimal calculateTotalProfit() {
        return userInvestmentRepository.findAll().stream()
                .map(investment -> investment.getInvestmentAmount().multiply(BigDecimal.valueOf(0.05))) // 5% commission
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}

