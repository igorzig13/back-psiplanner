package web2.dev.backpsiplanner.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web2.dev.backpsiplanner.dto.ProfessionalInfoDTO;
import web2.dev.backpsiplanner.dto.misc.RemoveProfessionalMessage;
import web2.dev.backpsiplanner.service.ClinicService;
import web2.dev.backpsiplanner.service.ProfessionalService;

import java.util.List;

@RestController
@RequestMapping("/api/clinic")
@SecurityRequirement(name = "Bearer Authentication")
public class ClinicController {
    private final ClinicService clinicService;
    private final ProfessionalService professionalService;

    public ClinicController(ClinicService clinicService, ProfessionalService professionalService) {
        this.clinicService = clinicService;
        this.professionalService = professionalService;
    }

    @GetMapping("/professionals/available/")
    public List<ProfessionalInfoDTO> findAvailableProfessionals() {
        return professionalService.getAllAutonomousProfessionals();
    }

    @PostMapping("/professionals/add")
    public ResponseEntity<String> addProfessional(@RequestParam Long professionalId, @RequestParam Long clinicId) {
        clinicService.addProfessional(professionalId, clinicId);
        return ResponseEntity.ok("Successfully added the professional to the clinic");
    }

    @GetMapping("/professionals/list")
    public List<ProfessionalInfoDTO> listProfessionals(@RequestParam Long clinicId) {
        return professionalService.getAllByClinicId(clinicId);
    }

    @DeleteMapping("/professionals/remove")
    public ResponseEntity<RemoveProfessionalMessage> removeProfessional(@RequestParam Long professionalId, @RequestParam Long clinicId) {
        return ResponseEntity.ok(clinicService.removeProfessional(professionalId, clinicId));
    }
}
