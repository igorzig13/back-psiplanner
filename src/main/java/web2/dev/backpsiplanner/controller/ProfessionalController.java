package web2.dev.backpsiplanner.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import web2.dev.backpsiplanner.dto.AppointmentInfoDTO;
import web2.dev.backpsiplanner.dto.ProfessionalDashboardDTO;
import web2.dev.backpsiplanner.service.ProfessionalService;

import java.util.List;

@RestController
@RequestMapping("/api/professional")
@SecurityRequirement(name = "Bearer Authentication")
public class ProfessionalController {
    private final ProfessionalService professionalService;

    public ProfessionalController(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }

    @GetMapping("/dashboard")
    public ProfessionalDashboardDTO professionalDashboard(@RequestParam Long professionalId) {
        return professionalService.getDashboard(professionalId);
    }

    @GetMapping("/appointments")
    public List<AppointmentInfoDTO> getAppointments(@RequestParam Long professionalId) {
        return professionalService.getAppointments(professionalId);
    }
}
