-- Enable UUID support (required once per DB)
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE fields (
    id UUID PRIMARY KEY  DEFAULT  gen_random_uuid(),
    farm_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    crop VARCHAR(100),
    area_ha NUMERIC(10,2),
    CONSTRAINT fk_field_farm FOREIGN KEY (farm_id)
                    REFERENCES farms (id) ON DELETE  CASCADE
)