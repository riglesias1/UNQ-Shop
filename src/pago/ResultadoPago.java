package pago;

public class ResultadoPago {
	private boolean exitoso;
	private String codigoTransaccion;
	private String comprobante;

	public ResultadoPago() {
		this.exitoso = true;
	}

	public boolean esExitoso() {
		return this.exitoso;
	}

	public void setExitoso(boolean exitoso) {
		this.exitoso = exitoso;
	}

	public String getCodigoTransaccion() {
		return this.codigoTransaccion;
	}

	public void setCodigoTransaccion(String codigoTransaccion) {
		this.codigoTransaccion = codigoTransaccion;
	}

	public String getComprobante() {
		return this.comprobante;
	}

	public void registrarComprobante(String comprobante) {
		this.comprobante = comprobante;
	}
}
