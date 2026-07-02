package reporte;

import catalogo.ItemCatalogo;
import pedido.LineaPedido;


public class VentaAcumulada {
	private final ItemCatalogo item;
	private int unidades;
	private double ingresos;

	public VentaAcumulada(ItemCatalogo item) {
		this.item = item;
	}

	public void sumar(LineaPedido linea) {
		this.unidades += linea.getCantidad();
		this.ingresos += linea.subtotal();
	}

	public ItemCatalogo getItem() {
		return this.item;
	}

	public int getUnidades() {
		return this.unidades;
	}

	public double getPrecioPromedio() {
		if (this.unidades == 0) {
			return 0;
		}
		return this.ingresos / this.unidades;
	}
}
