ALTER TABLE appointments
    ADD cancelled BOOLEAN DEFAULT FALSE;

ALTER TABLE appointments
    ALTER COLUMN cancelled SET NOT NULL;