package AoristHomes.AoristHomes.controller;

import AoristHomes.AoristHomes.dto.LoginRequest;
import AoristHomes.AoristHomes.dto.LoginResponse;
import AoristHomes.AoristHomes.dto.RegisterRequest;
import AoristHomes.AoristHomes.dto.UserDTO;
import AoristHomes.AoristHomes.model.User;
import AoristHomes.AoristHomes.response.ApiResponse;
import AoristHomes.AoristHomes.service.user.UserService;
import AoristHomes.AoristHomes.utils.exceptions.CustomExceptionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static AoristHomes.AoristHomes.utils.exceptions.ApiResponseUtils.REQUEST_ERROR_MESSAGE;
import static AoristHomes.AoristHomes.utils.exceptions.ApiResponseUtils.REQUEST_SUCCESS_MESSAGE;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        try {
            User user = userService.createUser(request);
            UserDTO userDTO = userService.registerUser(user);
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, userDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = userService.logInUser(request);
            return ResponseEntity.ok(new ApiResponse(REQUEST_SUCCESS_MESSAGE,response));
        } catch (CustomExceptionResponse e) {
            return ResponseEntity.status(FORBIDDEN).body(new ApiResponse(REQUEST_ERROR_MESSAGE,e.getMessage()));
        }
    }


}
