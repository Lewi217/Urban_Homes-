package AoristHomes.AoristHomes.service.user;

import AoristHomes.AoristHomes.dto.LoginRequest;
import AoristHomes.AoristHomes.dto.LoginResponse;
import AoristHomes.AoristHomes.dto.RegisterRequest;
import AoristHomes.AoristHomes.dto.UserDTO;
import AoristHomes.AoristHomes.model.User;

import java.util.Optional;

public interface IUserService {

    UserDTO registerUser(User user);
    LoginResponse logInUser(LoginRequest request);
}
