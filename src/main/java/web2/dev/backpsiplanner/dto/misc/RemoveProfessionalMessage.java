package web2.dev.backpsiplanner.dto.misc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RemoveProfessionalMessage {
    private String message;
    private Integer remainingCount;
}
