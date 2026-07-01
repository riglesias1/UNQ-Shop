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
		List<ItemCatalogo> resultado = new ArrayList<>();
		for (ItemCatalogo item : this.items) {
			if (criterio.satisface(item)) {
				resultado.add(item);
			}
		}
		return resultado;
	}
}
