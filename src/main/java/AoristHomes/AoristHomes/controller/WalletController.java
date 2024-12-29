package AoristHomes.AoristHomes.controller;

import AoristHomes.AoristHomes.dto.WalletDTO;
import AoristHomes.AoristHomes.response.ApiResponse;
import AoristHomes.AoristHomes.service.wallet.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/wallets")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;

    @PostMapping("/deposit")
    public ResponseEntity<ApiResponse<WalletDTO>> depositFunds(@RequestParam String userId, @RequestParam BigDecimal amount) {
        try {
            WalletDTO updatedWallet = walletService.depositFunds(userId, amount);
            return ResponseEntity.ok(new ApiResponse<>("Deposit successful", updatedWallet));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>("Error during deposit", e.getMessage()));
        }
    }

    @PostMapping("/withdraw")
    public ResponseEntity<ApiResponse<WalletDTO>> withdrawFunds(@RequestParam String userId, @RequestParam BigDecimal amount) {
        try {
            WalletDTO updatedWallet = walletService.withdrawFunds(userId, amount);
            return ResponseEntity.ok(new ApiResponse<>("Withdrawal successful", updatedWallet));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse<>("Error during withdrawal", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<WalletDTO>> getWalletById(@PathVariable String id) {
        try {
            WalletDTO wallet = walletService.getWalletById(id);
            return ResponseEntity.ok(new ApiResponse<>("Wallet fetched successfully", wallet));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>("Wallet not found", e.getMessage()));
        }
    }
}
