package reporte;

import java.util.ArrayList;
import java.util.List;

import catalogo.ItemCatalogo;
import pedido.LineaPedido;
import pedido.Pedido;


public class ReporteProductosMasVendidos implements Reporte {

	private final List<Pedido> pedidos;

	public ReporteProductosMasVendidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public ElementoReporte construir(Periodo periodo) {
		List<VentaAcumulada> acumuladas = new ArrayList<>();
		
		for (Pedido pedido : this.pedidos) {
			if (!this.esVenta(pedido) || !periodo.contiene(pedido.getFecha())) {
				continue;
			}
			
			for (LineaPedido linea : pedido.getLineas()) {
				VentaAcumulada acumulada = this.buscarOCrear(acumuladas, linea.getItem());
				acumulada.sumar(linea);
			}
		}
		
		acumuladas.sort((a, b) -> b.getUnidades() - a.getUnidades());
		
		return this.generarReporte(acumuladas);
	}

	private boolean esVenta(Pedido pedido) {
		String estado = pedido.nombreEstado();
		return !(estado == "BORRADOR") && !(estado == "CANCELADO");
	}

	private VentaAcumulada buscarOCrear(List<VentaAcumulada> acumuladas, ItemCatalogo item) {
		for (VentaAcumulada acumulada : acumuladas) {
			if (acumulada.getItem() == item) {
				return acumulada;
			}
		}
		
		VentaAcumulada nueva = new VentaAcumulada(item);
		return nueva;
	}

	private ElementoReporte generarReporte(List<VentaAcumulada> acumuladas) {
		return new Tabla(
				"titu",
				"columnas",
				"filas"
			);
	}	
}
