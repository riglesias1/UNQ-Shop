package pago;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.Categoria;
import catalogo.Producto;
import envio.MetodoEnvio;
import excepciones.PagoInvalidoException;
import pedido.Inventario;
import pedido.Pedido;

public class PagoTransferenciaTest {
	private ApiTransferencia api;
	private Pedido pedido;
	
	@BeforeEach
	void setUp() {
		api = mock(ApiTransferencia.class);
        pedido = new Pedido(mock(Inventario.class), mock(MetodoEnvio.class), mock(MetodoPago.class));
        pedido.agregarItem(new Producto("P-1", "Producto", null, "Marca", Categoria.ELECTRONICA, 1000), 1);
	}

	@Test
		void validarTarjetaCreditoFuncionaCorrectamente() {
			when(api.validarCbuAlias("0001", "mi.alias")).thenReturn(true);
			PagoTransferencia pago = new PagoTransferencia(api, "0001", "mi.alias", true);
			ResultadoPago resultado = pago.procesar(pedido);
			assertEquals(true, resultado.esExitoso());
		}
	
	@Test
	void validarTarjetaCreditoNoFuncionaCorrectamente() {
		when(api.validarCbuAlias("0001", "mi.alias")).thenReturn(false);
		PagoTransferencia pago = new PagoTransferencia(api, "0001", "mi.alias", true);
		assertThrows(PagoInvalidoException.class, () -> pago.procesar(pedido));
	}
	
}
