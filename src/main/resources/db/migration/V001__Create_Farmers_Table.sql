CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE  farmers (
    id UUID PRIMARY KEY  DEFAULT  gen_random_uuid(),
    name VARCHAR(255) NOT NULL ,
    phone VARCHAR(50),
    email VARCHAR(255) UNIQUE ,
    COUNTY VARCHAR(100)
)