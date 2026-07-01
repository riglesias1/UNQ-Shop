package pago;

import excepciones.PagoInvalidoException;
import pedido.Pedido;

public class PagoBilleteraVirtual extends MetodoPago {
	private ApiBilleteraVirtual apiBilletera;

	public PagoBilleteraVirtual(ApiBilleteraVirtual apiBilletera) {
		this.apiBilletera = apiBilletera;
	}

	@Override
	protected void validarDatos(Pedido pedido) {
		boolean haySaldo = apiBilletera.saldoSuficiente(pedido.totalProductos());
		if (!haySaldo) {
			throw new PagoInvalidoException("Saldo insuficiente en la billetera virtual");
		}
	}

	@Override
	protected void reservarFondos(Pedido pedido) {
		apiBilletera.bloquearSaldo(pedido.totalProductos());
	}

	@Override
	protected void ejecutarTransaccion(Pedido pedido) {
		apiBilletera.acreditar(pedido.totalProductos());
	}

	@Override
	protected void notificarResultado(ResultadoPago resultado) {
		super.notificarResultado(resultado);
		apiBilletera.notificarPush("Pago acreditado - operación " + resultado.getCodigoTransaccion());
	}
}
