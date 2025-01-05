package AoristHomes.AoristHomes.service.UserInvestment;

import AoristHomes.AoristHomes.dto.UserInvestmentDTO;

import java.math.BigDecimal;
import java.util.List;

public interface IUserInvestmentService {
    UserInvestmentDTO addUserInvestment(UserInvestmentDTO userInvestmentDTO);
    UserInvestmentDTO getUserInvestmentById(String userId);
    List<UserInvestmentDTO> getInvestmentsByUserId(String userId);
    UserInvestmentDTO updateInvestmentAmount(String id, BigDecimal newAmount);
    boolean deleteInvestment(String id);
    
}
