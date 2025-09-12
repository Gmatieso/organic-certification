-- Enable UUID support (required once per DB)
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE  certificates (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    farm_id UUID NOT NULL UNIQUE,
    certificate_no VARCHAR(100) NOT NULL UNIQUE ,
    issue_date DATE NOT NULL ,
    expiry_date DATE NOT NULL ,
    pdf_url VARCHAR(50),
    CONSTRAINT fk_certificate_farm FOREIGN KEY (farm_id)
        REFERENCES farms (id) ON DELETE CASCADE
)