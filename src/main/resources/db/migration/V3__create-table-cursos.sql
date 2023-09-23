CREATE TABLE cursos (
                         id SERIAL PRIMARY KEY,
                         nombre VARCHAR(100) NOT NULL,
                         descripcion TEXT NOT NULL,
                         estado VARCHAR(100) NOT NULL
);