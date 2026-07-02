package catalogo;

import org.junit.jupiter.api.Test;

import excepciones.PrecioInvalidoException;

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
        assertThrows(PrecioInvalidoException.class,
                () -> new Producto("P-1", "Producto", null, "marca", Categoria.HOGAR, -1));
    }

    @Test
    void descuentoFueraDeRangoEsInvalido() {
        assertThrows(PrecioInvalidoException.class,
                () -> new Producto("P-1", "Producto", null, "marca", Categoria.HOGAR, 100, 1.5));
        assertThrows(PrecioInvalidoException.class,
                () -> new Producto("P-1", "Producto", null, "marca", Categoria.HOGAR, 100, -0.2));
    }

    @Test
    void atributosDinamicos() {
        Producto producto = new Producto("P-1", "Producto", null, "m", Categoria.HOGAR, 500);
        producto.definirAtributo(new Atributo("peso", 1.2, true));
 
        Atributo peso = producto.getAtributo("peso");

        assertEquals(1, producto.getAtributos().size());
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
	void productoEqualsSegunSku() {
		Producto unSku = new Producto("SKU-1", "A", null, "M", Categoria.ELECTRONICA, 10);
		Producto mismoSku = new Producto("SKU-1", "B", null, "M", Categoria.HOGAR, 20);
		Producto otroSku = new Producto("SKU-2", "A", null, "M", Categoria.ELECTRONICA, 10);

		assertTrue(unSku.equals(mismoSku));
		assertFalse(unSku.equals(otroSku));
		assertFalse(unSku.equals(null));
		assertFalse(unSku.equals("no es un producto"));
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
