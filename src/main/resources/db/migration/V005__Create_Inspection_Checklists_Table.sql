-- Enable UUID support (required once per DB)
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE inspection_checklists(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    inspection_id UUID NOT NULL,
    question TEXT NOT NULL ,
    answer BOOLEAN NOT NULL ,
    CONSTRAINT  fk_checklist_inspection FOREIGN KEY (inspection_id)
        REFERENCES inspections (id) ON DELETE CASCADE
)