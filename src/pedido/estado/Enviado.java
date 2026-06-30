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
		pedido.setEstado(new Cancelado());
		//TODO: reembolsar envio (pero no coste de envio)
		// reponer stock?
	}

}
