package AoristHomes.AoristHomes.service.card;

import AoristHomes.AoristHomes.dto.CardDTO;
import AoristHomes.AoristHomes.model.Card;
import AoristHomes.AoristHomes.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    // Add card
    public CardDTO addCard(CardDTO cardDTO) {
        Card card = new Card();
        card.setUserId(cardDTO.getUserId());
        card.setCardNumber(cardDTO.getCardNumber());
        card.setCardHolderName(cardDTO.getCardHolderName());
        card.setExpiryDate(cardDTO.getExpiryDate());
        card.setCvv(cardDTO.getCvv());
        
        cardRepository.save(card);
        return mapToDTO(card);
    }

    // get card by userId
    public CardDTO getCardByUserId(String userId) {
        Card card = cardRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Card not found"));
        return mapToDTO(card);
    }

    // Edit card
    public CardDTO updateCard(String id, CardDTO cardDTO) {
        Card card = cardRepository.findById(id).orElseThrow(() -> new RuntimeException("Card not found"));
        card.setCardNumber(cardDTO.getCardNumber());
        card.setCardHolderName(cardDTO.getCardHolderName());
        card.setExpiryDate(cardDTO.getExpiryDate());
        card.setCvv(cardDTO.getCvv());

        cardRepository.save(card);
        return mapToDTO(card);
    }

    // Delete card
    public void deleteCard(String id) {
        Card card = cardRepository.findById(id).orElseThrow(() -> new RuntimeException("Card not found"));
        cardRepository.delete(card);
    }

    // Map model to DTO
    private CardDTO mapToDTO(Card card) {
        CardDTO cardDTO = new CardDTO();
        cardDTO.setId(card.getId());
        cardDTO.setUserId(card.getUserId());
        cardDTO.setCardNumber(card.getCardNumber());
        cardDTO.setCardHolderName(card.getCardHolderName());
        cardDTO.setExpiryDate(card.getExpiryDate());
        cardDTO.setCvv(card.getCvv());
        return cardDTO;
    }
}
