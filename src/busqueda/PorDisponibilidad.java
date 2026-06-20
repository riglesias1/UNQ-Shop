package busqueda;

import catalogo.Categoria;
import catalogo.Inventario;
import catalogo.ItemCatalogo;

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