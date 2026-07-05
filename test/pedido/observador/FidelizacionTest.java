package pedido.observador;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.Categoria;
import catalogo.Producto;
import envio.MetodoEnvio;
import pago.MetodoPago;
import pedido.Inventario;
import pedido.Pedido;

public class FidelizacionTest {
	private MailSender mailSender;
	private Pedido pedido;
	
	@BeforeEach
	void setUp() {
		mailSender = mock(MailSender.class);
        pedido = new Pedido(mock(Inventario.class), mock(MetodoEnvio.class), mock(MetodoPago.class));
        pedido.agregarItem(new Producto("P-1", "Producto", null, "Marca", Categoria.ELECTRONICA, 1000), 1);
        pedido.suscribir(new Fidelizacion(mailSender));
	}

	@Test
		void seEnviaCupoDelCincoPorCientoAlCancelar() {
		pedido.cancelar();
		verify(mailSender).enviarMail(
				pedido.getDireccionEnvio(),
				"Pedido cancelado!",
				"Se cancelo tu pedido, puedes usar un cupón de descuento del " + "5" + "% para tu próxima compra.",
				null);
		}
	
	@Test
	void noSeEnviaCupoDelCincoPorCientoSiNoSeCancela() {
		pedido.confirmar();
		verify(mailSender, never()).enviarMail(
			pedido.getDireccionEnvio(),
			"Pedido cancelado!",
			"Se cancelo tu pedido, puedes usar un cupón de descuento del " + "5" + "% para tu próxima compra.",
			null);
	}
}
