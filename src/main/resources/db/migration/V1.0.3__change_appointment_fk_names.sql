ALTER TABLE appointments
DROP
CONSTRAINT fk_appointments_on_client_client;

ALTER TABLE appointments
DROP
CONSTRAINT fk_appointments_on_professional_professional;

ALTER TABLE appointments
    ADD client_id BIGINT;

ALTER TABLE appointments
    ADD professional_id BIGINT;

ALTER TABLE appointments
    ADD CONSTRAINT FK_APPOINTMENTS_ON_CLIENT FOREIGN KEY (client_id) REFERENCES clients (client_id);

ALTER TABLE appointments
    ADD CONSTRAINT FK_APPOINTMENTS_ON_PROFESSIONAL FOREIGN KEY (professional_id) REFERENCES professionals (professional_id);

ALTER TABLE appointments
DROP
COLUMN client_client_id;

ALTER TABLE appointments
DROP
COLUMN professional_professional_id;