package AoristHomes.AoristHomes.service.wallet;

import AoristHomes.AoristHomes.dto.WalletDTO;
import AoristHomes.AoristHomes.model.Wallet;
import AoristHomes.AoristHomes.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class WalletService implements IWalletService {
    private final WalletRepository walletRepository;

    @Override
    public WalletDTO depositFunds(String userId, BigDecimal amount) {
        Wallet wallet = walletRepository.findByUserId(userId).orElseGet(() -> createWallet(userId));
        wallet.setBalance(wallet.getBalance().add(amount));
        walletRepository.save(wallet);
        return mapToDTO(wallet);
    }



    @Override
    public WalletDTO withdrawFunds(String userId, BigDecimal amount) {
        Wallet wallet = walletRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Wallet not found"));
        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient funds");
        }
        wallet.setBalance(wallet.getBalance().subtract(amount));
        walletRepository.save(wallet);
        return mapToDTO(wallet);
    }

    @Override
    public WalletDTO getWalletById(String id) {
        Wallet wallet = walletRepository.findById(id).orElseThrow(() -> new RuntimeException("Wallet not found"));
        return mapToDTO(wallet);
    }


    private Wallet createWallet(String userId) {
        Wallet newWallet = new Wallet();
        newWallet.setUserId(userId);
        newWallet.setBalance(BigDecimal.ZERO);
        newWallet.setAgencyId("");
        walletRepository.save(newWallet);
        return newWallet;
    }

    private WalletDTO mapToDTO(Wallet wallet) {
        WalletDTO walletDTO = new WalletDTO();
        walletDTO.setId(wallet.getId());
        walletDTO.setUserId(wallet.getUserId());
        walletDTO.setAgencyId(wallet.getAgencyId());
        walletDTO.setBalance(wallet.getBalance());
        return walletDTO;
    }
}
