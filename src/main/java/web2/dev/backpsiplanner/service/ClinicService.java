package web2.dev.backpsiplanner.service;

import org.springframework.stereotype.Service;
import web2.dev.backpsiplanner.dto.ClinicInfoDTO;
import web2.dev.backpsiplanner.repository.ClinicRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClinicService {
    private final ClinicRepository clinicRepository;

    public ClinicService(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
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
}
