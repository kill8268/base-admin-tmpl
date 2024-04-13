CREATE TABLE new_table (
    id         serial  constraint table_pk  primary key,
    uuid       uuid     unique                             not null,
    created_by uuid                                      not null,
    created_at timestamp   default CURRENT_TIMESTAMP     not null,
    updated_by uuid                                      not null,
    update_at  timestamp                                 not null
);

CREATE TRIGGER update_table_time
BEFORE UPDATE ON table
FOR EACH ROW
EXECUTE PROCEDURE update_modified_column();


CREATE TRIGGER protect_table_columns
BEFORE UPDATE ON table
FOR EACH ROW
EXECUTE PROCEDURE protect_created_columns();
