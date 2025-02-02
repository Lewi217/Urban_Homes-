package AoristHomes.AoristHomes.payments;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

@Service
public class PesapalAuthServices {
    @Value("${consumer_key}")
    private String consumerKey;

    @Value("${consumer_secret}")
    private String consumerSecret;

    private final String authUrl = "https://pay.pesapal.com/v3/api/Auth/RequestToken";

    public String getAccessToken(){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("consumer_key",consumerKey);
        requestBody.put("consumer_secret",consumerSecret);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(authUrl, request, Map.class);

        if(response.getStatusCode() == HttpStatus.OK){
            return (String) response.getBody().get("token");
        }else{
            throw new RuntimeException("Failed to get Pesapal access token");
        }

    }

}
