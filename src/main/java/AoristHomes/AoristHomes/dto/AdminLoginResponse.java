package AoristHomes.AoristHomes.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminLoginResponse {
    private String token;
    private String refreshToken;
    private AdminDTO admin;
}
