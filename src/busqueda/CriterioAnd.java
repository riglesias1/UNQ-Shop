package busqueda;

import java.util.ArrayList;
import java.util.List;

import catalogo.ItemCatalogo;

public class CriterioAnd implements CriterioBusqueda {
	private List<CriterioBusqueda> criterios = new ArrayList<>();
	
	public CriterioAnd(List<CriterioBusqueda> criterios) {
		this.criterios = criterios;
	}

	@Override
	public boolean satisface(ItemCatalogo item) {
		for (CriterioBusqueda criterio: this.criterios) {
			if (! criterio.satisface(item)) {
				return false;
			}
		}
		return true;
	}
}

/*
	(
		Electrónica AND disponible
	)
OR 
 	(Nombre contiene "oferta")

CriterioOr(
		CriterioAnd(
			PorCategoria("electronica"), 
			PorDisponibilidad()
		),
		PorNombre("Oferta")
	)
*/

