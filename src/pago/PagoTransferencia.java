package pago;

import excepciones.PagoInvalidoException;
import pedido.Pedido;

public class PagoTransferencia extends MetodoPago {
	private ApiTransferencia apiTransferencia;
	private String cbu;
	private String alias;
	private boolean programada;

	public PagoTransferencia(ApiTransferencia apiTransferencia, String cbu, String alias, boolean programada) {
		this.apiTransferencia = apiTransferencia;
		this.cbu = cbu;
		this.alias = alias;
		this.programada = programada;
	}

	@Override
	protected void validarDatos(Pedido pedido) {
		boolean esValido = apiTransferencia.validarCbuAlias(this.cbu, this.alias);
		if (!esValido) {
			throw new PagoInvalidoException("El CBU/CVU o alias de la transferencia son inválidos");
		}
	}

	@Override
	protected void reservarFondos(Pedido pedido) {
		// la transferencia no reserva fondos
	}

	@Override
	protected void ejecutarTransaccion(Pedido pedido) {
		apiTransferencia.ejecutar(pedido.totalProductos(), this.programada);
	}

	@Override
	protected void notificarResultado(ResultadoPago resultado) {
		super.notificarResultado(resultado);
		resultado.registrarComprobante("Comprobante CBU " + this.cbu + " - operación " + resultado.getCodigoTransaccion());
	}
}
