package busqueda;

import catalogo.Categoria;
import catalogo.ItemCatalogo;

public class PorCategoria implements CriterioBusqueda {
	private Categoria categoria;
	
	public PorCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public boolean satisface(ItemCatalogo item) {
		// TODO: Ver categoria opcional?
		return item.getCategoria() == this.categoria;
	}
}