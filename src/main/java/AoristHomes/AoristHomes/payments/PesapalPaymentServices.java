package AoristHomes.AoristHomes.payments;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PesapalPaymentServices {

    private final PesapalAuthServices pesapalAuthServices;
    private final String paymentUrl = "https://pay.pesapal.com/v3/api/Transactions/SubmitOrderRequest";



    public String initiatePayment(String orderTrackingId, String amount, String phoneNumber, String email){
        RestTemplate restTemplate = new RestTemplate();

        String accessToken = pesapalAuthServices.getAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", orderTrackingId);
        requestBody.put("currency", "KES");
        requestBody.put("amount", amount);
        requestBody.put("description", "Payment for Aorist Homes");
        requestBody.put("callback_url", "https://eb9b-196-216-90-169.ngrok-free.app/api/pesapal/callback");
        requestBody.put("notification_id", "78220df4-c10a-4106-8f57-dc353e63d87f"); // Get from Pesapal portal
        requestBody.put("billing_address", Map.of("email", email, "phone_number", phoneNumber));

        HttpEntity<Map<String, Object>> request =  new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(paymentUrl,request, Map.class);

        if(response.getStatusCode() == HttpStatus.OK){
            return (String) response.getBody().get("order_tracking_id");
        }else{
            throw new RuntimeException("Failed to initiate Pesapal payment");
        }



    }
}
