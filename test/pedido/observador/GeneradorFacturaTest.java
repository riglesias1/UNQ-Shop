package pedido.observador;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

public class GeneradorFacturaTest {
	private GeneradorFactura generador;
	private Pedido pedido;
	
	@BeforeEach
	void setUp() {
        pedido = new Pedido(mock(Inventario.class), mock(MetodoEnvio.class), mock(MetodoPago.class));
        pedido.agregarItem(new Producto("P-1", "Producto", null, "Marca", Categoria.ELECTRONICA, 1000), 1);
        generador = new GeneradorFactura();
        pedido.suscribir(generador);
	}

	@Test
		void noSeGeneraFacturaAntesDeEntregar() {
		pedido.confirmar();
		pedido.prepararEnvio();
		pedido.enviar();
		assertEquals(0, generador.getFacturas().size());
		}
	
	@Test
	void generaFacturaAlEntregar() {
		pedido.confirmar();
		pedido.prepararEnvio();
		pedido.enviar();
		pedido.entregar();
		assertEquals(1, generador.getFacturas().size());
		assertEquals(1000d, generador.getFacturas().get(0).getTotal());
	}
}
