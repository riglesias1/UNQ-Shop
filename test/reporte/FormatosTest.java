package reporte;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FormatosTest {
	private Tabla tabla;

	@BeforeEach
	void setUp() {
		tabla = new Tabla(
				"Reporte",
				List.of("Item", "Unidades"),
				List.of(new Fila(List.of("Auriculares", "5"))));
	}

	@Test
	void textoPlanoIncluyeTituloEncabezadosYFilas() {
		FormatoTextoPlano visitante = new FormatoTextoPlano();
		tabla.aceptar(visitante);

		assertEquals("Reporte\nItem | Unidades\nAuriculares | 5", visitante.resultado());
	}

	@Test
	void csvSeparaConComas() {
		FormatoCSV visitante = new FormatoCSV();
		tabla.aceptar(visitante);

		assertEquals("Item,Unidades\nAuriculares,5\n", visitante.resultado());
	}

	@Test
	void htmlUsaTagsDeTabla() {
		FormatoHTML visitante = new FormatoHTML();
		tabla.aceptar(visitante);

		String html = visitante.resultado();
		assertTrue(html.contains("<h1>Reporte</h1>"));
		assertTrue(html.contains("<th>Item</th><th>Unidades</th>"));
		assertTrue(html.contains("<td>Auriculares</td><td>5</td>"));
	}
}
