package AoristHomes.AoristHomes.service.user;

import AoristHomes.AoristHomes.dto.HoldingDTO;
import AoristHomes.AoristHomes.dto.LoginRequest;
import AoristHomes.AoristHomes.dto.LoginResponse;
import AoristHomes.AoristHomes.dto.UserDTO;
import AoristHomes.AoristHomes.model.Property;
import AoristHomes.AoristHomes.model.UserInvestment;
import AoristHomes.AoristHomes.model.User;
import AoristHomes.AoristHomes.repository.PropertyRepository;
import AoristHomes.AoristHomes.repository.UserInvestmentRepository;
import AoristHomes.AoristHomes.repository.UserRepository;
import AoristHomes.AoristHomes.security.JwtUtil;
import AoristHomes.AoristHomes.utils.exceptions.CustomExceptionResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;
    private final UserInvestmentRepository userInvestmentRepository;
    private final PropertyRepository propertyRepository;

    @Override
    public UserDTO registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User saved = userRepository.save(user);
        return mapToUserDTO(saved);
    }

    @Override
    public LoginResponse logInUser(LoginRequest request) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            UserDetails ud = (UserDetails) auth.getPrincipal();
            String token        = jwtUtil.generateToken(ud);
            String refreshToken = jwtUtil.generateRefreshToken(new HashMap<>(), ud);

            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new CustomExceptionResponse("User not found"));
            return LoginResponse.builder()
                    .token(token)
                    .refreshToken(refreshToken)
                    .user(mapToUserDTO(user))
                    .build();
        } catch (AuthenticationException e) {
            throw new CustomExceptionResponse("Authentication failed: " + e.getMessage());
        }
    }

    @Override
    public List<HoldingDTO> getHoldingsByUserId(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomExceptionResponse("User not found"));
        List<UserInvestment> userInvestments = userInvestmentRepository.findByUserId(userId);
        return userInvestments.stream().map(inv -> {
            Property p = propertyRepository.findById(inv.getPropertyId())
                    .orElseThrow(() -> new CustomExceptionResponse("Property not found"));
            BigDecimal total = userInvestmentRepository.findByPropertyId(inv.getPropertyId())
                    .stream()
                    .map(UserInvestment::getInvestmentAmount)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal share = total.compareTo(BigDecimal.ZERO) > 0
                    ? inv.getInvestmentAmount()
                    .divide(total, 4, RoundingMode.HALF_UP)
                    .multiply(BigDecimal.valueOf(100))
                    : BigDecimal.ZERO;

            return new HoldingDTO(
                    p.getId(),
                    p.getName(),
                    inv.getInvestmentAmount(),
                    total,
                    share
            );
        }).collect(Collectors.toList());
    }

    private UserDTO mapToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
}
