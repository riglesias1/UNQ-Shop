package reporte;

import java.util.List;

public class Tabla implements ElementoReporte {
	private final String titulo;
	private final List<String> encabezados;
	private final List<Fila> filas;

	public Tabla(String titulo, List<String> encabezados, List<Fila> filas) {
		this.titulo = titulo;
		this.encabezados = encabezados;
		this.filas = filas;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public List<String> getEncabezados() {
		return this.encabezados;
	}

	public List<Fila> getFilas() {
		return this.filas;
	}

	@Override
	public void aceptar(VisitanteFormato visitante) {
		visitante.visitarTabla(this);
	}
}
