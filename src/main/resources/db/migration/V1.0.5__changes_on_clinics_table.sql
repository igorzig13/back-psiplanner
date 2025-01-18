ALTER TABLE clinics
    ADD description VARCHAR(500);

ALTER TABLE clinics
    DROP COLUMN type_of_service;