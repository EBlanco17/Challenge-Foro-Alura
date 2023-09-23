package alura.foro.api.domain.topico;

import java.time.LocalDateTime;
import java.util.UUID;

public record DatosDetalleTopico(Long id, String titulo, String mensaje, UUID autor, LocalDateTime fechaCreacion, Integer curso, String estado){
    public DatosDetalleTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getAutor().getId(), topico.getFechaCreacion(), topico.getCurso().getId(), topico.getEstado().toString());
    }
}
