ALTER TABLE certificates
    ADD COLUMN inspection_id UUID;

ALTER TABLE certificates DROP CONSTRAINT fk_certificate_farm;

ALTER TABLE certificates DROP COLUMN farm_id;

ALTER TABLE certificates
    ADD CONSTRAINT fk_certificate_inspection
        FOREIGN KEY (inspection_id)
            REFERENCES inspections (id)
            ON DELETE CASCADE;

ALTER TABLE certificates RENAME COLUMN certificate_no TO certificate_number;

ALTER TABLE certificates ADD COLUMN compliance_score DOUBLE PRECISION;
