package alura.foro.api.domain.curso;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarCurso(
        @NotNull(message = "El id del curso no puede ser nulo")
        Integer id, String nombre, String descripcion) {
}
