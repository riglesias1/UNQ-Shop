package busqueda;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.Categoria;
import catalogo.Producto;
import pedido.Inventario;

public class CriteriosTest {
	private Producto auriculares;

	@BeforeEach
	void setUp() {
		auriculares = new Producto("P-1", "Auriculares Bluetooth", null, "Marca", Categoria.ELECTRONICA, 8000);
	}

	@Test
	void porNombreContieneSinDistinguirMayusculas() {
		assertTrue(new PorNombre("bluetooth").satisface(auriculares));
		assertTrue(new PorNombre("AURICULARES").satisface(auriculares));
		assertFalse(new PorNombre("cable").satisface(auriculares));
	}

	@Test
	void porPrecioMaximoUsaElPrecioFinal() {
		assertTrue(new PorPrecioMaximo(8000).satisface(auriculares));
		assertFalse(new PorPrecioMaximo(7999).satisface(auriculares));
	}

	@Test
	void porCategoria() {
		assertTrue(new PorCategoria(Categoria.ELECTRONICA).satisface(auriculares));
		assertFalse(new PorCategoria(Categoria.HOGAR).satisface(auriculares));
	}

	@Test
	void porDisponibilidadConsultaElInventario() {
		Inventario inventario = mock(Inventario.class);
		when(inventario.hayStock(auriculares, 1)).thenReturn(true);

		assertTrue(new PorDisponibilidad(inventario).satisface(auriculares));
	}

	@Test
	void andExigeTodosLosCriterios() {
		CriterioAnd and = new CriterioAnd(List.of(
				new PorCategoria(Categoria.ELECTRONICA), new PorPrecioMaximo(9000)));
		assertTrue(and.satisface(auriculares));

		CriterioAnd andFalla = new CriterioAnd(List.of(
				new PorCategoria(Categoria.ELECTRONICA), new PorPrecioMaximo(100)));
		assertFalse(andFalla.satisface(auriculares));
	}

	@Test
	void orExigeAlMenosUno() {
		CriterioOr or = new CriterioOr(List.of(
				new PorCategoria(Categoria.HOGAR), new PorNombre("auriculares")));
		assertTrue(or.satisface(auriculares));

		CriterioOr orFalla = new CriterioOr(List.of(
				new PorCategoria(Categoria.HOGAR), new PorNombre("cable")));
		assertFalse(orFalla.satisface(auriculares));
	}

	@Test
	void notNiegaElCriterioEnvuelto() {
		assertFalse(new CriterioNot(new PorCategoria(Categoria.ELECTRONICA)).satisface(auriculares));
		assertTrue(new CriterioNot(new PorCategoria(Categoria.HOGAR)).satisface(auriculares));
	}
}
