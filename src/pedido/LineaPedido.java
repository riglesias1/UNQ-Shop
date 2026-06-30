package pedido;

import catalogo.ItemCatalogo;

public class LineaPedido {
	private ItemCatalogo item;
	private int cantidad;

	public LineaPedido(ItemCatalogo item, int cantidad) {
		this.item = item;
		this.cantidad = cantidad;
	}

	public ItemCatalogo getItem() {
		return item;
	}

	public int getCantidad() {
		return cantidad;
	}

	public double subtotal() {
		return this.item.getPrecioFinal() * this.cantidad;
	}
}
