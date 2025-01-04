package AoristHomes.AoristHomes.service.user;

import AoristHomes.AoristHomes.dto.LoginRequest;
import AoristHomes.AoristHomes.dto.LoginResponse;
import AoristHomes.AoristHomes.dto.RegisterRequest;
import AoristHomes.AoristHomes.dto.UserDTO;
import AoristHomes.AoristHomes.model.User;
import AoristHomes.AoristHomes.repository.UserRepository;
import AoristHomes.AoristHomes.security.JwtUtil;
import AoristHomes.AoristHomes.utils.exceptions.CustomExceptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public UserDTO registerUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        return mapToDTO(user);
    }

    @Override
    public LoginResponse logInUser(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtUtil.generateToken(userDetails);
            HashMap<String, Object> claims = new HashMap<>();
            String refreshToken = jwtUtil.generateRefreshToken(claims, userDetails);
            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new CustomExceptionResponse("User not found"));
            UserDTO userDTO = mapToDTO(user);
            return LoginResponse.builder()
                    .token(token)
                    .refreshToken(refreshToken)
                    .user(userDTO)
                    .build();

        } catch (AuthenticationException e) {
            throw new CustomExceptionResponse("Authentication failed: " + e.getMessage());
        } catch (CustomExceptionResponse e) {
            throw e;
        } catch (Exception e) {
            throw new CustomExceptionResponse("Unexpected error: " + e.getMessage());
        }
    }





    private UserDTO mapToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setRoles(user.getRoles());
        userDTO.setWalletBalance(user.getWalletBalance());
        return userDTO;
    }
}
