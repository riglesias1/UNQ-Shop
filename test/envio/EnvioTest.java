package envio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.Atributo;
import catalogo.Categoria;
import catalogo.Producto;
import pedido.Inventario;
import pedido.Pedido;

public class EnvioTest {
	private Pedido pedido;
	private Producto producto;

	@BeforeEach
	void setUp() {
		pedido = new Pedido(mock(Inventario.class));
		producto = new Producto("P-1", "Producto", null, "Marca", Categoria.ELECTRONICA, 1000);
		producto.definirAtributo(new Atributo("peso", 2.0, true));
		pedido.agregarItem(producto, 3);
	}

	@Test
	void envioEstandarUsaCorreoArgentinaConElPesoTotalYEstima5a7Dias() {
		CorreoArgentina correo = mock(CorreoArgentina.class);
		when(correo.estimarEnvio(anyDouble(), any())).thenReturn(1500.0);
		EnvioEstandar envio = new EnvioEstandar(correo);

		assertEquals(1500.0, envio.calcularCosto(pedido), 0.001);
		verify(correo).estimarEnvio(6.0, null);

		RangoDias dias = envio.estimarDias(pedido);
		assertEquals(5, dias.getMinimo());
		assertEquals(7, dias.getMaximo());
	}

	@Test
	void envioExpressCalculaPorcentajeSobreTotalMasCargoBaseYEstima1Dia() {
		EnvioExpress envio = new EnvioExpress(500.0, 0.10);

		assertEquals(3800.0, envio.calcularCosto(pedido), 0.001);
		assertEquals(1, envio.estimarDias(pedido).getMinimo());
		assertEquals(1, envio.estimarDias(pedido).getMaximo());
	}

	@Test
	void retiroEnSucursalCuestaCeroYEsInmediatoSiHayStockLocal() {
		Sucursal sucursal = mock(Sucursal.class);
		when(sucursal.hayStockEnLocal(producto)).thenReturn(true);
		RetiroEnSucursal retiro = new RetiroEnSucursal(sucursal);

		assertEquals(0.0, retiro.calcularCosto(pedido), 0.001);
		assertEquals(0, retiro.estimarDias(pedido).getMaximo());
	}

	@Test
	void retiroEnSucursalEstimaHasta3DiasSiRequiereTraslado() {
		Sucursal sucursal = mock(Sucursal.class);
		when(sucursal.hayStockEnLocal(producto)).thenReturn(false);
		RetiroEnSucursal retiro = new RetiroEnSucursal(sucursal);

		RangoDias dias = retiro.estimarDias(pedido);
		assertEquals(0, dias.getMinimo());
		assertEquals(3, dias.getMaximo());
	}

	@Test
	void sucursalDelegaEnElInventario() {
		Inventario inventario = mock(Inventario.class);
		when(inventario.estaDisponible(producto)).thenReturn(true);
		Sucursal sucursal = new Sucursal(inventario);

		assertEquals(true, sucursal.hayStockEnLocal(producto));
	}
}
