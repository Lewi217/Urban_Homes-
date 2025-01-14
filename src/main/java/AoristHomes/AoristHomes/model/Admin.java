package AoristHomes.AoristHomes.model;

import AoristHomes.AoristHomes.utils.enums.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

;
import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "admins")
public class Admin {
    @Id
    private String id;
    private String fullName;
    private String email;
    private String password;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
    private Role role = Role.ADMIN;

}
