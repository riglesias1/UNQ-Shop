package pago;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ResultadoPagoTest {
	
	@Test
	void nuevoResultadoEsExitoso() {
		ResultadoPago resultado = new ResultadoPago();
		resultado.setExitoso(true);
		assertTrue(resultado.esExitoso());
	}
	
	@Test
	void registrarCodigoDeComprobante() {
		ResultadoPago resultado = new ResultadoPago();
		resultado.setExitoso(false);
		resultado.registrarComprobante("comprobante");
		resultado.setCodigoTransaccion("transaccion");
		assertFalse(resultado.esExitoso());
		assertEquals(resultado.getComprobante(), "comprobante");
		assertEquals(resultado.getCodigoTransaccion(), "transaccion");
	}
}
