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
import excepciones.PagoInvalidoException;
import pedido.Inventario;
import pedido.Pedido;

public class PagoTarjetaCreditoTest {
	private ApiTarjetaCredito api;
	private Pedido pedido;
	
	@BeforeEach
	void setUp() {
		api = mock(ApiTarjetaCredito.class);
        pedido = new Pedido(mock(Inventario.class));
        pedido.agregarItem(new Producto("P-1", "Producto", null, "Marca", Categoria.ELECTRONICA, 1000), 2);
	}

	@Test
		void validarTarjetaCreditoFuncionaCorrectamente() {
			when(api.validarTarjeta("1234 5678 9112 3456", "999", "01/30")).thenReturn(true);
			PagoTarjetaCredito pago = new PagoTarjetaCredito(api, "1234 5678 9112 3456", "999", "01/30");
			ResultadoPago resultado = pago.procesar(pedido);
			assertEquals(true, resultado.esExitoso());
		}
	
	@Test
	void validarTarjetaCreditoNoFuncionaCorrectamente() {
		when(api.validarTarjeta("1234 5678 9112 3456", "999", "01/30")).thenReturn(false);
		PagoTarjetaCredito pago = new PagoTarjetaCredito(api, "1234 5678 9112 3456", "999", "01/30");
		assertThrows(PagoInvalidoException.class, () -> pago.procesar(pedido));
	}
	
}
	


