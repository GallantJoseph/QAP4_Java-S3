CREATE TABLE IF NOT EXISTS parts (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    description TEXT,
    category VARCHAR(50),
    unit_price DOUBLE PRECISION NOT NULL,
    quantity_on_hand INT NOT NULL
);