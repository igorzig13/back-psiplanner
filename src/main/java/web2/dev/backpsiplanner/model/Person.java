package web2.dev.backpsiplanner.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.br.CPF;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "persons")
@PrimaryKeyJoinColumn(name = "person_id")
public @Data class Person extends LegalOrNaturalPerson {

    @NotBlank
    @Column(nullable = false)
    @Size(min = 2, max = 30)
    private String firstName;

    @NotBlank
    @Column(nullable = false)
    @Size(min = 2, max = 50)
    private String lastName;

    @NotNull
    @CPF
    @Column(unique = true)
    private String cpf;

    private String gender;

    @Override
    public String getCpfOrCnpj() {
        return cpf;
    }
}
