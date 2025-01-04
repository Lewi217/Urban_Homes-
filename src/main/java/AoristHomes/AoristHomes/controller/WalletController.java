package AoristHomes.AoristHomes.controller;

import AoristHomes.AoristHomes.dto.WalletDTO;
import AoristHomes.AoristHomes.response.ApiResponse;
import AoristHomes.AoristHomes.service.wallet.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static AoristHomes.AoristHomes.utils.exceptions.ApiResponseUtils.REQUEST_ERROR_MESSAGE;
import static AoristHomes.AoristHomes.utils.exceptions.ApiResponseUtils.REQUEST_SUCCESS_MESSAGE;

@RestController
@RequestMapping("/api/wallets")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;

    @PostMapping("/deposit")
    public ResponseEntity<ApiResponse> depositFunds(@RequestBody WalletDTO walletRequest) {
        try {
            WalletDTO updatedWallet = walletService.depositFunds(walletRequest.getUserId(), walletRequest.getAmount());
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, updatedWallet));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }


    @PostMapping("/withdraw")
    public ResponseEntity<ApiResponse> withdrawFunds(@RequestBody WalletDTO walletRequest) {
        try {
            WalletDTO updatedWallet = walletService.withdrawFunds(walletRequest.getUserId(), walletRequest.getAmount());
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, updatedWallet));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getWalletById(@PathVariable String id) {
        try {
            WalletDTO wallet = walletService.getWalletById(id);
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, wallet));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }

}
