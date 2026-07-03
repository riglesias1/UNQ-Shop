package reporte;

public class FormatoTextoPlano implements VisitanteFormato {
	private String salida = new String();
	
	private void agregar(String valor) {
		this.salida += valor;
	}

	@Override
	public void visitarTabla(Tabla tabla) {
		this.agregar(tabla.getTitulo());
		this.agregar("\n");

		this.agregar(String.join(" | ", tabla.getEncabezados()));
		this.agregar("\n");

		for (Fila fila : tabla.getFilas()) {
			fila.aceptar(this);
		}
	}

	@Override
	public void visitarFila(Fila fila) {
		this.agregar(String.join(" | ", fila.getCeldas()));
		this.agregar("\n");
	}

	@Override
	public String resultado() {
		return salida.toString();
	}
}
