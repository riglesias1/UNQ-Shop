package pedido;

import java.util.HashMap;
import java.util.Map;

import catalogo.Producto;

public class Inventario {

    private Map<Producto, Integer> stockPorProducto = new HashMap<>();


    public void incrementar(Producto producto, int cantidad) {
        int stockActual = this.stockDe(producto);
        this.stockPorProducto.put(producto, stockActual + cantidad);
    }

    public void decrementar(Producto producto, int cantidad) {
        int stockActual = this.stockDe(producto);

        // TODO: Ver de verificar la cantidad
        // if (stockActual < cantidad) {
        //     "error"
        // }
        this.stockPorProducto.put(producto, stockActual - cantidad);
    }

    public int stockDe(Producto producto) {
        return this.stockPorProducto.getOrDefault(producto, 0);
    }

    public boolean estaDisponible(Producto producto) {
        return this.stockDe(producto) > 0;
    }
}
