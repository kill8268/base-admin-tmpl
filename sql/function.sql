CREATE OR REPLACE FUNCTION protect_superuser_column()
RETURNS TRIGGER AS $$
BEGIN
    IF OLD.super IS DISTINCT FROM NEW.super THEN
        RAISE EXCEPTION 'Cannot change super column!';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION update_modified_column()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated_at = now();
   RETURN NEW; 
END;
$$ language 'plpgsql';

CREATE OR REPLACE FUNCTION protect_created_columns()
RETURNS TRIGGER AS $$
BEGIN
    IF OLD.created_by IS DISTINCT FROM NEW.created_by OR OLD.created_at IS DISTINCT FROM NEW.created_at THEN
        RAISE EXCEPTION 'Cannot change created_by or created_at columns!';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;
