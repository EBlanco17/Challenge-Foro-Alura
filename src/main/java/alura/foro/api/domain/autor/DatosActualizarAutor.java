package alura.foro.api.domain.autor;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record DatosActualizarAutor(
        @NotNull(message = "El id no puede estar vacío")
        UUID id,
        String nombre,
        String email,
        String clave) {
}
