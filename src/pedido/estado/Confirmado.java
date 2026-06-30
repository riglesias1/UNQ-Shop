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
        //pedido.reponerStock();
        //TODO pedido.reembolsarPlata ?¿(pedido.totalProductos() );
        pedido.setEstado(new Cancelado());
    }
}
