package web2.dev.backpsiplanner.dto;

import lombok.Data;
import web2.dev.backpsiplanner.model.Professional;

@Data
public class ProfessionalInfoDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String crp;
    private String approach;
    private String phoneNumber;
    private String description;
    private String location;

    public ProfessionalInfoDTO from(Professional professional) {
        ProfessionalInfoDTO dto = new ProfessionalInfoDTO();

        dto.setId(professional.getId());
        dto.setFirstName(professional.getPerson().getFirstName());
        dto.setLastName(professional.getPerson().getLastName());
        dto.setCrp(professional.getCrp());
        dto.setApproach(professional.getApproach());
        dto.setPhoneNumber(professional.getPerson().getPhoneNumber());
        dto.setDescription(professional.getDescription());
        dto.setLocation(professional.getLocation());

        return dto;
    }
}
