package alura.foro.api.domain.curso;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroCurso(
        @NotBlank(message = "El nombre del curso no puede estar vacío")
        String nombre,
        @NotBlank(message = "La descripción del curso no puede estar vacía")
        String descripcion) {
}
