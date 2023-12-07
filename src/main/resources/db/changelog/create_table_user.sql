
-- Create the table cities
CREATE TABLE IF NOT EXISTS cities (
    id SERIAL PRIMARY KEY,
    city_name VARCHAR(50),
    logo VARCHAR(255),
    country_id BIGINT,
    FOREIGN KEY (country_id) REFERENCES countries(id)
);
