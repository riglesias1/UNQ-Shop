package excepciones;

public class PagoInvalidoException extends RuntimeException {
	public PagoInvalidoException(String mensaje) {
		super(mensaje);
	}
}
