package pedido.observador;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.Categoria;
import catalogo.Producto;
import envio.MetodoEnvio;
import pago.MetodoPago;
import pedido.Inventario;
import pedido.Pedido;

public class NotificadorEmailTest {
	private MailSender mailSender;
	private Pedido pedido;
	
	@BeforeEach
	void setUp() {
		mailSender = mock(MailSender.class);
        pedido = new Pedido(mock(Inventario.class), mock(MetodoEnvio.class), mock(MetodoPago.class));
        pedido.agregarItem(new Producto("P-1", "Producto", null, "Marca", Categoria.ELECTRONICA, 1000), 1);
        pedido.suscribir(new NotificadorEmail(mailSender));
	}

	@Test
		void seEnviaEmailAlConfirmar() {
		pedido.confirmar();
		verify(mailSender).enviarMail(pedido.getDireccionEnvio(),
				"Estado de tu pedido: " + "CONFIRMADO",
				"Tu pedido cambió de estado a " + "CONFIRMADO" + ".",
				null);
		}
	
	@Test
	void noSeEnviaEmailAlPrepararEnvio() {
		pedido.confirmar();
		pedido.prepararEnvio();
		verify(mailSender, times(1)).enviarMail(pedido.getDireccionEnvio(),
				"Estado de tu pedido: " + "CONFIRMADO",
				"Tu pedido cambió de estado a " + "CONFIRMADO" + ".",
				null);
	}
}
