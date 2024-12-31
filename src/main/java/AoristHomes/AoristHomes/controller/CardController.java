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
        try {
            return ResponseEntity.ok(cardService.addCard(cardDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Get card by userId
    @GetMapping("/user/{userId}")
    public ResponseEntity<CardDTO> getCardByUserId(@PathVariable String userId) {
        try {
            return ResponseEntity.ok(cardService.getCardByUserId(userId));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    // Update card details
    @PutMapping("/{id}")
    public ResponseEntity<CardDTO> updateCard(@PathVariable String id, @RequestBody CardDTO cardDTO) {
        try {
            return ResponseEntity.ok(cardService.updateCard(id, cardDTO));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete card
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable String id) {
        try {
            cardService.deleteCard(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
