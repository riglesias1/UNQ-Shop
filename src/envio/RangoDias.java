package envio;

public class RangoDias {
	private final int minimo;
	private final int maximo;
	
	public RangoDias(int minimo, int maximo) {
		this.minimo = minimo;
		this.maximo = maximo;
	}

	public int getMinimo() {
		return minimo;
	}

	public int getMaximo() {
		return maximo;
	}

	public static RangoDias entre(int minimo, int maximo) {
		return new RangoDias(minimo,maximo);
	}

	public static RangoDias exacto(int valor) {
		return new RangoDias(valor,valor);
	}
}
