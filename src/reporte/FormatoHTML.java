package reporte;

public class FormatoHTML implements VisitanteFormato {
	private String salida = new String();

	private void agregar(String valor) {
		this.salida += valor;
	}

	@Override
	public void visitarTabla(Tabla tabla) {
		this.agregar("<h1>" + tabla.getTitulo() + "</h1>");
		this.agregar("\n");

		this.agregar("<table>");
		this.agregar("\n");

		this.agregar("<tr>");
		for (String encabezado : tabla.getEncabezados()) {
			this.agregar("<th>");
			this.agregar(encabezado);
			this.agregar("</th>");
		}
		this.agregar("</tr>");
		this.agregar("\n");

		for (Fila fila : tabla.getFilas()) {
			fila.aceptar(this);
		}
		this.agregar("</table>");
		this.agregar("\n");
	}

	@Override
	public void visitarFila(Fila fila) {
		this.agregar("<tr>");
		for (String celda : fila.getCeldas()) {
			this.agregar("<td>");
			this.agregar(celda);
			this.agregar("</td>");
		}
		this.agregar("</tr>");
		this.agregar("\n");
	}

	@Override
	public String resultado() {
		return salida.toString();
	}
}
