package alura.foro.api.domain.respuesta;

import alura.foro.api.domain.autor.Autor;
import alura.foro.api.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contenidoRespuesta;
    private LocalDateTime fechaCreacion;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Autor autor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;


    public Respuesta(DatosRegistroRespuesta datosRegistroRespuesta, Autor autor, Topico topico) {
        this.contenidoRespuesta = datosRegistroRespuesta.contenidoRespuesta();
        this.fechaCreacion = LocalDateTime.now();
        this.autor = autor;
        this.topico = topico;
    }

    public void actualizar(DatosActualizarRespuesta datosActualizarRespuesta) {
        this.contenidoRespuesta = datosActualizarRespuesta.contenidoRespuesta();
    }
}
