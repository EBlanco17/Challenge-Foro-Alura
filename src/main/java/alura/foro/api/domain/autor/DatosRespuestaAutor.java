package alura.foro.api.domain.autor;

import java.time.LocalDateTime;
import java.util.UUID;

public record DatosRespuestaAutor(UUID id, String nombre, String alias, String email, LocalDateTime fechaRegistro) {
}
