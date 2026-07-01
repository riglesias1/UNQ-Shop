package pedido.estado;

import catalogo.ItemCatalogo;
import pedido.Pedido;

public class Borrador extends EstadoBase{
	
	@Override
    public String nombre() {
        return "BORRADOR";
    }

	@Override
	public void agregarItem(Pedido pedido, ItemCatalogo item, int cantidad) {
		pedido.agregarLinea(item, cantidad);
	}

	@Override
	public void quitarItem(Pedido pedido, ItemCatalogo item) {
		pedido.sacarLinea(item);
	}

	@Override
    public void confirmar(Pedido pedido) {
        if (pedido.estaVacio()) {
            // TODO: error de "No se puede confirmar un pedido vacio"
        }
        pedido.setEstado(new Confirmado());
        pedido.descontarStock();
    }
	
	@Override
    public void cancelar(Pedido pedido) {
        pedido.setEstado(new Cancelado());
    }
}
