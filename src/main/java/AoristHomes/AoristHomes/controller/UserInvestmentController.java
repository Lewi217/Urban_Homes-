package AoristHomes.AoristHomes.controller;

import AoristHomes.AoristHomes.dto.UserInvestmentDTO;
import AoristHomes.AoristHomes.service.UserInvestment.UserInvestmentService;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-investments")
@RequiredArgsConstructor
public class UserInvestmentController {
    private final UserInvestmentService userInvestmentService;

    @PostMapping
    public ResponseEntity<UserInvestmentDTO> addUserInvestment(@RequestBody UserInvestmentDTO userInvestmentDTO) {
        try {
            UserInvestmentDTO createdUserInvestment = userInvestmentService.addUserInvestment(userInvestmentDTO);
            return ResponseEntity.ok(createdUserInvestment);
        }
        
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInvestmentDTO> getUserInvestmentById(@PathVariable String id) {
        try {
            UserInvestmentDTO userInvestmentDTO = userInvestmentService.getUserInvestmentById(id);
            return ResponseEntity.ok(userInvestmentDTO);
        }
        
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserInvestmentDTO>> getInvestmentsByUserId(@PathVariable String userId) {
        try {
            List<UserInvestmentDTO> investments = userInvestmentService.getInvestmentsByUserId(userId);
            return ResponseEntity.ok(investments);
        }
        
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/amount")
    public ResponseEntity<UserInvestmentDTO> updateInvestmentAmount(@PathVariable String id, @RequestBody BigDecimal newAmount) {
        try {
            UserInvestmentDTO updatedInvestment = userInvestmentService.updateInvestmentAmount(id, newAmount);
            return ResponseEntity.ok(updatedInvestment);
        }
        
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvestment(@PathVariable String id) {
        try {
            userInvestmentService.deleteInvestment(id);
            return ResponseEntity.noContent().build();
        }
        
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}