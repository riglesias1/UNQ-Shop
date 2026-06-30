package pedido;

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

    public Pedido(Inventario inventario) {
        this.inventario = inventario;
        this.estado = new Borrador();
    }
	
	public void agregarItem(ItemCatalogo item, int cantidad){
		LineaPedido linea = new LineaPedido(item, cantidad);
        this.lineas.add(linea);
    }

	public void quitarItem(ItemCatalogo item){
		// TODO: Checkear como quitar
        // this.lineas.remove(item);
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
}
