CREATE TABLE topicos (
                         id SERIAL PRIMARY KEY,
                         titulo VARCHAR(100) NOT NULL,
                         mensaje TEXT NOT NULL,
                         fecha_creacion TIMESTAMP NOT NULL,
                         estado VARCHAR(100) NOT NULL,
                         autor_id UUID NOT NULL,
                         curso_id INT NOT NULL,
                         CONSTRAINT fk_topicos_autor_id FOREIGN KEY (autor_id) REFERENCES autores(id),
                         CONSTRAINT fk_topicos_curso_id FOREIGN KEY (curso_id) REFERENCES cursos(id)
);