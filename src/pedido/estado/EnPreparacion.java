package pedido.estado;

import pedido.Pedido;

public class EnPreparacion extends EstadoBase {
	
	@Override
    public String nombre() {
        return "EN_PREPARACION";
    }

	@Override
	public void enviar(Pedido pedido) {
		pedido.setEstado(new Enviado());
	}
	
	@Override
	public void cancelar(Pedido pedido) {
		pedido.setEstado(new Cancelado());
		//TODO: reponer stock y reembolsar coste de productos y envio
	}

}
