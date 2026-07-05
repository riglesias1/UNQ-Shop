package pedido.observador;

import java.util.Set;

import pedido.Pedido;
import pedido.estado.EstadoPedido;

public class NotificadorEmail implements ObservadorPedido {

	private final MailSender controlMail;

	public NotificadorEmail(MailSender controlMail) {
		this.controlMail = controlMail;
	}

	@Override
	public void alCambiarEstado(Pedido pedido, EstadoPedido anterior, EstadoPedido nuevo) {
		if (!nuevo.esNotificable()) {
			return;
		}
		this.controlMail.enviarMail(
				pedido.getDireccionEnvio(),
				"Estado de tu pedido: " + nuevo.nombre(),
				"Tu pedido cambió de estar " + anterior.nombre() + " a estar " + nuevo.nombre() + ".",
				null);
	}
}
