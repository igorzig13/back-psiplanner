package web2.dev.backpsiplanner.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import web2.dev.backpsiplanner.dto.ProfessionalDashboardDTO;
import web2.dev.backpsiplanner.service.ProfessionalService;

@RestController
@RequestMapping("/api/professional")
public class ProfessionalController {
    private final ProfessionalService professionalService;

    public ProfessionalController(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }

    @GetMapping("/dashboard")
    public ProfessionalDashboardDTO professionalDashboard(@RequestParam Long professionalId) {
        return professionalService.getDashboard(professionalId);
    }
}
