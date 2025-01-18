package web2.dev.backpsiplanner.dto;

import lombok.Data;

@Data
public class PersonRegisterDTO {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String cpf;
    private String phoneNumber;
    private String gender;
}
