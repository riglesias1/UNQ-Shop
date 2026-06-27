package pedido.observador;

import pedido.Pedido;
import pedido.estado.EstadoPedido;

public interface ObservadorPedido {
	void alCambiarEstado(Pedido pedido, EstadoPedido anterior, EstadoPedido nuevo);
}
