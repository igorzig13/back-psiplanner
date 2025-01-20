package web2.dev.backpsiplanner.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web2.dev.backpsiplanner.config.security.JWTCreator;
import web2.dev.backpsiplanner.config.security.JWTObject;
import web2.dev.backpsiplanner.config.security.SecurityProperties;
import web2.dev.backpsiplanner.dto.auth.Login;
import web2.dev.backpsiplanner.dto.auth.Session;
import web2.dev.backpsiplanner.model.Role;
import web2.dev.backpsiplanner.model.User;
import web2.dev.backpsiplanner.repository.UserRepository;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final SecurityProperties securityProperties;

    public AuthService(PasswordEncoder passwordEncoder, UserRepository userRepository, SecurityProperties securityProperties) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.securityProperties = securityProperties;
    }

    @SuppressWarnings("unchecked")
    public Session login (Login login) {
        User user = userRepository.findByUsername(login.getUsernameOrEmail())
                .orElseGet(() -> userRepository.findByEmail(login.getUsernameOrEmail())
                        .orElseThrow( () -> new BadCredentialsException(login.getUsernameOrEmail() + " not found")
                ));

        if (!passwordEncoder.matches(login.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Wrong password. Please try again");
        }

        Session session = new Session();
        session.setLogin(user.getUsername());
        session.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toSet()));
        session.setUserId(user.getId());
        session.setEntityId(user.getLegalOrNaturalPerson().getId());

        JWTObject jwtObject = new JWTObject();
        jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
        jwtObject.setExpiration(new Date(System.currentTimeMillis() + securityProperties.getExpiration()));
        jwtObject.setSubject(user.getUsername());
        jwtObject.setRoles((Set<Role>) user.getAuthorities());
        session.setToken(JWTCreator.create(securityProperties.getPrefix(), securityProperties.getKey(), jwtObject));
        return session;
    }
}
