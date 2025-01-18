package web2.dev.backpsiplanner.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProfessionalRegisterDTO extends PersonRegisterDTO {
    private String crp;
    private String approach;
    private String description;
    private String location;
}
