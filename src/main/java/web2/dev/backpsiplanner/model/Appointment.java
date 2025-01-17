package web2.dev.backpsiplanner.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public @Data class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Professional professional;

    @ManyToOne(fetch = FetchType.EAGER)
    private Client client;

    private LocalDateTime date;
}
