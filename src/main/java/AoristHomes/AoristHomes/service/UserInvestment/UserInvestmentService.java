package AoristHomes.AoristHomes.service.UserInvestment;

import AoristHomes.AoristHomes.dto.UserInvestmentDTO;
import AoristHomes.AoristHomes.model.UserInvestment;
import AoristHomes.AoristHomes.repository.UserInvestmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserInvestmentService {
    private final UserInvestmentRepository userInvestmentRepository;

    public UserInvestmentDTO addUserInvestment(UserInvestmentDTO userInvestmentDTO) {
        UserInvestment userInvestment = mapToEntity(userInvestmentDTO);
        userInvestmentRepository.save(userInvestment);
        return mapToDTO(userInvestment);
    }

    public UserInvestmentDTO getUserInvestmentById(String id) {
        Optional<UserInvestment> userInvestmentOptional = userInvestmentRepository.findById(id);
        return userInvestmentOptional.map(this::mapToDTO).orElse(null);
    }

    public List<UserInvestmentDTO> getInvestmentsByUserId(String userId) {
        List<UserInvestment> investments = userInvestmentRepository.findByUserId(userId);
        return investments.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public UserInvestmentDTO updateInvestmentAmount(String id, BigDecimal newAmount) {
        Optional<UserInvestment> userInvestmentOptional = userInvestmentRepository.findById(id);
        if (userInvestmentOptional.isPresent()) {
            UserInvestment userInvestment = userInvestmentOptional.get();
            userInvestment.setInvestmentAmount(newAmount);
            userInvestmentRepository.save(userInvestment);
            return mapToDTO(userInvestment);
        } else {
            return null;
        }
    }

    public boolean deleteInvestment(String id) {
        if (userInvestmentRepository.existsById(id)) {
            userInvestmentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private UserInvestment mapToEntity(UserInvestmentDTO userInvestmentDTO) {
        UserInvestment userInvestment = new UserInvestment();
        userInvestment.setId(userInvestmentDTO.getId());
        userInvestment.setUserId(userInvestmentDTO.getUserId());
        userInvestment.setPropertyId(userInvestmentDTO.getPropertyId());
        userInvestment.setInvestmentAmount(userInvestmentDTO.getInvestmentAmount());
        return userInvestment;
    }

    private UserInvestmentDTO mapToDTO(UserInvestment userInvestment) {
        UserInvestmentDTO userInvestmentDTO = new UserInvestmentDTO();
        userInvestmentDTO.setId(userInvestment.getId());
        userInvestmentDTO.setUserId(userInvestment.getUserId());
        userInvestmentDTO.setPropertyId(userInvestment.getPropertyId());
        userInvestmentDTO.setInvestmentAmount(userInvestment.getInvestmentAmount());
        return userInvestmentDTO;
    }
}