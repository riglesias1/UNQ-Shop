package reporte;

import java.time.LocalDate;

public class Periodo {
	private final LocalDate desde;
	private final LocalDate hasta;

	public Periodo(LocalDate desde, LocalDate hasta) {
		this.desde = desde;
		this.hasta = hasta;
	}

	public boolean contiene(LocalDate fecha) {
		return fecha != null && !fecha.isBefore(this.desde) && !fecha.isAfter(this.hasta);
	}
}
