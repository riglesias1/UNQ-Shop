package pedido.estado;

import pedido.Pedido;

public interface EstadoPedido {
	void confirmar(Pedido pedido);
	void prepararEnvio(Pedido pedido);
	void enviar(Pedido pedido);
	void entregar(Pedido pedido);
	void cancelar(Pedido pedido);
}	
