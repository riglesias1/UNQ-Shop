package pedido.observador;

import java.util.Set;

import pedido.Pedido;
import pedido.estado.EstadoPedido;

public class NotificadorEmail implements ObservadorPedido {

	private static final Set<String> ESTADOS_NOTIFICABLES = Set.of("CONFIRMADO", "ENVIADO", "ENTREGADO");

	private final MailSender controlMail;

	public NotificadorEmail(MailSender controlMail) {
		this.controlMail = controlMail;
	}

	@Override
	public void alCambiarEstado(Pedido pedido, EstadoPedido anterior, EstadoPedido nuevo) {
		if (!ESTADOS_NOTIFICABLES.contains(nuevo.nombre())) {
			return;
		}
		this.controlMail.enviarMail(
				pedido.getDireccionEnvio(),
				"Estado de tu pedido: " + nuevo.nombre(),
				"Tu pedido cambió de estado a " + nuevo.nombre() + ".",
				null);
	}
}
