package web2.dev.backpsiplanner.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
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
    @JoinColumn(name = "person_id")
    private Person person;

    @NotBlank
    private String location;

    @OneToMany(mappedBy = "professional", cascade = CascadeType.ALL)
    private Collection<Appointment> appointments;

    @OneToMany(mappedBy = "professional")
    private Collection<Rating> ratings;

    /**
     * Calculates the average rating of the professional.
     * @return BigDecimal value indicating the average or -1.0 if no ratings have been found.
     */
    public BigDecimal getAverageRating() {
        double average = ratings.stream().mapToInt(Rating::getValue).average().orElse(-1.0);
        return new BigDecimal(average).setScale(2, RoundingMode.HALF_UP);
    }
}
