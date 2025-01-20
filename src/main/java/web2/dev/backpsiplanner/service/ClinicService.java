package web2.dev.backpsiplanner.service;

import org.springframework.stereotype.Service;
import web2.dev.backpsiplanner.dto.ClinicInfoDTO;
import web2.dev.backpsiplanner.dto.ProfessionalInfoDTO;
import web2.dev.backpsiplanner.model.Clinic;
import web2.dev.backpsiplanner.model.Professional;
import web2.dev.backpsiplanner.repository.ClinicRepository;
import web2.dev.backpsiplanner.repository.ProfessionalRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClinicService {
    private final ClinicRepository clinicRepository;
    private final ProfessionalRepository professionalRepository;

    public ClinicService(ClinicRepository clinicRepository, ProfessionalRepository professionalRepository) {
        this.clinicRepository = clinicRepository;
        this.professionalRepository = professionalRepository;
    }

    public List<ClinicInfoDTO> getAllNotEmptyClinics() {
        return clinicRepository.findByProfessionalsIsNotEmpty().stream()
                .map(clinic -> new ClinicInfoDTO().from(clinic))
                .collect(Collectors.toList());
    }

    public List<ClinicInfoDTO> getAllNotEmptyClinicsByName(String name) {
        return clinicRepository.findByProfessionalsIsNotEmptyAndNameContainingIgnoreCase(name).stream()
                .map(clinic -> new ClinicInfoDTO().from(clinic))
                .collect(Collectors.toList());
    }

    public List<ClinicInfoDTO> getAllNotEmptyClinicsByLocation(String location) {
        return clinicRepository.findByProfessionalsIsNotEmptyAndLocationContainingIgnoreCase(location).stream()
                .map(clinic -> new ClinicInfoDTO().from(clinic))
                .collect(Collectors.toList());
    }

    public void addProfessional(ProfessionalInfoDTO professionalInfoDTO, Long clinicId) {
        Professional professional = professionalRepository.findById(professionalInfoDTO.getId()).orElse(null);
        Clinic clinic = clinicRepository.findById(clinicId).orElse(null);

        if (clinic == null)
            throw new RuntimeException("Clinic not found");
        if (professional == null)
            throw new RuntimeException("Professional not found");
        if (professional.getClinic() != null)
            throw new RuntimeException("Professional already registered in another clinic: " + professional.getClinic().getName());

        professional.setClinic(clinic);
        professionalRepository.save(professional);
    }
}
