package catalogo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.Categoria;
import catalogo.Paquete;
import catalogo.Producto;

import static org.junit.jupiter.api.Assertions.*;


public class ProductoTest {	
   @Test
    void precioBaseEsPrecioDelProducto() {
        Producto auriculares = new Producto("AUR-001", "Auriculares Bluetooth", null, "Sony", Categoria.ELECTRONICA, 8000);
 
        assertEquals(8000, auriculares.getPrecioBase(), 0.001);
    }
 
    @Test
    void precioFinalSinDescuento() {
        Producto auriculares = new Producto("AUR-001", "Auriculares Bluetooth", null, "Sony", Categoria.ELECTRONICA, 8000);
 
        assertEquals(8000, auriculares.getPrecioFinal(), 0.001);
    }
 
    @Test
    void precioFinalConDescuento() {
        Producto enOferta = new Producto("OFE-001", "Parlante", null, "JBL", Categoria.ELECTRONICA, 1000, 0.20);
 
        assertEquals(1000, enOferta.getPrecioBase(), 0.001);
        assertEquals(800, enOferta.getPrecioFinal(), 0.001);
    }
 
    @Test
    void precioNegativoEsInvalido() {
        // TODO: Ver si hacer, el constructor rechaza un precio negativo.
    }
 
    @Test
    void descuentoFueraDeRangoEsInvalido() {
        // TODO: Ver si hacer, el constructor rechaza un descuento fuera de [0, 1].
    }

    @Test
    void atributosDinamicos() {
        Producto producto = new Producto("P-1", "Producto", null, "m", Categoria.HOGAR, 500);
        producto.definirAtributo(new Atributo("peso", 1.2, true));
 
        Atributo peso = producto.getAtributo("peso");
 
        assertEquals(1.2, peso.getValor());
        assertEquals(1.2, producto.getPeso());
        assertTrue(peso.tieneValor());
    }
 
    @Test
    void atributoInexistenteDevuelveVacio() {
        Producto producto = new Producto("P-1", "Producto", null, "m", Categoria.HOGAR, 500);
 
        assertTrue(producto.getAtributo("Alto") == null);
        assertEquals(0d, producto.getPeso());
    }
    
    @Test
    void getersBasicos() {
    	Producto producto = new Producto("P-1", "Producto", null, "m", Categoria.HOGAR, 500);
    	assertEquals("P-1", producto.getSku());
    	assertEquals("Producto", producto.getNombre());
    	assertEquals(null, producto.getDescripcion());
    	assertEquals("m", producto.getMarca());
    	assertEquals(Categoria.HOGAR, producto.getCategoria());
    	assertEquals(500, producto.getPrecioBase());
    	
    	assertTrue(producto.esCategoria(Categoria.HOGAR));
    }
}
