package alura.foro.api.domain.respuesta;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record DatosRegistroRespuesta(
        @NotBlank(message = "El contenido de la respuesta no puede estar vacío")
        String contenidoRespuesta,
        @NotBlank(message = "El ID del autor no puede estar vacío")
        UUID autorId,
        @NotBlank(message = "El ID del tópico no puede estar vacío")
        Long topicoId) {
}
