package AoristHomes.AoristHomes.controller;

import AoristHomes.AoristHomes.dto.*;
import AoristHomes.AoristHomes.model.User;
import AoristHomes.AoristHomes.response.ApiResponse;
import AoristHomes.AoristHomes.service.user.UserService;
import AoristHomes.AoristHomes.utils.exceptions.CustomExceptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static AoristHomes.AoristHomes.utils.exceptions.ApiResponseUtils.REQUEST_ERROR_MESSAGE;
import static AoristHomes.AoristHomes.utils.exceptions.ApiResponseUtils.REQUEST_SUCCESS_MESSAGE;
import static org.springframework.http.HttpStatus.FORBIDDEN;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        try {
            User user = new User();
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            UserDTO userDTO = userService.registerUser(user);

            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, userDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse loginResponse = userService.logInUser(request);
            return ResponseEntity.ok(new ApiResponse(REQUEST_SUCCESS_MESSAGE,loginResponse));
        } catch (CustomExceptionResponse e) {
            return ResponseEntity.status(FORBIDDEN).body(new ApiResponse(REQUEST_ERROR_MESSAGE,e.getMessage()));
        }
    }

}
