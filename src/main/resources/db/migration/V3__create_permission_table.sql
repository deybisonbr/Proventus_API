CREATE TABLE "permissions" (
    "id" UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    "name" VARCHAR(255) NOT NULL UNIQUE,
    "description" TEXT,
    "created_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP
);

CREATE TABLE "role_permissions" (
    "id" UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    "role_id" UUID NOT NULL,
    "permission_id" UUID NOT NULL,
    CONSTRAINT fk_role FOREIGN KEY ("role_id") REFERENCES "roles" ("id") ON DELETE CASCADE,
    CONSTRAINT fk_permission FOREIGN KEY ("permission_id") REFERENCES "permissions" ("id") ON DELETE CASCADE
);

CREATE INDEX idx_role_permissions_role_id ON "role_permissions" ("role_id");
CREATE INDEX idx_role_permissions_permission_id ON "role_permissions" ("permission_id");