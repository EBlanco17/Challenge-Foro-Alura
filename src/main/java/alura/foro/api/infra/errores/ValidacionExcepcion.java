package alura.foro.api.infra.errores;

public class ValidacionExcepcion extends RuntimeException {
    public ValidacionExcepcion(String msg) {
        super(msg);
    }
}
