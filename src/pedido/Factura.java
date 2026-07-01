package pedido;

public class Factura {
	private final String numero;
	private final double total;

	public Factura(String numero, double total) {
		this.numero = numero;
		this.total = total;
	}

	public String getNumero() {
		return this.numero;
	}

	public double getTotal() {
		return this.total;
	}
}
