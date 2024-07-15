package my.api_gateway.client;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class UserDTO {

        private int id;
        private String username;
        private String password;
        private String role;
        private String phone;

}
