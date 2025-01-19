package web2.dev.backpsiplanner.service;

import org.springframework.stereotype.Service;
import web2.dev.backpsiplanner.dto.ProfessionalInfoDTO;
import web2.dev.backpsiplanner.repository.ProfessionalRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessionalService {
    private final ProfessionalRepository professionalRepository;

    public ProfessionalService(ProfessionalRepository professionalRepository) {
        this.professionalRepository = professionalRepository;
    }

    public List<ProfessionalInfoDTO> getAllAutonomousProfessionals() {
        return professionalRepository.findByClinicIsNull().stream()
                .map(professional -> new ProfessionalInfoDTO().from(professional))
                .collect(Collectors.toList());
    }
}
