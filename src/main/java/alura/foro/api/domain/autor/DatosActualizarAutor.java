package alura.foro.api.domain.autor;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record DatosActualizarAutor(
        @NotBlank(message = "El id no puede estar vacío")
        UUID id, String nombre, String email, String clave) {
}
