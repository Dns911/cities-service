-- Create table cities
CREATE TABLE IF NOT EXISTS cities (
    id SERIAL PRIMARY KEY,
    city_name VARCHAR(50),
    logo VARCHAR(255),
    country_id BIGINT,
    FOREIGN KEY (country_id) REFERENCES countries(id)
);

--Insert test data
INSERT INTO cities (city_name, logo, country_id)
VALUES
    ('Warsaw', 'logo_1', 1),
    ('Poznan', 'logo_2', 1),
    ('New York', 'logo_3', 2),
    ('Paris', 'logo_4', 3),
    ('Brest', 'logo_5', 3),
    ('Berlin', 'logo_6', 4),
    ('Minsk', 'logo_7', 5),
    ('Brest', 'logo_8', 5);
