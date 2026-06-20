package busqueda;

import java.util.ArrayList;
import java.util.List;

import catalogo.ItemCatalogo;

public class Catalogo {
	
	public Catalogo(List<ItemCatalogo> items) {
		this.items = items;
	}

	private List<ItemCatalogo> items = new ArrayList<>();
	
	public List<ItemCatalogo> buscar(CriterioBusqueda criterio) {
		return this.items;
	}
}
