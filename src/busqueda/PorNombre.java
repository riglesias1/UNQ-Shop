package busqueda;

import catalogo.ItemCatalogo;

public class PorNombre implements CriterioBusqueda {
	private String texto;
	
	public PorNombre(String texto) {
		this.texto = texto;
	}

	@Override
	public boolean satisface(ItemCatalogo item) {
		return item.getNombre() == this.texto;
	}
}