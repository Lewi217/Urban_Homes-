package AoristHomes.AoristHomes.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class UserDTO {
    private String id;
    private String fullName;
    private String email;
    private List<String> roles;
    private Double walletBalance;
}
