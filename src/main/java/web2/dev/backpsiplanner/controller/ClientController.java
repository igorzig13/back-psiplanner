package web2.dev.backpsiplanner.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web2.dev.backpsiplanner.dto.ClinicOrProfessionalDTO;
import web2.dev.backpsiplanner.service.ClientService;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/clinics-and-professionals")
    public List<ClinicOrProfessionalDTO> findAllClinicsAndProfessionals(){
        return clientService.getAllClinicsAndProfessionals();
    }
}
