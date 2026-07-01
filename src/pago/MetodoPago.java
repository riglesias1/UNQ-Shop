package pago;

import pedido.Pedido;

public abstract class MetodoPago {
	
	protected abstract void validarDatos(Pedido pedido);
	protected abstract void reservarFondos(Pedido pedido);
	protected abstract void ejecutarTransaccion(Pedido pedido);
	
	public final ResultadoPago procesar(Pedido pedido) {
		this.validarDatos(pedido);
		this.reservarFondos(pedido);
		this.ejecutarTransaccion(pedido);
		ResultadoPago resultado = new ResultadoPago();
		this.notificarResultado(resultado);
		return resultado;
	}

	protected void notificarResultado(ResultadoPago resultado) {
		resultado.setCodigoTransaccion("Transaccion -" + System.identityHashCode(resultado));
	}
}
