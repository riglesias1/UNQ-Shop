package busqueda;

import java.util.ArrayList;
import java.util.List;

import catalogo.ItemCatalogo;

public class Catalogo {
	private List<ItemCatalogo> items = new ArrayList<>();
		
	public Catalogo(List<ItemCatalogo> items) {
		this.items = items;
	}

	public List<ItemCatalogo> buscar(CriterioBusqueda criterio) {
		return this.items;
	}
}
