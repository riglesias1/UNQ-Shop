package pago;

public interface ApiTransferencia {
	boolean validarCbuAlias(String cbu, String alias);
	void ejecutar(Double monto, boolean programada);
}
