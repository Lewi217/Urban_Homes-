package AoristHomes.AoristHomes.payments;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/pesapal")
public class PesapalCallbackController {

    @PostMapping("/callback")
    public void handlePesapalCallback(@RequestBody Map<String, Object> callbackData){
        System.out.println("Pesapal Payment Callback Received: "+ callbackData);

        if(callbackData.containsKey("order_tracking_id")){
            String orderTrackingId = (String) callbackData.get("order_tracking_id");
            String status = (String) callbackData.get("status");

            if("COMPLETED".equals(status)){
                System.out.println("Payment Successful for Order: "+ orderTrackingId);
            }else{
                System.out.println("Payment Failed: " + status);
            }
        }
    }
}
