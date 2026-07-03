package envio;

import pedido.Pedido;

public class EnvioExpress implements MetodoEnvio {
	private Double cargoBase;
	private Double porcentajeFijo;
	
		
	public EnvioExpress(Double cargoBase, Double porcentajeFijo) {
		this.cargoBase = cargoBase;
		this.porcentajeFijo = porcentajeFijo;
	}

	@Override
	public RangoDias estimarDias(Pedido pedido) {		
		return RangoDias.exacto(1);
	}

	@Override
	public double calcularCosto(Pedido pedido){
		return (pedido.totalProductos() *  this.porcentajeFijo) + this.cargoBase;
	}
}
