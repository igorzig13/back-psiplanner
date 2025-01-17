package web2.dev.backpsiplanner.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Collection;

@Entity
@Table(name = "professionals")
public @Data class Professional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "professional_id")
    private Long id;

    @NotBlank
    private String crp;

    @NotBlank
    private String approach;

    @ManyToOne
    @JoinColumn(name = "clinic_id")
    private Clinic clinic;

    @Size(min = 1, max = 500)
    @NotBlank
    private String description;

    @OneToOne(fetch = FetchType.EAGER)
    @NotNull
    private Person person;

    @OneToMany(mappedBy = "professional", cascade = CascadeType.ALL)
    private Collection<Appointment> appointments;

    @OneToMany(mappedBy = "professional")
    private Collection<Rating> ratings;
}
