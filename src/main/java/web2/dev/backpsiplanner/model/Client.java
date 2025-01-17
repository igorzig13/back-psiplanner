package web2.dev.backpsiplanner.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Collection;

@Entity
@Table(name = "clients")
public @Data class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @NotNull
    private Person person;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Collection<Appointment> appointments;
}
