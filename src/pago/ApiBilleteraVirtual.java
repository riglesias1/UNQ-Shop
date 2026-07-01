package pago;

public interface ApiBilleteraVirtual {
	boolean saldoSuficiente(Double monto);
	void bloquearSaldo(Double monto);
	void acreditar(Double monto);
	void notificarPush(String mensaje);
}
