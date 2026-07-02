package excepciones;

public class PrecioInvalidoException extends RuntimeException {
	public PrecioInvalidoException(String mensaje) {
		super(mensaje);
	}
}
