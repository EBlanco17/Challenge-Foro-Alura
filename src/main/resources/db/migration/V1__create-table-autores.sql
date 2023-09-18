CREATE TABLE autores (
                         id UUID NOT NULL UNIQUE,
                         nombre VARCHAR(50) NOT NULL,
                         alias VARCHAR(15) NOT NULL UNIQUE,
                         email VARCHAR(100) NOT NULL UNIQUE,
                         clave VARCHAR(300) NOT NULL,
                         PRIMARY KEY (id)
);