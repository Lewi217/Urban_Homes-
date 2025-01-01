package AoristHomes.AoristHomes.controller;

import AoristHomes.AoristHomes.dto.CardDTO;
import AoristHomes.AoristHomes.response.ApiResponse;
import AoristHomes.AoristHomes.service.card.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static AoristHomes.AoristHomes.utils.exceptions.ApiResponseUtils.REQUEST_ERROR_MESSAGE;
import static AoristHomes.AoristHomes.utils.exceptions.ApiResponseUtils.REQUEST_SUCCESS_MESSAGE;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    // Add a new card
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCard(@RequestBody CardDTO cardDTO) {
        try {
            CardDTO addedCard = cardService.addCard(cardDTO);
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, addedCard));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }

    // Get card by userId
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> getCardByUserId(@PathVariable String userId) {
        try {
            CardDTO cardDTO = cardService.getCardByUserId(userId);
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, cardDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }
    // Update card details
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCard(@PathVariable String id, @RequestBody CardDTO cardDTO) {
        try {
            CardDTO updatedCard = cardService.updateCard(id, cardDTO);
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, updatedCard));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }

    // Delete card
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCard(@PathVariable String id) {
        try {
            cardService.deleteCard(id);
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }
}
