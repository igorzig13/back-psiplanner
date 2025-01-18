ALTER TABLE clinics
    ADD location VARCHAR(255);

ALTER TABLE professionals
    ADD location VARCHAR(255);

ALTER TABLE clinics
    DROP COLUMN address;