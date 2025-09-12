-- Enable UUID support (required once per DB)
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE  farms (
    id UUID PRIMARY KEY  DEFAULT  gen_random_uuid(),
    farmer_id UUID NOT NULL,
    farm_name VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    area_ha NUMERIC(10,2),
    CONSTRAINT fk_farm_farmer FOREIGN KEY  (farmer_id)
                    REFERENCES farmers (id) ON DELETE  CASCADE
)
