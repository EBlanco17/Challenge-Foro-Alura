package alura.foro.api.domain.autor;

import java.time.LocalDateTime;
import java.util.UUID;

public record DatosListadoAutor(UUID id, String nombre, String alias, String email, LocalDateTime fechaRegistro) {
    public DatosListadoAutor(Autor autor) {
        this(autor.getId(), autor.getNombre(), autor.getAlias(), autor.getEmail(), autor.getFechaRegistro());
    }
}
