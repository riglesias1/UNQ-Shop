package supermercado;

public class Producto {
	private String nombre;
	private Double precio;
	private boolean esPrecioCuidado;

	public Producto(String nombre, double precio, boolean esPrecioCuidado) {
		this.nombre = nombre;
		this.precio = precio;
		this.esPrecioCuidado = esPrecioCuidado;
	}

	public Producto(String nombre, double precio) {
		this.nombre = nombre;
		this.precio = precio;
		this.esPrecioCuidado = false;
	}

	public String getNombre() {
		return this.nombre;
	}

	public Double getPrecio() {
		return this.precio;
	}

	public boolean esPrecioCuidado() {
		return this.esPrecioCuidado;
	}

	public void aumentarPrecio(double aumento) {
		this.precio += aumento;
	}

}
