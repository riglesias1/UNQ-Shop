package pedido;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.Categoria;
import catalogo.Producto;
import envio.MetodoEnvio;
import pago.MetodoPago;
import pedido.observador.ObservadorPedido;

public class PedidoTest {
	private Pedido pedido;
	private Producto producto;

	@BeforeEach
	void setUp() {
		pedido = new Pedido(mock(Inventario.class), mock(MetodoEnvio.class), mock(MetodoPago.class));
		producto = new Producto("P-1", "Producto", null, "Marca", Categoria.ELECTRONICA, 1000);
	}

	@Test
	void agregarElMismoItemAcumulaCantidadEnUnaSolaLinea() {
		pedido.agregarItem(producto, 2);
		pedido.agregarItem(producto, 3);

		assertEquals(1, pedido.getLineas().size());
		assertEquals(5, pedido.getLineas().get(0).getCantidad());
		assertEquals(5000.0, pedido.totalProductos(), 0.001);
	}
/*
	@Test
	void costoEnvioEsCeroSiNoHayMetodoDeEnvio() {
		pedido.agregarItem(producto, 1);
		assertEquals(0.0, pedido.costoEnvio(), 0.001);
	}*/

	@Test
	void observadorDesuscriptoNoRecibeNotificaciones() {
		ObservadorPedido observador = mock(ObservadorPedido.class);
		pedido.suscribir(observador);
		pedido.desuscribir(observador);
		pedido.agregarItem(producto, 1);

		pedido.confirmar();

		verify(observador, never()).alCambiarEstado(any(), any(), any());
	}

	@Test
	void registrarNotaCreditoGuardaElMonto() {
		pedido.registrarNotaCredito(1234.0);

		assertEquals(1, pedido.getNotasCredito().size());
		assertEquals(1234.0, pedido.getNotasCredito().get(0).getMonto(), 0.001);
	}
}
