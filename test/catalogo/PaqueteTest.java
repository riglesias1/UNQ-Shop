package catalogo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pedido.Inventario;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class PaqueteTest {
	
	private Producto auriculares;
    private Producto funda;
    private Producto cable;
    private Paquete packAudioMovil;
	
	@BeforeEach
	void setUp() {
		auriculares = new Producto("AUR-001", "Auriculares Bluetooth", null, "Sony", Categoria.ELECTRONICA, 8000);
        funda = new Producto("FUN-001", "Funda protectora", null, "Genérica", Categoria.ELECTRONICA, 1500);
        cable = new Producto("CAB-001", "Cable USB-C", null, "Genérica", Categoria.ELECTRONICA, 800);
 
        packAudioMovil = new Paquete("Pack Audio Móvil", null, 0.15);
        packAudioMovil.agregar(auriculares);
        packAudioMovil.agregar(funda);
        packAudioMovil.agregar(cable);
	}
	
	@Test
	void precioBaseEsLaSuma() {
		assertEquals(10300, packAudioMovil.getPrecioBase(), 0.001);
	}
	
	@Test
	void precioFinalAplicaDescuento() {
		assertEquals(8755, packAudioMovil.getPrecioFinal(), 0.001);
	}

	@Test
    void precioConPaqueteAnidado() {
        Producto teclado = new Producto("TEC-001", "Teclado", null, "Logitech", Categoria.ELECTRONICA, 5000);
        Producto mouse = new Producto("MOU-001", "Mouse", null, "Logitech", Categoria.ELECTRONICA, 2000);
 
        Paquete kitHomeOffice = new Paquete("Kit Home Office", null, 0.10);
        kitHomeOffice.agregar(packAudioMovil);
        kitHomeOffice.agregar(teclado);
        kitHomeOffice.agregar(mouse);
 
        assertEquals(17300, kitHomeOffice.getPrecioBase(), 0.001);
        assertEquals(14179.5, kitHomeOffice.getPrecioFinal(), 0.001);
    }
	
	@Test
	void pesoConPaqueteAnidado() {
		Producto teclado = new Producto("TEC-001", "Teclado", null, "Logitech", Categoria.ELECTRONICA, 5000);
        Producto mouse = new Producto("MOU-001", "Mouse", null, "Logitech", Categoria.ELECTRONICA, 2000);
        Producto monitor = new Producto("MON-001", "Monitor", null, "Samsung", Categoria.ELECTRONICA, 2000);
        
        Paquete paqueteBasico = packAudioMovil;
        paqueteBasico.agregar(teclado);
        paqueteBasico.agregar(monitor);
        
        teclado.definirAtributo(new Atributo("peso", 5.5, true));
        mouse.definirAtributo(new Atributo("peso", 10d, true));
 
        Paquete kitHomeOffice = new Paquete("Kit Home Office", null, 0.10);
        kitHomeOffice.agregar(paqueteBasico);
        kitHomeOffice.agregar(mouse);
        
        assertEquals(15.5d, kitHomeOffice.getPeso());
	}
 
    @Test
    void respetaDescuentosDeLosItemsEnPaquete() {
        Producto conPromo = new Producto("PRO-001", "Parlante", null, "JBL", Categoria.ELECTRONICA, 1000, 0.20);
        Producto sinPromo = new Producto("PRO-002", "Soporte", null, "Genérica", Categoria.HOGAR, 500);
 
        Paquete combo = new Paquete("Combo", null);
        combo.agregar(conPromo);
        combo.agregar(sinPromo);

        assertEquals(1500, combo.getPrecioBase(), 0.001);
        assertEquals(1300, combo.getPrecioFinal(), 0.001);
    }
    
    @Test
    void categoriaDePaquete() {
    	Paquete paquete = packAudioMovil;
    	Producto soporte = new Producto("PRO-002", "Soporte", null, "Genérica", Categoria.HOGAR, 500);
    	paquete.agregar(soporte);
    	assertTrue(paquete.esCategoria(Categoria.HOGAR));
    	
    	paquete.quitar(soporte);
    	assertFalse(paquete.esCategoria(Categoria.HOGAR));
    }
    
    @Test
    void getersBasicos() {
    	packAudioMovil = new Paquete("Pack Audio Móvil", null, 0.15);
    	assertEquals("Pack Audio Móvil", packAudioMovil.getNombre());
    	assertEquals(null, packAudioMovil.getDescripcion());
    	assertEquals(0.15d, packAudioMovil.getDescuento());
    }

	@Test
	void productoTieneStockEnInventario() {
		Inventario inventario = new Inventario();
		inventario.incrementar(auriculares, 1);
		inventario.incrementar(funda, 1);
		inventario.incrementar(cable, 1);

		assertTrue(packAudioMovil.tieneStock(inventario, 1));
	}

	@Test
	void paqueteTieneStockSoloSiTodosSusItemsTienenStock() {
		Producto electronico = new Producto("SKU-1", "Cable", null, "M", Categoria.ELECTRONICA, 800);
		Producto hogar = new Producto("SKU-2", "Toalla", null, "M", Categoria.HOGAR, 500);
		Paquete combo = new Paquete("Combo", "desc");
		combo.agregar(electronico);
		combo.agregar(hogar);

		Inventario inventario = mock(Inventario.class);
		when(inventario.hayStock(electronico, 1)).thenReturn(true);
		when(inventario.hayStock(hogar, 1)).thenReturn(true);
		assertTrue(combo.tieneStock(inventario, 1));

		when(inventario.hayStock(hogar, 1)).thenReturn(false);
		assertFalse(combo.tieneStock(inventario, 1));
	}

    @Test
    void paqueteVacioTienePrecioCero() {
        Paquete vacio = new Paquete("Vacío", null, 0.15);
 
        assertEquals(0, vacio.getPrecioBase(), 0.001);
        assertEquals(0, vacio.getPrecioFinal(), 0.001);
    }
}
