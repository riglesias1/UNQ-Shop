package pago;

import pedido.Pedido;

public class PagoTarjetaCredito extends MetodoPago {
	private ApiTarjetaCredito apiTarjeta;
	private String nroTarjeta;
	private String cvv;
	private String vencimiento;

	public PagoTarjetaCredito(ApiTarjetaCredito apiTarjeta, String nroTarjeta, String cvv, String vencimiento) {
		this.apiTarjeta = apiTarjeta;
		this.nroTarjeta = nroTarjeta;
		this.cvv = cvv;
		this.vencimiento = vencimiento;
	}

	@Override
	protected void validarDatos(Pedido pedido) {
		boolean esValida = apiTarjeta.validarTarjeta(this.nroTarjeta, this.cvv, this.vencimiento);
		if (!esValida) {
			//TODO throw error datos de tarjeta invalidos
		}
	}

	@Override
	protected void reservarFondos(Pedido pedido) {
		apiTarjeta.preAutorizar(pedido.totalProductos());
	}

	@Override
	protected void ejecutarTransaccion(Pedido pedido) {
		apiTarjeta.ejecutar(pedido.totalProductos());
	}
	
	@Override
	protected void notificarResultado(ResultadoPago resultado) {
		// TODO 
	}
	
	

}
