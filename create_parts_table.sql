CREATE TABLE IF NOT EXISTS parts (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50),
    description TEXT,
    category VARCHAR(50),
    unit_price DOUBLE PRECISION,
    quantity_on_hand INT
);