package pedido.observador;

import pedido.Pedido;
import pedido.estado.EstadoPedido;

public class Fidelizacion implements ObservadorPedido {
	private final MailSender controlMail;

	public Fidelizacion(MailSender controlMail) {
		this.controlMail = controlMail;
	}

	@Override
	public void alCambiarEstado(Pedido pedido, EstadoPedido anterior, EstadoPedido nuevo) {
		if (!"CANCELADO".equals(nuevo.nombre())) {
			return;
		}
		int porcentaje = 5;
		this.controlMail.enviarMail(
				pedido.getDireccionEnvio(),
				"Pedido cancelado!",
				"Se cancelo tu pedido, puedes usar un cupón de descuento del " + porcentaje + "% para tu próxima compra.",
				null);
	}
}
