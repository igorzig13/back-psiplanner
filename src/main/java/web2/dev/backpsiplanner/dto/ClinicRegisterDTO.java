package web2.dev.backpsiplanner.dto;

import lombok.Data;

@Data
public class ClinicRegisterDTO {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private String clinicName;
    private String cnpj;
    private String location;
    private String description;
}
