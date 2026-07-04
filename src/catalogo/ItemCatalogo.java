package catalogo;

import pedido.Inventario;

public interface ItemCatalogo {
	
	public String getNombre();
	public String getDescripcion();
	public Double getPrecioBase();
	public Double getPrecioFinal();
	public Double getPeso();
	public boolean esCategoria(Categoria c);
	public boolean tieneStock(Inventario inventario, int cantidad);
}
