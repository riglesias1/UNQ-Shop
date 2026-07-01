package busqueda;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.Categoria;
import catalogo.ItemCatalogo;
import catalogo.Producto;

public class CatalogoTest {
	private Producto auriculares;
	private Producto cable;
	private Producto remera;
	private Catalogo catalogo;

	@BeforeEach
	void setUp() {
		auriculares = new Producto("P-1", "Auriculares Bluetooth", null, "Marca", Categoria.ELECTRONICA, 8000);
		cable = new Producto("P-2", "Cable USB-C", null, "Marca", Categoria.ELECTRONICA, 800);
		remera = new Producto("P-3", "Remera", null, "Marca", Categoria.INDUMENTARIA, 3000);
		catalogo = new Catalogo(List.of(auriculares, cable, remera));
	}

	@Test
	void buscarFiltraSegunElCriterioRecibido() {
		List<ItemCatalogo> resultado = catalogo.buscar(new PorCategoria(Categoria.ELECTRONICA));

		assertEquals(2, resultado.size());
		assertTrue(resultado.contains(auriculares));
		assertTrue(resultado.contains(cable));
	}

	@Test
	void buscarConCriterioCompuestoAnidado() {
		CriterioBusqueda criterio = new CriterioOr(List.of(
				new CriterioAnd(List.of(new PorCategoria(Categoria.ELECTRONICA), new PorPrecioMaximo(1000))),
				new PorNombre("remera")));

		List<ItemCatalogo> resultado = catalogo.buscar(criterio);

		assertEquals(2, resultado.size());
		assertTrue(resultado.contains(cable));
		assertTrue(resultado.contains(remera));
	}
}
