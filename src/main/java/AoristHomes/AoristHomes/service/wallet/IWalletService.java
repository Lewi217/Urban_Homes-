package AoristHomes.AoristHomes.service.wallet;

import AoristHomes.AoristHomes.dto.WalletDTO;

import java.math.BigDecimal;

public interface IWalletService {
    WalletDTO depositFunds(String userId, BigDecimal amount);
    WalletDTO withdrawFunds(String userId, BigDecimal amount);
    WalletDTO getWalletById(String id);
}