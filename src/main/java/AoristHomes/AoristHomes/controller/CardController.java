package AoristHomes.AoristHomes.controller;

import AoristHomes.AoristHomes.dto.CardDTO;
import AoristHomes.AoristHomes.service.card.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    // Add a new card
    @PostMapping("/add")
    public ResponseEntity<CardDTO> addCard(@RequestBody CardDTO cardDTO) {
        return ResponseEntity.ok(cardService.addCard(cardDTO));
    }

    // Update card details
    @PutMapping("/{id}")
    public ResponseEntity<CardDTO> updateCard(@PathVariable String id, @RequestBody CardDTO cardDTO) {
        return ResponseEntity.ok(cardService.updateCard(id, cardDTO));
    }

    // Delete card
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable String id) {
        cardService.deleteCard(id);
        return ResponseEntity.noContent().build();
    }
}
