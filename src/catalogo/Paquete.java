package catalogo;

import java.util.ArrayList;
import java.util.List;

import pedido.Inventario;

public class Paquete implements ItemCatalogo{
	private String nombre;
	private String descripcion;
	private double descuento;
	private List<ItemCatalogo> items = new ArrayList<>();

	public Paquete(String nombre, String descripcion) {
		this(nombre, descripcion, 0.0d);
	}
	

	public Paquete(String nombre, String descripcion, double descuento) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.descuento = descuento;
	}
	
	@Override
	public String getNombre() {
		return this.nombre;
	}

	@Override
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public double getDescuento() {
		return this.descuento;
	}
	
	public void agregar(ItemCatalogo item) {
		this.items.add(item);
	}

	public void quitar(ItemCatalogo item) {
		this.items.remove(item);
	}
	
	@Override
    public boolean esCategoria(Categoria categoria) {
		for (ItemCatalogo item: this.items){
			if (item.esCategoria(categoria)) {
				return true;
			}
		}
		return false;
	}
    

	@Override
	public Double getPrecioBase() {
		double precioBase = 0d;
		for (ItemCatalogo item: this.items) {
			precioBase += item.getPrecioBase();
		}
		return precioBase;
	}

	@Override
	public Double getPrecioFinal() {
		double precioFinal = 0d;
		for (ItemCatalogo item: this.items) {
			precioFinal += item.getPrecioFinal();
		}
		return precioFinal - (precioFinal * this.descuento);
	}

	@Override
	public boolean tieneStock(Inventario inventario) {
		for (ItemCatalogo item: this.items) {
			if (! item.tieneStock(inventario)) {
				return false;
			}
		}
		return true;
	}
}
