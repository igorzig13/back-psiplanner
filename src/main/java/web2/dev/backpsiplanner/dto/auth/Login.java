package web2.dev.backpsiplanner.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Login {
    private String usernameOrEmail;
    private String password;
}
