package pedido;

import excepciones.MontoInvalidoException;

public class NotaCredito {
	private String motivo;
	private double monto;

	public NotaCredito(String motivo, double monto) {
		if (monto <= 0) {
			throw new MontoInvalidoException("El monto de la nota de credito debe ser mayor a cero");
		}
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
