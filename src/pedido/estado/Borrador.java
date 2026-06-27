package pedido.estado;

import pedido.Pedido;

public class Borrador extends EstadoBase{
	
	@Override
    public void confirmar(Pedido pedido) {
        pedido.setEstado(new Confirmado());
        // pedido.descontarStock();
        //TODO: descontar stock y verificar si tiene stock
    }
	
	@Override
    public void cancelar(Pedido pedido) {
        pedido.setEstado(new Cancelado());
    }
}
