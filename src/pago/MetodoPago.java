package pago;

import excepciones.MovimientoEstadoInvalido;
import pedido.Pedido;

public abstract class MetodoPago {
	
	protected abstract void validarDatos(Pedido pedido);
	protected abstract void reservarFondos(Pedido pedido);
	protected abstract void ejecutarTransaccion(Pedido pedido);
	protected abstract void notificarResultado(ResultadoPago resultado);
	
	public final ResultadoPago procesar(Pedido pedido) {
		this.validarDatos(pedido);
		this.reservarFondos(pedido);
		this.ejecutarTransaccion(pedido);
		ResultadoPago resultado = new ResultadoPago();
		this.notificarResultado(resultado);
	}
}
