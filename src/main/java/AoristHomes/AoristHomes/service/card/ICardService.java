package AoristHomes.AoristHomes.service.card;

import AoristHomes.AoristHomes.dto.CardDTO;

public interface ICardService {
    CardDTO addCard(CardDTO cardDTO);
    CardDTO getCardByUserId(String userId);
    CardDTO updateCard(String id, CardDTO cardDTO);
    void deleteCard(String id);
}
