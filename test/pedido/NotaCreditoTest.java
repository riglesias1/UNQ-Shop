package pedido;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import excepciones.MontoInvalidoException;

public class NotaCreditoTest {

	@Test
	void guardaMotivoYMonto() {
		NotaCredito nota = new NotaCredito("Cancelacion de pedido", 1500);

		assertEquals("Cancelacion de pedido", nota.getMotivo());
		assertEquals(1500, nota.getMonto(), 0.001);
	}

	@Test
	void montoCeroONegativoEsInvalido() {
		assertThrows(MontoInvalidoException.class, () -> new NotaCredito("motivo", 0));
		assertThrows(MontoInvalidoException.class, () -> new NotaCredito("motivo", -100));
	}
}
