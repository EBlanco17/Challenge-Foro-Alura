CREATE TABLE respuestas (
                         id SERIAL PRIMARY KEY,
                         contenido_respuesta TEXT NOT NULL,
                         fecha_creacion TIMESTAMP NOT NULL,
                         autor_id UUID NOT NULL,
                         topico_id INT NOT NULL,
                         CONSTRAINT fk_respuestas_autor_id FOREIGN KEY (autor_id) REFERENCES autores(id),
                         CONSTRAINT fk_respuestas_topico_id FOREIGN KEY (topico_id) REFERENCES topicos(id)
);