package pedido.estado;

import pedido.Pedido;

public class Borrador extends EstadoBase{
	
	@Override
    public String nombre() {
        return "BORRADOR";
    }

	@Override
    public void confirmar(Pedido pedido) {
        pedido.setEstado(new Confirmado());
        pedido.descontarStock();
    }
	
	@Override
    public void cancelar(Pedido pedido) {
        pedido.setEstado(new Cancelado());
    }
}
