package web2.dev.backpsiplanner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class AppointmentCreateDTO {
    private Long clientId;
    private Long professionalId;
    private LocalDateTime dateTime;
}
