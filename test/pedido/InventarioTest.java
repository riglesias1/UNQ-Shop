package pedido;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.Categoria;
import catalogo.Producto;
import excepciones.StockInsuficienteException;

public class InventarioTest {
	private Inventario inventario;
	private Producto producto;

	@BeforeEach
	void setUp() {
		inventario = new Inventario();
		producto = new Producto("P-1", "Producto", null, "Marca", Categoria.ELECTRONICA, 1000);
	}

	@Test
	void incrementarYDecrementarActualizanElStock() {
		inventario.incrementar(producto, 10);
		inventario.decrementar(producto, 4);

		assertEquals(6, inventario.stockDe(producto));
		assertTrue(inventario.estaDisponible(producto));
	}

	@Test
	void decrementarMasDeLoDisponibleLanzaStockInsuficiente() {
		inventario.incrementar(producto, 2);

		assertThrows(StockInsuficienteException.class, () -> inventario.decrementar(producto, 3));
	}

	@Test
	void productoSinStockNoEstaDisponible() {
		assertFalse(inventario.estaDisponible(producto));
		assertEquals(0, inventario.stockDe(producto));
	}
}
