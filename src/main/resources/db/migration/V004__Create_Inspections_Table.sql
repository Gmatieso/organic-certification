-- Enable UUID support (required once per DB)
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE  inspections (
 id UUID PRIMARY KEY  DEFAULT gen_random_uuid(),
 farm_id UUID NOT NULL ,
 date DATE NOT NULL,
 inspector_name VARCHAR(255) NOT NULL,
 status VARCHAR(20) NOT NULL CHECK ( status IN  ('DRAFT', 'SUBMITTED' ,'APPROVED', 'REJECTED')),
 compliance_score NUMERIC(5,2),
 CONSTRAINT fk_inspection_farm FOREIGN KEY (farm_id)
     REFERENCES farms(id) ON DELETE CASCADE
)