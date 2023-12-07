-- Create table countries
CREATE TABLE IF NOT EXISTS countries (
    id BIGINT PRIMARY KEY,
    country_name VARCHAR(50)
    );

--Insert test data
INSERT INTO countries
VALUES
    (1, 'Poland'),
    (2, 'USA'),
    (3, 'France'),
    (4, 'Germany'),
    (5, 'Belarus');