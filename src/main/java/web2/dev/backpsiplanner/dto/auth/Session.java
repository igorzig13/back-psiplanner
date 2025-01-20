package web2.dev.backpsiplanner.dto.auth;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class Session {
    private String login;
    private String token;
    private Set<String> roles;
    private Long userId;
    private Long entityId;
}
