package web2.dev.backpsiplanner.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract @Data class LegalOrNaturalPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 8)
    private String phoneNumber;

    private String imageUrl;

    public abstract String getCpfOrCnpj();
}
