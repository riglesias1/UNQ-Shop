package pedido.estado;

import pedido.Pedido;

public class Confirmado extends EstadoBase {
	
	@Override
    public String nombre() {
        return "CONFIRMADO";
    }
	
	@Override
    public void preparar(Pedido pedido) {
        pedido.setEstado(new EnPreparacion());
    }	
	
	@Override
    public void cancelar(Pedido pedido) {
        pedido.reponerStock();
        pedido.registrarNotaCredito(pedido.totalProductos() + pedido.costoEnvio());
        pedido.setEstado(new Cancelado());
    }

    @Override
    public boolean esNotificable(){
        return true;
    }
    
    @Override
	public boolean representaVenta() {
		return true;
	}
}
