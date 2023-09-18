package alura.foro.api.domain.autor;

import java.util.UUID;

public record DatosRespuestaAutor(UUID id, String nombre, String alias, String email) {
}
