package catalogo;

import pedido.Inventario;

public interface ItemCatalogo {
	
	public String getNombre();
	public String getDescripcion();
	public Double getPrecioBase();
	public Double getPrecioFinal();
	public boolean esCategoria(Categoria c);
	public boolean tieneStock(Inventario inventario);
}
