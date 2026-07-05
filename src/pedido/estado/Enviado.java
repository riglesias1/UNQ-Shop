package pedido.estado;

import pedido.Pedido;

public class Enviado extends EstadoBase{
	
	@Override
    public String nombre() {
        return "ENVIADO";
    }

	@Override
	public void entregar(Pedido pedido) {
		pedido.setEstado(new Entregado());
	}
	
	@Override
	public void cancelar(Pedido pedido) {
		pedido.registrarNotaCredito(pedido.totalProductos());
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
