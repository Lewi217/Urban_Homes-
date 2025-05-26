package AoristHomes.AoristHomes.service.UserInvestment;

import AoristHomes.AoristHomes.dto.UserInvestmentDTO;
import AoristHomes.AoristHomes.model.UserInvestment;
import AoristHomes.AoristHomes.repository.UserInvestmentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserInvestmentService implements IUserInvestmentService {
    private final UserInvestmentRepository userInvestmentRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserInvestmentDTO addUserInvestment(UserInvestmentDTO userInvestmentDTO) {
        UserInvestment userInvestment = mapToEntity(userInvestmentDTO);
        userInvestmentRepository.save(userInvestment);
        return mapToDTO(userInvestment);
    }
    @Override
    public UserInvestmentDTO getUserInvestmentById(String id) {
        Optional<UserInvestment> userInvestmentOptional = userInvestmentRepository.findById(id);
        return userInvestmentOptional.map(this::mapToDTO).orElse(null);
    }
    @Override
    public List<UserInvestmentDTO> getInvestmentsByUserId(String userId) {
        List<UserInvestment> investments = userInvestmentRepository.findByUserId(userId);
        return investments.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
    @Override
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

    @Override
    public boolean deleteInvestment(String id) {
        if (userInvestmentRepository.existsById(id)) {
            userInvestmentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private UserInvestment mapToEntity(UserInvestmentDTO userInvestmentDTO) {
        return modelMapper.map(userInvestmentDTO, UserInvestment.class);
    }

    private UserInvestmentDTO mapToDTO(UserInvestment userInvestment) {
        return modelMapper.map(userInvestment, UserInvestmentDTO.class);
    }
}