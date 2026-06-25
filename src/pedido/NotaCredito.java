package pedido;

public class NotaCredito {
	private String motivo;
	private double monto;

	public NotaCredito(String motivo, double monto) {
		// TODO: ver si checkeamos monto mayor a cero
		this.motivo = motivo;
		this.monto = monto;
	}

	public String getMotivo() {
		return motivo;
	}

	public double getMonto() {
		return monto;
	}	
}
