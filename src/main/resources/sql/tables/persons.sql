CREATE TABLE IF NOT EXISTS persons (
    id SERIAL PRIMARY KEY,
    name VARCHAR(32) NOT NULL,
    lastname VARCHAR(32) NOT NULL,
    dni INTEGER NOT NULL UNIQUE,
    age INTEGER NOT NULL,
    date TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW()
);