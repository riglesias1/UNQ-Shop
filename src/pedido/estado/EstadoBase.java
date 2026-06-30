package pedido.estado;

import excepciones.MovimientoEstadoInvalido;
import pedido.Pedido;

public abstract class EstadoBase implements EstadoPedido{
	
	protected String rechazar(String operacion) {
		throw new MovimientoEstadoInvalido("No se puede " + operacion + " un pedido en estado " + nombre());
	}
	
	@Override
	public void confirmar(Pedido pedido) {
		rechazar("confirmar");
	}
	
	@Override
	public void preparar(Pedido pedido) {
		rechazar("preparar");
	}
	
	@Override
	public void enviar(Pedido pedido) {
		rechazar("enviar");
	}
	
	@Override
	public void entregar(Pedido pedido) {
		rechazar("entregar");
	}
	
	@Override
	public void cancelar(Pedido pedido) {
		rechazar("cancelar");
	}
}
