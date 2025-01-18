package web2.dev.backpsiplanner.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "clinics")
@PrimaryKeyJoinColumn(name = "clinic_id")
public @Data class Clinic extends LegalOrNaturalPerson {

    @NotBlank
    @Column(nullable = false)
    @Size(min = 2, max = 50)
    private String name;

    @NotNull
    @CNPJ
    @Column(unique = true)
    private String cnpj;

    @NotBlank
    private String location;

    @Size(min = 1, max = 500)
    @NotBlank
    private String description;

    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL)
    private Collection<Professional> professionals;

    @Override
    public String getCpfOrCnpj() {
        return cnpj;
    }
}
