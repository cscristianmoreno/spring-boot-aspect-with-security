CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(32) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(72) NOT NULL UNIQUE,
    date TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW(),
    persons_id INTEGER NOT NULL UNIQUE,
    roles_id INTEGER NOT NULL UNIQUE,
    CONSTRAINT fk_persons_id FOREIGN KEY(persons_id) REFERENCES persons(id),
    CONSTRAINT fk_roles_id FOREIGN KEY(roles_id) REFERENCES roles(id)
);