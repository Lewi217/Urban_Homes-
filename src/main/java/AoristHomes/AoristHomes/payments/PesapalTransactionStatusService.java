package AoristHomes.AoristHomes.payments;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PesapalTransactionStatusService {

    private final PesapalAuthServices pesapalAuthServices;
    private final String statusUrl = "https://pay.pesapal.com/v3/api/Transactions/GetTransactionStatus";

    public String checkPaymentStatus(String orderTrackingId){
        RestTemplate restTemplate  = new RestTemplate();

        String accessToken = pesapalAuthServices.getAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("order_tracking_id", orderTrackingId);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(statusUrl, request, Map.class);

        return (String) response.getBody().get("Status");
    }
}
