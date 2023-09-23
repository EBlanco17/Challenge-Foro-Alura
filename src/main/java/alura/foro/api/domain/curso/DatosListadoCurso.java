package alura.foro.api.domain.curso;

public record DatosListadoCurso(Integer id, String nombre, String descripcion) {
    public DatosListadoCurso(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getDescripcion());
    }
}
