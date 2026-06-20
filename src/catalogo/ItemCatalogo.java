package catalogo;

public interface ItemCatalogo {
	
	public String getNombre();
	public String getDescripcion();
	public Double getPrecioBase();
	public Double getPrecioFinal();
	public boolean tieneStock(Inventario inventario);
}
