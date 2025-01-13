package web2.dev.backpsiplanner.config.security;

import lombok.Getter;
import lombok.Setter;
import web2.dev.backpsiplanner.model.Role;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class JWTObject {
    private String subject;
    private Date issuedAt;
    private Date expiration;
    private Set<Role> roles;

    public Set<String> getRoleNames(){
        return this.getRoles().stream().map(Role::getAuthority).collect(Collectors.toSet());
    }
}
