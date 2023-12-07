--Create table
CREATE TABLE IF NOT EXISTS users (
    id BIGINT   GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    username       VARCHAR UNIQUE,
    password   VARCHAR NOT NULL
);

INSERT INTO users (username, password)
VALUES
    ('editor', '$2a$12$GNyNNYrp0YRdfoa4WTSdS.cDJ/mnyrLVeseBs.vTJkMfvGYqzOuGy'),
    ('user', '$2a$12$GNyNNYrp0YRdfoa4WTSdS.cDJ/mnyrLVeseBs.vTJkMfvGYqzOuGy'),
    ('admin', '$2a$12$GNyNNYrp0YRdfoa4WTSdS.cDJ/mnyrLVeseBs.vTJkMfvGYqzOuGy');