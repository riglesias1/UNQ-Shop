package busqueda;

import catalogo.ItemCatalogo;

public class PorPrecioMaximo implements CriterioBusqueda {
	private double precioMaximo;
	
	public PorPrecioMaximo(double precioMaximo) {
		this.precioMaximo = precioMaximo;
	}

	@Override
	public boolean satisface(ItemCatalogo item) {
		return item.getPrecioFinal() <= this.precioMaximo;
	}
}
