package AoristHomes.AoristHomes.service.user;

import AoristHomes.AoristHomes.dto.*;
import AoristHomes.AoristHomes.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    UserDTO registerUser(User user);
    LoginResponse logInUser(LoginRequest request);
    List<HoldingDTO> getHoldingsByUserId(String userId);
}
