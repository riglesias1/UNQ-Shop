package pedido.estado;

import pedido.Pedido;

public class EstadoBase implements EstadoPedido{
	
	//TODO: reemplazar por excepcion
	protected String rechazar(String operacion) {
		return "No se puede realizar la operacion " + operacion;
	}
	
		@Override
	    public void confirmar(Pedido pedido) {
	        rechazar("confirmar");
	    }
	 
	    @Override
	    public void preparar(Pedido pedido) {
	        rechazar("preparar");
	    }
	 
	    @Override
	    public void enviar(Pedido pedido) {
	        rechazar("enviar");
	    }
	 
	    @Override
	    public void entregar(Pedido pedido) {
	        rechazar("entregar");
	    }
	 
	    @Override
	    public void cancelar(Pedido pedido) {
	        rechazar("cancelar");
	    }
	
}
