ALTER TABLE clinics
    ADD CONSTRAINT uc_clinics_cnpj UNIQUE (cnpj);

ALTER TABLE persons
    ADD CONSTRAINT uc_persons_cpf UNIQUE (cpf);