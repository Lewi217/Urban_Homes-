package AoristHomes.AoristHomes.controller;

import AoristHomes.AoristHomes.dto.AgencyDTO;
import AoristHomes.AoristHomes.response.ApiResponse;
import AoristHomes.AoristHomes.service.agency.AgencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static AoristHomes.AoristHomes.utils.exceptions.ApiResponseUtils.REQUEST_ERROR_MESSAGE;
import static AoristHomes.AoristHomes.utils.exceptions.ApiResponseUtils.REQUEST_SUCCESS_MESSAGE;

@RestController
@RequestMapping("/api/agencies")
@RequiredArgsConstructor
public class AgencyController {
    private final AgencyService agencyService;

    @PostMapping
    public ResponseEntity<ApiResponse> createAgency(@RequestBody AgencyDTO agencyDTO){
        try{
            AgencyDTO createdAgency = agencyService.createAgency(agencyDTO);
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, createdAgency));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getAgency(@PathVariable String id) {
        try {
            AgencyDTO agencyDTO = agencyService.getAgencyById(id);
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, agencyDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateAgency(@PathVariable String id, @RequestBody AgencyDTO agencyDTO) {
        try {
            AgencyDTO updatedAgency = agencyService.updateAgency(id, agencyDTO);
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, updatedAgency));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAgency(@PathVariable String id) {
        try {
            boolean deleted = agencyService.deleteAgency(id);
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, deleted));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<ApiResponse> withdrawFromWallet(@PathVariable String id, @RequestParam BigDecimal amount) {
        try {
            boolean success = agencyService.withdrawFromWallet(id, amount);
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, success));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }

    @GetMapping("/{id}/properties")
    public ResponseEntity<ApiResponse> listPropertiesForInvestment(@PathVariable String id) {
        try {
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, agencyService.listPropertiesForInvestment(id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }

}
