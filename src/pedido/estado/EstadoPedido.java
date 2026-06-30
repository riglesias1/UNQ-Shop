package pedido.estado;

import pedido.Pedido;

public interface EstadoPedido {
	String nombre();
	void confirmar(Pedido pedido);
	void preparar(Pedido pedido);
	void enviar(Pedido pedido);
	void entregar(Pedido pedido);
	void cancelar(Pedido pedido);
}	
