package catalogo;

import java.util.ArrayList;
import java.util.List;

public class Paquete implements ItemCatalogo{
	private String nombre;
	private String descripcion;
	private double descuento;
	private List<ItemCatalogo> items = new ArrayList<>();
	
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
	
	/*
	@Override
	public Categoria getCategoria() {
		return this.categoria;
	}
	-------ARREGLAR---------
	*/

	@Override
	public Double getPrecioBase() {
		double precioBase = 0d;
		for (ItemCatalogo item: items) {
			precioBase += item.getPrecioBase();
		}
		return precioBase;
	}

	@Override
	public Double getPrecioFinal() {
		double precioFinal = 0d;
		for (ItemCatalogo item: items) {
			precioFinal += item.getPrecioFinal();
		}
		return precioFinal;
	}
	
}
