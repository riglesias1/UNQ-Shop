package reporte;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import catalogo.Categoria;
import catalogo.Producto;

public class VentaAcumuladaTest {

	@Test
	void sinUnidadesElPromedioEsCero() {
		Producto producto = new Producto("P-1", "Producto", null, "M", Categoria.ELECTRONICA, 1000);

		VentaAcumulada acumulada = new VentaAcumulada(producto);

		assertEquals(0, acumulada.getUnidades());
		assertEquals(0, acumulada.getPrecioPromedio(), 0.001);
		assertEquals(producto, acumulada.getItem());
	}
}
