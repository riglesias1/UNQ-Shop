package busqueda;

import java.util.ArrayList;
import java.util.List;

import catalogo.ItemCatalogo;

public class CriterioOr implements CriterioBusqueda {
	private List<CriterioBusqueda> criterios = new ArrayList<>();
	
	public CriterioOr(List<CriterioBusqueda> criterios) {
		this.criterios = criterios;
	}

	@Override
	public boolean satisface(ItemCatalogo item) {
		for (CriterioBusqueda criterio: this.criterios) {
			if (criterio.satisface(item)) {
				return true;
			}
		}
		return false;
	}
}
