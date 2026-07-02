package catalogo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AtributoTest {

	@Test
	void atributoObligatorioSinValor() {
		Atributo atributo = new Atributo("alto", true);

		assertEquals("alto", atributo.getNombre());
		assertTrue(atributo.esObligatorio());
		assertFalse(atributo.tieneValor());
	}

	@Test
	void setValorAsignaElValorYPasaATenerlo() {
		Atributo atributo = new Atributo("alto", true);

		atributo.setValor(10);

		assertTrue(atributo.tieneValor());
		assertEquals(10, atributo.getValor());
	}

	@Test
	void atributoOpcionalConValor() {
		Atributo atributo = new Atributo("color", "rojo", false);

		assertFalse(atributo.esObligatorio());
		assertTrue(atributo.tieneValor());
		assertEquals("rojo", atributo.getValor());
	}
}
