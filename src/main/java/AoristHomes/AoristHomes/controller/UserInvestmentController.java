package AoristHomes.AoristHomes.controller;

import AoristHomes.AoristHomes.dto.UserInvestmentDTO;
import AoristHomes.AoristHomes.response.ApiResponse;
import AoristHomes.AoristHomes.service.UserInvestment.UserInvestmentService;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static AoristHomes.AoristHomes.utils.exceptions.ApiResponseUtils.REQUEST_ERROR_MESSAGE;
import static AoristHomes.AoristHomes.utils.exceptions.ApiResponseUtils.REQUEST_SUCCESS_MESSAGE;


@RestController
@RequestMapping("/api/user-investments")
@RequiredArgsConstructor
public class UserInvestmentController {
    private final UserInvestmentService userInvestmentService;

    @PostMapping
    public ResponseEntity<ApiResponse> addUserInvestment(@RequestBody UserInvestmentDTO userInvestmentDTO) {
        try {
            UserInvestmentDTO createdUserInvestment = userInvestmentService.addUserInvestment(userInvestmentDTO);
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE,createdUserInvestment));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getUserInvestmentById(@PathVariable String id) {
        try {
            UserInvestmentDTO userInvestmentDTO = userInvestmentService.getUserInvestmentById(id);
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE,userInvestmentDTO));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> getInvestmentsByUserId(@PathVariable String userId) {
        try {
            List<UserInvestmentDTO> investments = userInvestmentService.getInvestmentsByUserId(userId);
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE,investments));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }

    @PutMapping("/{id}/amount")
    public ResponseEntity<ApiResponse> updateInvestmentAmount(@PathVariable String id, @RequestBody BigDecimal newAmount) {
        try {
            UserInvestmentDTO updatedInvestment = userInvestmentService.updateInvestmentAmount(id, newAmount);
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE,updatedInvestment));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteInvestment(@PathVariable String id) {
        try {
            userInvestmentService.deleteInvestment(id);
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE,deleteInvestment(id)));
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }
}