package envio;

import catalogo.ItemCatalogo;
import pedido.LineaPedido;
import pedido.Pedido;

public class RetiroEnSucursal implements MetodoEnvio {
	private Sucursal sucursal;
	
		
	public RetiroEnSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	@Override
	public double calcularCosto(Pedido pedido) {
	    return 0d;
	}

	@Override
	public RangoDias estimarDias(Pedido pedido) {
		for (LineaPedido lineaPedido : pedido.getLineas()) {
			ItemCatalogo item = lineaPedido.getItem();
			
			if (!sucursal.hayStockEnLocal(item,	lineaPedido.getCantidad())) {
				return RangoDias.entre(0, 3);
			}
		}
		
		return RangoDias.exacto(0);
	}
}
