package pedido.estado;

import pedido.Pedido;

public class Confirmado extends EstadoBase {

	@Override
    public void prepararEnvio(Pedido pedido) {
        pedido.cambiarEstado(new EnPreparacion());
    }	
	
	@Override
    public void cancelar(Pedido pedido) {
        pedido.reponerStock();
        //TODO pedido.reembolsarPlata ?¿(pedido.totalProductos() );
        pedido.cambiarEstado(new Cancelado());
    }
	}

}
