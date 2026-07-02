package pedido;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import catalogo.ItemCatalogo;
import envio.MetodoEnvio;
import pedido.estado.Borrador;
import pedido.estado.EstadoBase;
import pedido.estado.EstadoPedido;
import pedido.observador.ObservadorPedido;

public class Pedido {
    private List<LineaPedido> lineas = new ArrayList<>();
    private List<ObservadorPedido> observadores = new ArrayList<>();
    private List<NotaCredito> notasCredito = new ArrayList<>();
    private Inventario inventario;
    
    private EstadoPedido estado;
    private MetodoEnvio metodoEnvio;
    private String direccionEnvio;
    private LocalDate fecha;

    public Pedido(Inventario inventario) {
        this.inventario = inventario;
        this.estado = new Borrador();
        this.fecha = LocalDate.now();
    }
	
	public void agregarItem(ItemCatalogo item, int cantidad){
		this.estado.agregarItem(this, item, cantidad);
    }

	public void quitarItem(ItemCatalogo item){
		this.estado.quitarItem(this, item);
    }

	public void agregarLinea(ItemCatalogo item, int cantidad){
		// if (cantidad <= 0) {
			// TODO: Agregar error
		// }
		for (LineaPedido linea : this.lineas) {
			if (linea.getItem().equals(item)) {
				linea.agregar(cantidad);
				return;
			}
		}
		this.lineas.add(new LineaPedido(item, cantidad));
	}

	public void sacarLinea(ItemCatalogo item){
		this.lineas.removeIf(linea -> linea.getItem().equals(item));
	}

	public boolean estaVacio(){
		return this.lineas.isEmpty();
	}

	public List<LineaPedido> getLineas(){
		return this.lineas;
	}

	public double totalProductos(){
		double total = 0d;
		for (LineaPedido linea : this.lineas) {
			total += linea.subtotal();
		}
		return total;
	}

	public double costoEnvio(){
		return this.metodoEnvio == null ? 0d : this.metodoEnvio.calcularCosto(this);
	}

	// -------------- NOTA DE CREDITO -------------- \\
	public void registrarNotaCredito(double monto){
		this.notasCredito.add(new NotaCredito("Cancelacion de pedido", monto));
	}

	public List<NotaCredito> getNotasCredito(){
		return this.notasCredito;
	}

	public void setMetodoEnvio(MetodoEnvio metodoEnvio){
		this.metodoEnvio = metodoEnvio;
	}

	public void descontarStock(){
		for (LineaPedido linea : this.lineas) {
			this.inventario.decrementar(linea.getItem(), linea.getCantidad());
		}
	}

	public void reponerStock(){
		for (LineaPedido linea : this.lineas) {
			this.inventario.incrementar(linea.getItem(), linea.getCantidad());
		}
	}
	
	public void setEstado(EstadoBase estado) {
		EstadoPedido anterior = this.estado;
        this.estado = estado;
        notificar(anterior, estado);
	}
	
	private void notificar(EstadoPedido anterior, EstadoPedido nuevo) {
        for (ObservadorPedido observador : observadores) {
            observador.alCambiarEstado(this, anterior, nuevo);
        }
    }

	// -------------- ESTADOS --------------  \\
    public String nombreEstado() {
        return estado.nombre();
    }

	public void confirmar(){
        this.estado.confirmar(this);
    }

    public void prepararEnvio(){
        this.estado.preparar(this);
    }
    
    public void enviar(){
        this.estado.enviar(this);
    }
    
    public void entregar(){
        this.estado.entregar(this);
    }
    
    public void cancelar(){
        this.estado.cancelar(this);
    }

    // -------------- OBSERVER --------------  \\
    public void suscribir(ObservadorPedido observador){
        this.observadores.add(observador);
    }

    public void desuscribir(ObservadorPedido observador){
        this.observadores.remove(observador);
    }

	public String getDireccionEnvio() {
		return this.direccionEnvio;
	}

	public LocalDate getFecha() {
		return this.fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
}
