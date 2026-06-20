package catalogo;

public class Producto implements ItemCatalogo{
	private String sku;
	private String nombre;
	private String descripcion;
	private String marca;
	private Categoria categoria;
	private double precio;
	private double descuentoPromocional;
	//map de atributos
	
	public Producto(String sku, String nombre,String descripcion, String marca, Categoria categoria, double precio) {
		this(sku, nombre, descripcion, marca, categoria, precio, 0.0d);
	}
	
	public Producto(String sku, String nombre,String descripcion, String marca, Categoria categoria, double precio, double descuentoPromocional) {
		// TODO: validarPrecio() y descuento?
		//validamos que el sku sea único?
		//agregar getters y setters de atributos
		this.sku = sku;
		this.nombre =nombre;
		this.descripcion =descripcion;
		this.marca =marca;
		this.categoria =categoria;
		this.precio =precio;
		this.descuentoPromocional =descuentoPromocional;
	}
	
	
	@Override
	public String getNombre() {
		return this.nombre;
	}
	@Override
	public String getDescripcion() {
		return this.descripcion;
	}
	public Categoria getCategoria() {
		return this.categoria;
	}
	@Override
	public Double getPrecioBase() {
		return this.precio;
	}
	@Override
	public Double getPrecioFinal() {
		return this.precio - (this.precio * this.descuentoPromocional);
	}
	public String getSku() {
		return this.sku;
	}
	public String getMarca() {
		return this.marca;
	}

	@Override
	public boolean tieneStock(Inventario inventario) {
		return inventario.estaDisponible(this);
	}
	
	
}
