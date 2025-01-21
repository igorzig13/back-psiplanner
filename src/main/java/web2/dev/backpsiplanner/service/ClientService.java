package web2.dev.backpsiplanner.service;

import org.springframework.stereotype.Service;
import web2.dev.backpsiplanner.dto.AppointmentInfoDTO;
import web2.dev.backpsiplanner.dto.ClinicInfoDTO;
import web2.dev.backpsiplanner.dto.ClinicOrProfessionalDTO;
import web2.dev.backpsiplanner.dto.ProfessionalInfoDTO;
import web2.dev.backpsiplanner.repository.AppointmentRepository;
import web2.dev.backpsiplanner.repository.ClientRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClinicService clinicService;
    private final ProfessionalService professionalService;
    private final AppointmentRepository appointmentRepository;

    public ClientService(ClientRepository clientRepository, ClinicService clinicService, ProfessionalService professionalService, AppointmentRepository appointmentRepository) {
        this.clientRepository = clientRepository;
        this.clinicService = clinicService;
        this.professionalService = professionalService;
        this.appointmentRepository = appointmentRepository;
    }

    public List<ClinicOrProfessionalDTO> getAllClinicsAndProfessionals(){
        List<ProfessionalInfoDTO> professionals = professionalService.getAllAutonomousProfessionals();
        List<ClinicInfoDTO> clinics = clinicService.getAllNotEmptyClinics();

        return mergeLists(professionals, clinics);
    }

    public List<ClinicOrProfessionalDTO> getAllClinicsAndProfessionalsFilteredByName(String name) {
        List<ClinicInfoDTO> clinics = clinicService.getAllNotEmptyClinicsByName(name);
        List<ProfessionalInfoDTO> professionals = professionalService.getAllAutonomousProfessionalsByName(name);

        return mergeLists(professionals, clinics);
    }

    public List<ClinicOrProfessionalDTO> getAllClinicsAndProfessionalsFilteredByLocation(String location) {
        List<ClinicInfoDTO> clinics = clinicService.getAllNotEmptyClinicsByLocation(location);
        List<ProfessionalInfoDTO> professionals = professionalService.getAllAutonomousProfessionalsByLocation(location);

        return mergeLists(professionals, clinics);
    }

    private List<ClinicOrProfessionalDTO> mergeLists(List<ProfessionalInfoDTO> professionals, List<ClinicInfoDTO> clinics) {
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

    public List<AppointmentInfoDTO> getAppointments(Long clientId) {
        return appointmentRepository.findAppointmentsByClientId(clientId).stream()
                .map(appointment -> new AppointmentInfoDTO().from(appointment))
                .collect(Collectors.toList());
    }
}
