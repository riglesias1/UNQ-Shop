package pedido;

import java.util.HashMap;
import java.util.Map;

import catalogo.ItemCatalogo;

public class Inventario {

    private Map<ItemCatalogo, Integer> stockPorProducto = new HashMap<>();


    public void incrementar(ItemCatalogo producto, int cantidad) {
        int stockActual = this.stockDe(producto);
        this.stockPorProducto.put(producto, stockActual + cantidad);
    }

    public void decrementar(ItemCatalogo producto, int cantidad) {
        int stockActual = this.stockDe(producto);

        // TODO: Ver de verificar la cantidad
        // if (stockActual < cantidad) {
        //     "error"
        // }
        this.stockPorProducto.put(producto, stockActual - cantidad);
    }

    public int stockDe(ItemCatalogo producto) {
        return this.stockPorProducto.getOrDefault(producto, 0);
    }

    public boolean estaDisponible(ItemCatalogo producto) {
        return this.stockDe(producto) > 0;
    }
}
