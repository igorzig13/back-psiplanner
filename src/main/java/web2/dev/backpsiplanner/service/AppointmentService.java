package web2.dev.backpsiplanner.service;

import org.springframework.stereotype.Service;
import web2.dev.backpsiplanner.dto.AppointmentInfoDTO;
import web2.dev.backpsiplanner.model.Appointment;
import web2.dev.backpsiplanner.model.Client;
import web2.dev.backpsiplanner.model.Professional;
import web2.dev.backpsiplanner.repository.AppointmentRepository;
import web2.dev.backpsiplanner.repository.ClientRepository;
import web2.dev.backpsiplanner.repository.ProfessionalRepository;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final ClientRepository clientRepository;
    private final ProfessionalRepository professionalRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, ClientRepository clientRepository, ProfessionalRepository professionalRepository) {
        this.appointmentRepository = appointmentRepository;
        this.clientRepository = clientRepository;
        this.professionalRepository = professionalRepository;
    }

    public void createAppointment(AppointmentInfoDTO appointmentInfoDTO) {
        Appointment appointment = new Appointment();
        Client client = clientRepository.findById(appointmentInfoDTO.getClientId())
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));
        Professional professional = professionalRepository.findById(appointmentInfoDTO.getProfessionalId())
                .orElseThrow( () -> new IllegalArgumentException("Professional not found"));

        if (appointmentInfoDTO.getDateTime() == null) {
            throw new IllegalArgumentException("DateTime cannot be null");
        }

        appointment.setClient(client);
        appointment.setProfessional(professional);
        appointment.setDate(appointmentInfoDTO.getDateTime());
        appointment.setCancelled(appointmentInfoDTO.isCancelled());

        appointmentRepository.save(appointment);

    }
}
