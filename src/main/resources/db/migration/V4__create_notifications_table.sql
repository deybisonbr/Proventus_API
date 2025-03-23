CREATE TABLE "notifications" (
    "id" UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    "title" VARCHAR(255) NOT NULL,
    "description" TEXT,
    "read" BOOLEAN DEFAULT false,
    "type" VARCHAR(50),
    "url" VARCHAR(255),
    "created_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP,
    "user_id" UUID,
    CONSTRAINT fk_user FOREIGN KEY ("user_id") REFERENCES "users" ("id") ON DELETE CASCADE
);

CREATE INDEX idx_notifications_user_id ON "notifications" ("user_id");
