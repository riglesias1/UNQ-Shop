package pedido;

import java.util.ArrayList;
import java.util.List;

import catalogo.ItemCatalogo;

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
	}
	
	public void agregarItem(ItemCatalogo item, int cantidad){
		LineaPedido linea = new LineaPedido(item, cantidad);
        this.lineas.add(linea);
    }

	public void quitarItem(ItemCatalogo item){
		// TODO: Checkear como quitar
        // this.lineas.remove(item);
    }
	
	
	// -------------- ESTADOS --------------  \\
	public void confirmar(){
        this.estado.confirmar(this);
    }
    
    public void prepararEnvio(){
        this.estado.prepararEnvio(this);
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

    public void dessuscribir(ObservadorPedido observador){
        this.observadores.remove(observador);
    }

}
