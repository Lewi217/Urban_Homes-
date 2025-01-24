package AoristHomes.AoristHomes.controller;

import AoristHomes.AoristHomes.dto.AgencyDTO;
import AoristHomes.AoristHomes.dto.InvestmentRequestDTO;
import AoristHomes.AoristHomes.dto.PropertyDTO;
import AoristHomes.AoristHomes.dto.UserInvestmentDTO;
import AoristHomes.AoristHomes.response.ApiResponse;
import AoristHomes.AoristHomes.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static AoristHomes.AoristHomes.utils.exceptions.ApiResponseUtils.REQUEST_ERROR_MESSAGE;
import static AoristHomes.AoristHomes.utils.exceptions.ApiResponseUtils.REQUEST_SUCCESS_MESSAGE;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

   @GetMapping("/dashboard-metrics")
    public ResponseEntity<ApiResponse> getDashboardMetrics(){
       try{
           Map<String, Object> metrics = adminService.getDashboardMetrics();
           return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, metrics));
       }catch(Exception e){
           return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
       }
   }

   @GetMapping("/agencies")
    public ResponseEntity<ApiResponse> getAllAgencies(){
       try{
           List<AgencyDTO> agencies = adminService.getAllAgencies();
           return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, agencies));
       }catch (Exception e){
           return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
       }
   }

   @DeleteMapping("/agencies/{id}")
    public ResponseEntity<ApiResponse> deleteAgency(@PathVariable String id){
       try{
           adminService.deleteAgency(id);
           return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, true));
       } catch (Exception e) {
           return ResponseEntity.ok().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
       }
   }

   @GetMapping("/properties")
    public ResponseEntity<ApiResponse> getAllProperties(){
       try{
           List<PropertyDTO> properties = adminService.getAllProperties();
           return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, properties));
       } catch (Exception e) {
           return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
       }
   }

   @DeleteMapping("/properties/{id}")
    public ResponseEntity<ApiResponse> deleteProperty(@PathVariable String id){
       try{
           adminService.deleteProperty(id);
           return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, true));
       }catch (Exception e){
           return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
       }
   }

    @PostMapping("/investments")
    public ResponseEntity<ApiResponse> getInvestments(@RequestBody InvestmentRequestDTO investmentRequestDTO) {
        try {
            List<UserInvestmentDTO> investments = adminService.getInvestments(
                    investmentRequestDTO.getUserId(),
                    investmentRequestDTO.getPropertyId()
            );
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, investments));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }

}
