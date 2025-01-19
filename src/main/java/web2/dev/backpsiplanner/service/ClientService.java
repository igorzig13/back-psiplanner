package web2.dev.backpsiplanner.service;

import org.springframework.stereotype.Service;
import web2.dev.backpsiplanner.dto.ClinicInfoDTO;
import web2.dev.backpsiplanner.dto.ClinicOrProfessionalDTO;
import web2.dev.backpsiplanner.dto.ProfessionalInfoDTO;
import web2.dev.backpsiplanner.repository.ClientRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClinicService clinicService;
    private final ProfessionalService professionalService;

    public ClientService(ClientRepository clientRepository, ClinicService clinicService, ProfessionalService professionalService) {
        this.clientRepository = clientRepository;
        this.clinicService = clinicService;
        this.professionalService = professionalService;
    }

    public List<ClinicOrProfessionalDTO> getAllClinicsAndProfessionals(){
        List<ClinicInfoDTO> clinics = clinicService.getAllNotEmptyClinics();
        List<ProfessionalInfoDTO> professionals = professionalService.getAllAutonomousProfessionals();

        List<ClinicOrProfessionalDTO> result = new ArrayList<>();

        for (ClinicInfoDTO clinicInfoDTO : clinics) {
            ClinicOrProfessionalDTO clinicOrProfessional = new ClinicOrProfessionalDTO();
            clinicOrProfessional.setType("Clinic");
            clinicOrProfessional.setInfo(clinicInfoDTO);
            result.add(clinicOrProfessional);
        }
        for (ProfessionalInfoDTO professionalInfoDTO : professionals) {
            ClinicOrProfessionalDTO clinicOrProfessional = new ClinicOrProfessionalDTO();
            clinicOrProfessional.setType("Professional");
            clinicOrProfessional.setInfo(professionalInfoDTO);
            result.add(clinicOrProfessional);
        }

        return result;
    }

}
