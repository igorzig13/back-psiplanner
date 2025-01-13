package web2.dev.backpsiplanner.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web2.dev.backpsiplanner.dto.auth.Login;
import web2.dev.backpsiplanner.dto.auth.Session;
import web2.dev.backpsiplanner.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public Session login(@RequestBody Login login) {
        return authService.login(login);
    }
}
