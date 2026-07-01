package pago;

public interface ApiTarjetaCredito {
	boolean validarTarjeta(String numero, String cvv, String vencimiento);
	void preAutorizar(Double monto);
	void ejecutar(Double monto);
}
