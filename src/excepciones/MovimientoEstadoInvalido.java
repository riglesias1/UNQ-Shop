package excepciones;


public class MovimientoEstadoInvalido extends RuntimeException {
    public MovimientoEstadoInvalido(String mensaje) {
        super(mensaje);
    }
}
