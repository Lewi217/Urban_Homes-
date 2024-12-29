package AoristHomes.AoristHomes.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse<W> {
   private String message;
   private Object data;
}
