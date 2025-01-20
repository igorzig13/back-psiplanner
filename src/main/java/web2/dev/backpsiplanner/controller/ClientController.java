package web2.dev.backpsiplanner.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import web2.dev.backpsiplanner.dto.ClinicOrProfessionalDTO;
import web2.dev.backpsiplanner.dto.ProfessionalInfoDTO;
import web2.dev.backpsiplanner.service.ClientService;
import web2.dev.backpsiplanner.service.ProfessionalService;

import java.util.List;

@RestController
@RequestMapping("/api/client")
@SecurityRequirement(name = "Bearer Authentication")
public class ClientController {
    private final ClientService clientService;
    private final ProfessionalService professionalService;

    public ClientController(ClientService clientService, ProfessionalService professionalService) {
        this.clientService = clientService;
        this.professionalService = professionalService;
    }

    @GetMapping("/clinics-and-professionals")
    public List<ClinicOrProfessionalDTO> findAllClinicsAndProfessionals(){
        return clientService.getAllClinicsAndProfessionals();
    }

    @GetMapping("/clinics-and-professionals/by-name")
    public List<ClinicOrProfessionalDTO> findClinicsAndProfessionalsByName(@RequestParam("name") String name){
        return clientService.getAllClinicsAndProfessionalsFilteredByName(name);
    }

    @GetMapping("/clinics-and-professionals/by-location")
    public List<ClinicOrProfessionalDTO> findClinicsAndProfessionalsByLocation(@RequestParam("location") String location){
        return clientService.getAllClinicsAndProfessionalsFilteredByLocation(location);
    }

    @GetMapping("clinic/professionals")
    public List<ProfessionalInfoDTO> findProfessionalsByClinicId(@RequestParam("clinicId") Long clinicId){
        return professionalService.getAllByClinicId(clinicId);
    }
}
