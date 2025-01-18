ALTER TABLE clients
    ADD CONSTRAINT uc_clients_person UNIQUE (person_id);