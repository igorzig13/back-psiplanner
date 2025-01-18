ALTER TABLE professionals
    ADD CONSTRAINT uc_professionals_person UNIQUE (person_id);