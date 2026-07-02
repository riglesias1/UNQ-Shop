package pedido;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FacturaTest {

	@Test
	void guardaNumeroYTotal() {
		Factura factura = new Factura("F-1", 2500);

		assertEquals("F-1", factura.getNumero());
		assertEquals(2500, factura.getTotal(), 0.001);
	}
}
