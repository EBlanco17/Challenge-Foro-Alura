package alura.foro.api.domain.topico;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record DatosRegistroTopico(
        @NotBlank(message = "El título del tópico no puede estar vacío")
        String titulo,
        @NotBlank(message = "El mensaje del tópico no puede estar vacío")
        String mensaje,
        @NotBlank(message = "El ID del autor no puede estar vacío")
        UUID autorId,
        @NotBlank(message = "El nombre del curso no puede estar vacío")
        Integer cursoId){
}

