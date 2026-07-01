package envio;

import catalogo.ItemCatalogo;
import pedido.Inventario;

public class Sucursal {
	private Inventario inventario;

	public Sucursal(Inventario inventario) {
		this.inventario = inventario;
	}
	
	public boolean hayStockEnLocal(ItemCatalogo item) {
		return inventario.estaDisponible(item);
	}	
}
