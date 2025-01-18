package web2.dev.backpsiplanner.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web2.dev.backpsiplanner.dto.PersonRegisterDTO;
import web2.dev.backpsiplanner.dto.ProfessionalRegisterDTO;
import web2.dev.backpsiplanner.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register/client")
    public ResponseEntity<String> registerClientUser (PersonRegisterDTO personRegisterDTO) {
        userService.createClientUser(personRegisterDTO);
        return ResponseEntity.ok("Client registered successfully!");
    }

    @PostMapping("/register/professional")
    public ResponseEntity<String> registerProfessionalUser (ProfessionalRegisterDTO professionalRegisterDTO) {
        userService.createProfessionalUser(professionalRegisterDTO);
        return ResponseEntity.ok("Professional registered successfully!");
    }
}
