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

public class PagoBilleteraVirtualTest {
	private ApiBilleteraVirtual api;
	private Pedido pedido;
	
	@BeforeEach
	void setUp() {
		api = mock(ApiBilleteraVirtual.class);
        pedido = new Pedido(mock(Inventario.class), mock(MetodoEnvio.class), mock(MetodoPago.class));
        pedido.agregarItem(new Producto("P-1", "Producto", null, "Marca", Categoria.ELECTRONICA, 1000), 1);
	}

	@Test
		void validarTarjetaCreditoFuncionaCorrectamente() {
			when(api.saldoSuficiente(1000.0)).thenReturn(true);
			PagoBilleteraVirtual pago = new PagoBilleteraVirtual(api);
			ResultadoPago resultado = pago.procesar(pedido);
			assertEquals(true, resultado.esExitoso());
		}
	
	@Test
	void validarTarjetaCreditoNoFuncionaCorrectamente() {
		when(api.saldoSuficiente(1000.0)).thenReturn(false);
		PagoBilleteraVirtual pago = new PagoBilleteraVirtual(api);
		assertThrows(PagoInvalidoException.class, () -> pago.procesar(pedido));
	}
	
}
