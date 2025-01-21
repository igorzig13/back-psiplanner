package web2.dev.backpsiplanner.service;

import org.springframework.stereotype.Service;
import web2.dev.backpsiplanner.dto.AppointmentInfoDTO;
import web2.dev.backpsiplanner.dto.ProfessionalDashboardDTO;
import web2.dev.backpsiplanner.dto.ProfessionalInfoDTO;
import web2.dev.backpsiplanner.model.Professional;
import web2.dev.backpsiplanner.repository.AppointmentRepository;
import web2.dev.backpsiplanner.repository.ProfessionalRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessionalService {
    private final ProfessionalRepository professionalRepository;
    private final AppointmentRepository appointmentRepository;

    public ProfessionalService(ProfessionalRepository professionalRepository, AppointmentRepository appointmentRepository) {
        this.professionalRepository = professionalRepository;
        this.appointmentRepository = appointmentRepository;
    }

    public List<ProfessionalInfoDTO> getAllAutonomousProfessionals() {
        return professionalRepository.findByClinicIsNull().stream()
                .map(professional -> new ProfessionalInfoDTO().from(professional))
                .collect(Collectors.toList());
    }

    public List<ProfessionalInfoDTO> getAllAutonomousProfessionalsByName(String name) {
        return professionalRepository.findByClinicIsNullFilteredByName(name).stream()
                .map(professional -> new ProfessionalInfoDTO().from(professional))
                .collect(Collectors.toList());
    }

    public List<ProfessionalInfoDTO> getAllAutonomousProfessionalsByLocation(String location) {
        return professionalRepository.findByClinicIsNullAndLocationContainingIgnoreCase(location).stream()
                .map(professional -> new ProfessionalInfoDTO().from(professional))
                .collect(Collectors.toList());
    }

    public List<ProfessionalInfoDTO> getAllByClinicId(Long clinicId) {
        return professionalRepository.findProfessionalsByClinicId(clinicId).stream()
                .map(professional -> new ProfessionalInfoDTO().from(professional))
                .collect(Collectors.toList());
    }

    public ProfessionalDashboardDTO getDashboard(Long professionalId) {
        Professional professional = professionalRepository.findById(professionalId)
                .orElseThrow( () -> new RuntimeException("Professional not found"));

        return new ProfessionalDashboardDTO().from(professional);
    }

    public List<AppointmentInfoDTO> getAppointments(Long professionalId) {
        return appointmentRepository.findAppointmentByProfessionalId(professionalId).stream()
                .map(appointment ->  new AppointmentInfoDTO().from(appointment))
                .collect(Collectors.toList());
    }
}
