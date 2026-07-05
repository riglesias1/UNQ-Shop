package reporte;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.Categoria;
import catalogo.Paquete;
import catalogo.Producto;
import envio.MetodoEnvio;
import pago.MetodoPago;
import pedido.Inventario;
import pedido.Pedido;

public class ReporteProductosMasVendidosTest {
	private Producto auriculares;
	private Producto cable;
	private Periodo periodo;

	@BeforeEach
	void setUp() {
		auriculares = new Producto("P-1", "Auriculares Bluetooth", null, "Marca", Categoria.ELECTRONICA, 8000);
		cable = new Producto("P-2", "Cable USB-C", null, "Marca", Categoria.ELECTRONICA, 800);
		periodo = new Periodo(LocalDate.of(2026, 6, 1), LocalDate.of(2026, 6, 30));
	}

	private Pedido crearPedidoVendido(LocalDate fecha) {
		Pedido pedido = new Pedido(mock(Inventario.class), mock(MetodoEnvio.class), mock(MetodoPago.class));
		pedido.setFecha(fecha);
		return pedido;
	}

	@Test
	void ordenaPorUnidadesVendidasYCalculaPrecioPromedioEfectivo() {
		Pedido pedido1 = crearPedidoVendido(LocalDate.of(2026, 6, 5));
		pedido1.agregarItem(auriculares, 3);
		pedido1.confirmar();

		Pedido pedido2 = crearPedidoVendido(LocalDate.of(2026, 6, 10));
		pedido2.agregarItem(auriculares, 2);
		pedido2.agregarItem(cable, 1);
		pedido2.confirmar();

		Tabla tabla = (Tabla) new ReporteProductosMasVendidos(List.of(pedido1, pedido2)).construir(periodo);

		assertEquals(2, tabla.getFilas().size());

		assertEquals(List.of("Auriculares Bluetooth", "5", "8000.0"), tabla.getFilas().get(0).getCeldas());
		assertEquals(List.of("Cable USB-C", "1", "800.0"), tabla.getFilas().get(1).getCeldas());
	}
}
