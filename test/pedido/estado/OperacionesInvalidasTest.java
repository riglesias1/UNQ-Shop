package pedido.estado;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.Categoria;
import catalogo.Producto;
import envio.MetodoEnvio;
import excepciones.MovimientoEstadoInvalido;
import pago.MetodoPago;
import pedido.Inventario;
import pedido.Pedido;

public class OperacionesInvalidasTest {
	private Pedido pedido;
	private Producto producto;

	@BeforeEach
	void setUp() {
		pedido = new Pedido(mock(Inventario.class), mock(MetodoEnvio.class), mock(MetodoPago.class));
		producto = new Producto("P-1", "Producto", null, "Marca", Categoria.ELECTRONICA, 1000);
		pedido.agregarItem(producto, 2);
	}

	@Test
	void enBorradorSePuedeQuitarItem() {
		pedido.quitarItem(producto);
		assertTrue(pedido.estaVacio());
	}

	@Test
	void enBorradorSonInvalidasEntregarYEnviarYPreparar() {
		assertThrows(MovimientoEstadoInvalido.class, () -> pedido.entregar());
		assertThrows(MovimientoEstadoInvalido.class, () -> pedido.enviar());
		assertThrows(MovimientoEstadoInvalido.class, () -> pedido.prepararEnvio());
	}

	@Test
	void enConfirmadoSonInvalidasAgregarQuitarConfirmarEnviarEntregar() {
		pedido.confirmar();
		assertThrows(MovimientoEstadoInvalido.class, () -> pedido.agregarItem(producto, 1));
		assertThrows(MovimientoEstadoInvalido.class, () -> pedido.quitarItem(producto));
		assertThrows(MovimientoEstadoInvalido.class, () -> pedido.confirmar());
		assertThrows(MovimientoEstadoInvalido.class, () -> pedido.enviar());
		assertThrows(MovimientoEstadoInvalido.class, () -> pedido.entregar());
	}

	@Test
	void enPreparacionSonInvalidasAgregarQuitarConfirmarPrepararEntregar() {
		pedido.confirmar();
		pedido.prepararEnvio();
		assertThrows(MovimientoEstadoInvalido.class, () -> pedido.agregarItem(producto, 1));
		assertThrows(MovimientoEstadoInvalido.class, () -> pedido.quitarItem(producto));
		assertThrows(MovimientoEstadoInvalido.class, () -> pedido.confirmar());
		assertThrows(MovimientoEstadoInvalido.class, () -> pedido.prepararEnvio());
		assertThrows(MovimientoEstadoInvalido.class, () -> pedido.entregar());
	}

	@Test
	void enEnviadoSonInvalidasAgregarQuitarConfirmarPrepararEnviar() {
		pedido.confirmar();
		pedido.prepararEnvio();
		pedido.enviar();
		assertThrows(MovimientoEstadoInvalido.class, () -> pedido.agregarItem(producto, 1));
		assertThrows(MovimientoEstadoInvalido.class, () -> pedido.quitarItem(producto));
		assertThrows(MovimientoEstadoInvalido.class, () -> pedido.confirmar());
		assertThrows(MovimientoEstadoInvalido.class, () -> pedido.prepararEnvio());
		assertThrows(MovimientoEstadoInvalido.class, () -> pedido.enviar());
	}

	@Test
	void estadoTerminalEntregadoRechazaTodaOperacion() {
		pedido.confirmar();
		pedido.prepararEnvio();
		pedido.enviar();
		pedido.entregar();
		assertEquals("ENTREGADO", pedido.nombreEstado());
		assertThrows(MovimientoEstadoInvalido.class, () -> pedido.agregarItem(producto, 1));
		assertThrows(MovimientoEstadoInvalido.class, () -> pedido.quitarItem(producto));
		assertThrows(MovimientoEstadoInvalido.class, () -> pedido.confirmar());
		assertThrows(MovimientoEstadoInvalido.class, () -> pedido.prepararEnvio());
	}

	@Test
	void estadoTerminalCanceladoRechazaTodaOperacion() {
		pedido.cancelar();
		assertEquals("CANCELADO", pedido.nombreEstado());
		assertThrows(MovimientoEstadoInvalido.class, () -> pedido.agregarItem(producto, 1));
		assertThrows(MovimientoEstadoInvalido.class, () -> pedido.confirmar());
		assertThrows(MovimientoEstadoInvalido.class, () -> pedido.prepararEnvio());
		assertThrows(MovimientoEstadoInvalido.class, () -> pedido.enviar());
		assertThrows(MovimientoEstadoInvalido.class, () -> pedido.entregar());
		assertThrows(MovimientoEstadoInvalido.class, () -> pedido.cancelar());
	}
}
