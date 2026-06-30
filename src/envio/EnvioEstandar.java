package envio;

import pedido.LineaPedido;
import pedido.Pedido;

public class EnvioEstandar implements MetodoEnvio {
	private CorreoArgentina correoArgentina;
	
		
	public EnvioEstandar(CorreoArgentina correoArgentina) {
		this.correoArgentina = correoArgentina;
		
	}

	@Override
	public double calcularCosto(Pedido pedido) {
	    String direccion = pedido.getDireccionEnvio();
	    double pesoTotal = this.pesoDe(pedido);
	    return correoArgentina.estimarEnvio(pesoTotal, direccion);
	}
	
	private double pesoDe(Pedido pedido) {
		Double peso = 0d;
		for (LineaPedido linea : pedido.getLineas()) {
			peso += linea.getCantidad() * linea.getItem().ge;
		}
	}

	@Override
	public RangoDias estimarDias(Pedido pedido) {
		return RangoDias.entre(5,7);
	}

}
