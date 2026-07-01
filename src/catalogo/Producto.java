package catalogo;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import pedido.Inventario;

public class Producto implements ItemCatalogo{
	private String sku;
	private String nombre;
	private String descripcion;
	private String marca;
	private Categoria categoria;
	private double precio;
	private double descuentoPromocional;

	private Map<String, Atributo> atributos = new HashMap<>();
	
	public Producto(String sku, String nombre,String descripcion, String marca, Categoria categoria, double precio) {
		this(sku, nombre, descripcion, marca, categoria, precio, 0.0d);
	}
	
	public Producto(String sku, String nombre,String descripcion, String marca, Categoria categoria, double precio, double descuentoPromocional) {
		// TODO: validarPrecio() y descuento?
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
	public Double getPeso() {
		if (!this.tieneAtributo("peso")) {
			return 0d;
		}
		return (Double) this.getAtributo("peso").getValor();
	}

	@Override
	public boolean tieneStock(Inventario inventario) {
		return inventario.estaDisponible(this);
	}

    public boolean tieneAtributo(String nombre) {
        return this.atributos.containsKey(nombre);
    }

	public void definirAtributo(Atributo atributo) {
        this.atributos.put(atributo.getNombre(), atributo);
    }

    public Atributo getAtributo(String nombre) {
        return this.atributos.get(nombre);
    }

    public Collection<Atributo> getAtributos() {
        return this.atributos.values();
    }
    
    @Override
    public boolean esCategoria(Categoria categoria) {
    	return this.categoria == categoria;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Producto otro = (Producto) obj;
        return this.sku.equals(otro.sku);
    }

    @Override
    public int hashCode() {
        return this.sku.hashCode();
    }
}
