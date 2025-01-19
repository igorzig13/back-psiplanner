package web2.dev.backpsiplanner.dto;

import lombok.Data;
import web2.dev.backpsiplanner.model.Clinic;

@Data
public class ClinicInfoDTO {

    Long id;
    String name;
    String cnpj;
    String location;
    String description;
    String phoneNumber;
    String imageUrl;

    public ClinicInfoDTO from(Clinic clinic){
        ClinicInfoDTO dto = new ClinicInfoDTO();

        dto.id = clinic.getId();
        dto.name = clinic.getName();
        dto.cnpj = clinic.getCnpj();
        dto.location = clinic.getLocation();
        dto.description = clinic.getDescription();
        dto.phoneNumber = clinic.getPhoneNumber();
        dto.imageUrl = clinic.getImageUrl();

        return dto;
    }
}
