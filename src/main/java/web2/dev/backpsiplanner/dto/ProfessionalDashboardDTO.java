package web2.dev.backpsiplanner.dto;

import lombok.Data;
import web2.dev.backpsiplanner.model.Appointment;
import web2.dev.backpsiplanner.model.Professional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProfessionalDashboardDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String crp;
    private BigDecimal averageRating;
    private Integer totalAppointments;
    private Integer appointmentsInTheMonth;
    private Integer canceledAppointments;

    public ProfessionalDashboardDTO from(Professional professional) {
        ProfessionalDashboardDTO dto = new ProfessionalDashboardDTO();

        dto.setId(professional.getId());
        dto.setFirstName(professional.getPerson().getFirstName());
        dto.setLastName(professional.getPerson().getLastName());
        dto.setCrp(professional.getCrp());
        dto.setAverageRating(professional.getAverageRating());
        dto.setTotalAppointments(professional.getAppointments().size());
        dto.setAppointmentsInTheMonth(professional.getAppointments().stream()
                .filter( a -> a.getDate().getMonth() == LocalDateTime.now().getMonth())
                .toList().size());
        dto.setCanceledAppointments(professional.getAppointments().stream()
                .filter(Appointment::isCancelled).toList().size());

        return dto;
    }
}
