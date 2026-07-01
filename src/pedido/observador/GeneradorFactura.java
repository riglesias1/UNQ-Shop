package pedido.observador;

import java.util.ArrayList;
import java.util.List;

import pedido.Factura;
import pedido.Pedido;
import pedido.estado.EstadoPedido;

public class GeneradorFactura implements ObservadorPedido {

	private final List<Factura> facturas = new ArrayList<>();

	@Override
	public void alCambiarEstado(Pedido pedido, EstadoPedido anterior, EstadoPedido nuevo) {
		if (!"ENTREGADO".equals(nuevo.nombre())) {
			return;
		}
		double total = pedido.totalProductos() + pedido.costoEnvio();
		this.facturas.add(new Factura("F-" + (this.facturas.size() + 1), total));
	}

	public List<Factura> getFacturas() {
		return this.facturas;
	}
}
