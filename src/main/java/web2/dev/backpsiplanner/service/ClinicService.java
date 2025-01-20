package web2.dev.backpsiplanner.service;

import org.springframework.stereotype.Service;
import web2.dev.backpsiplanner.dto.ClinicInfoDTO;
import web2.dev.backpsiplanner.dto.misc.RemoveProfessionalMessage;
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

    public void addProfessional(Long professionalId, Long clinicId) {
        Professional professional = professionalRepository.findById(professionalId)
                .orElseThrow(() -> new RuntimeException("Professional not found"));

        Clinic clinic = clinicRepository.findById(clinicId)
                .orElseThrow(() -> new RuntimeException("Clinic not found"));

        if (professional.getClinic() != null)
            throw new RuntimeException("Professional already registered in another clinic: " + professional.getClinic().getName());

        professional.setClinic(clinic);
        professionalRepository.save(professional);
    }

    public RemoveProfessionalMessage removeProfessional(Long professionalId, Long clinicId) {
        Professional professional = professionalRepository.findById(professionalId)
                .orElseThrow(() -> new RuntimeException("Professional not found"));

        Clinic clinic = clinicRepository.findById(clinicId)
                .orElseThrow(() -> new RuntimeException("Clinic not found"));

        if (professional.getClinic() != null && !professional.getClinic().getId().equals(clinic.getId()))
            throw new RuntimeException("This professional is registered in another clinic. Unable to remove!");

        professional.setClinic(null);
        professionalRepository.save(professional);

        String message = "Successfully removed professional with CRP " + professional.getCrp() + "from the clinic";
        Integer remaining = professionalRepository.countProfessionalsByClinicId(clinic.getId());

        return new RemoveProfessionalMessage(message, remaining);
    }
}
