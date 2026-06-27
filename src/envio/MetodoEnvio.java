package envio;

import pedido.Pedido;

public interface MetodoEnvio {
	double calcularCosto(Pedido pedido);
}
