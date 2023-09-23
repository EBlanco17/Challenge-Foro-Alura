package alura.foro.api.domain.curso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String descripcion;
    private String estado;
    public Curso(DatosRegistroCurso datosRegistroCurso) {
        this.nombre = datosRegistroCurso.nombre().toUpperCase();
        this.descripcion = datosRegistroCurso.descripcion();
        this.estado = "ACTIVO";
    }

    public void actualizarDatos(DatosActualizarCurso datosActualizarCurso) {
        if (datosActualizarCurso.nombre() != null)
            this.nombre = datosActualizarCurso.nombre().toUpperCase();
        if (datosActualizarCurso.descripcion() != null)
            this.descripcion = datosActualizarCurso.descripcion();
    }

    public void desactivarCurso() {
        this.estado = "INACTIVO";
    }
}
