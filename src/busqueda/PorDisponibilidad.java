package busqueda;

import catalogo.ItemCatalogo;
import pedido.Inventario;

public class PorDisponibilidad implements CriterioBusqueda {
	private Inventario inventario;

	public PorDisponibilidad(Inventario inventario) {
		this.inventario = inventario;
	}

	@Override
	public boolean satisface(ItemCatalogo item) {
		return item.tieneStock(this.inventario);
	}
}