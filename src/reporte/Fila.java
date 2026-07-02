package reporte;

import java.util.List;

public class Fila implements ElementoReporte {
	private final List<String> celdas;

	public Fila(List<String> celdas) {
		this.celdas = celdas;
	}

	public List<String> getCeldas() {
		return this.celdas;
	}

	@Override
	public void aceptar(VisitanteFormato visitante) {
		visitante.visitarFila(this);
	}
}
