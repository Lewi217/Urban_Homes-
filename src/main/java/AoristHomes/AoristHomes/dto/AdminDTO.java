package AoristHomes.AoristHomes.dto;

import AoristHomes.AoristHomes.utils.enums.Role;
import lombok.Data;

@Data
public class AdminDTO {
    private String id;
    private String fullName;
    private String email;
    private Role role;
}
