package web2.dev.backpsiplanner.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web2.dev.backpsiplanner.dto.ClientRegisterDTO;
import web2.dev.backpsiplanner.service.UserService;

@RestController
@RequestMapping(name = "/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register/client")
    public ResponseEntity<String> registerClientUser (ClientRegisterDTO clientRegisterDTO) {
        userService.createClientUser(clientRegisterDTO);
        return ResponseEntity.ok("Client registered successfully!");
    }
}
