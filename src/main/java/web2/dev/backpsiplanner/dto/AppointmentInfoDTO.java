package web2.dev.backpsiplanner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import web2.dev.backpsiplanner.model.Appointment;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentInfoDTO {
    private Long clientId;
    private Long professionalId;
    private LocalDateTime dateTime;
    private boolean cancelled;

    public AppointmentInfoDTO from(Appointment appointment) {
        AppointmentInfoDTO dto = new AppointmentInfoDTO();
        dto.setClientId(appointment.getClient().getId());
        dto.setProfessionalId(appointment.getProfessional().getId());
        dto.setDateTime(appointment.getDate());
        dto.setCancelled(appointment.isCancelled());

        return dto;
    }
}
