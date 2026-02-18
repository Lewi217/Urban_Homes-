package AoristHomes.AoristHomes.controller;

import AoristHomes.AoristHomes.dto.*;
import AoristHomes.AoristHomes.model.Admin;
import AoristHomes.AoristHomes.repository.AdminRepository;
import AoristHomes.AoristHomes.response.ApiResponse;
import AoristHomes.AoristHomes.service.admin.AdminService;
import AoristHomes.AoristHomes.utils.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/setup")
    public ResponseEntity<ApiResponse> setupAdmin(@RequestBody LoginRequest request) {
        try {
            // Prevent duplicate admin
            if (adminRepository.findByEmail(request.getEmail()).isPresent()) {
                return ResponseEntity.badRequest()
                        .body(new ApiResponse(REQUEST_ERROR_MESSAGE, "Admin already exists"));
            }

            Admin admin = new Admin();
            admin.setFullName("Super Admin");
            admin.setEmail(request.getEmail());
            admin.setPassword(passwordEncoder.encode(request.getPassword()));
            admin.setRole(Role.ADMIN);
            adminRepository.save(admin);

            return ResponseEntity.ok(new ApiResponse(REQUEST_SUCCESS_MESSAGE, "Admin created successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> adminLogin(@RequestBody LoginRequest request) {
        try {
            AdminLoginResponse response = adminService.loginAdmin(request);
            return ResponseEntity.ok(new ApiResponse(REQUEST_SUCCESS_MESSAGE, response));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }

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
