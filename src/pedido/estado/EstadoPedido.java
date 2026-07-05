package pedido.estado;

import catalogo.ItemCatalogo;
import pedido.Pedido;

public interface EstadoPedido {
	String nombre();
	void agregarItem(Pedido pedido, ItemCatalogo item, int cantidad);
	void quitarItem(Pedido pedido, ItemCatalogo item);
	void confirmar(Pedido pedido);
	void preparar(Pedido pedido);
	void enviar(Pedido pedido);
	void entregar(Pedido pedido);
	void cancelar(Pedido pedido);
	boolean esNotificable();
	boolean estaEntregado();
	boolean estaCancelado();
	boolean representaVenta();
}	
