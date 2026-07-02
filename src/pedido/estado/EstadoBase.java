package pedido.estado;

import catalogo.ItemCatalogo;
import excepciones.MovimientoEstadoInvalido;
import pedido.Pedido;

public abstract class EstadoBase implements EstadoPedido{
	
	protected MovimientoEstadoInvalido rechazar(String operacion) {
		return new MovimientoEstadoInvalido("No se puede " + operacion + " un pedido en estado " + nombre());
	}

	@Override
	public void agregarItem(Pedido pedido, ItemCatalogo item, int cantidad) {
		throw rechazar("agregar items a");
	}

	@Override
	public void quitarItem(Pedido pedido, ItemCatalogo item) {
		throw rechazar("quitar items de");
	}

	@Override
	public void confirmar(Pedido pedido) {
		throw rechazar("confirmar");
	}

	@Override
	public void preparar(Pedido pedido) {
		throw rechazar("preparar");
	}

	@Override
	public void enviar(Pedido pedido) {
		throw rechazar("enviar");
	}

	@Override
	public void entregar(Pedido pedido) {
		throw rechazar("entregar");
	}

	@Override
	public void cancelar(Pedido pedido) {
		throw rechazar("cancelar");
	}
}
