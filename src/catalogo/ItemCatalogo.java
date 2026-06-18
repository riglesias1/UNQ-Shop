package catalogo;

public interface ItemCatalogo {
	
	public String getNombre();
	public String getDescripcion();
	public Categoria getCategoria();
	public Double getPrecioBase();
	public Double getPrecioFinal();
	// public Boolean tieneStock(Inventario);
}
