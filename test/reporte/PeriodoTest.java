package reporte;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class PeriodoTest {
	private final Periodo periodo = new Periodo(LocalDate.of(2026, 6, 1), LocalDate.of(2026, 6, 30));

	@Test
	void contieneFechasDentroDelRangoInclusive() {
		assertTrue(periodo.contiene(LocalDate.of(2026, 6, 1)));
		assertTrue(periodo.contiene(LocalDate.of(2026, 6, 15)));
		assertTrue(periodo.contiene(LocalDate.of(2026, 6, 30)));
	}

	@Test
	void noContieneFechasFueraDelRango() {
		assertFalse(periodo.contiene(LocalDate.of(2026, 5, 31)));
		assertFalse(periodo.contiene(LocalDate.of(2026, 7, 1)));
	}

	@Test
	void noContieneFechaNula() {
		assertFalse(periodo.contiene(null));
	}
}
