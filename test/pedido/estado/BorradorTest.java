package pedido.estado;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.Categoria;
import catalogo.Producto;
import excepciones.MovimientoEstadoInvalido;
import pedido.Inventario;
import pedido.Pedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


public class BorradorTest {
	private Inventario inventario;
    private Pedido pedido;
    private Producto teclado;
    private Producto mouse;

    @BeforeEach
    void setUp() {
        inventario = mock(Inventario.class);
        pedido = new Pedido(inventario);
        teclado = new Producto("TEC-001", "Teclado", null, "Logitech", Categoria.ELECTRONICA, 5000);
        mouse = new Producto("MOU-001", "Mouse", null, "Logitech", Categoria.ELECTRONICA, 2000);
    }

    @Test
    void unPedidoNuevoArrancaVacioYEnBorrador() {
        assertTrue(pedido.estaVacio());
        assertEquals("BORRADOR", pedido.nombreEstado());
    }

    @Test
    void agregarItemsSumaProductosYAumentaElTotal() {
        pedido.agregarItem(teclado, 1);
        pedido.agregarItem(mouse, 2);

        assertEquals(2, pedido.getLineas().size());
        assertEquals(5000 + 2 * 2000, pedido.totalProductos(), 0.001);
    }

    @Test
    void agregarDosItemsIgualesAcumulaMismaLinea() {
        pedido.agregarItem(teclado, 1);
        pedido.agregarItem(teclado, 3);

        assertEquals(1, pedido.getLineas().size());
        assertEquals(4, pedido.getLineas().get(0).getCantidad());
    }

    @Test
    void sacarUnItemEliminaSuLinea() {
        pedido.agregarItem(teclado, 1);
        pedido.agregarItem(mouse, 1);

        pedido.quitarItem(teclado);

        assertEquals(1, pedido.getLineas().size());
        assertEquals(mouse, pedido.getLineas().get(0).getItem());
    }

//    @Test
//    void noSePuedeSumarItemConCantidadNegativa() {
//        // TODO: Agregar cuando gestionemos excepciones
//        assertThrows(IllegalArgumentException.class, () -> pedido.agregarItem(teclado, 0));
//    }

//    @Test
//    void noSePuedeConfirmarUnPedidoVacio() {
//    	// TODO: Agregar cuando gestionemos excepciones
//        assertThrows(MovimientoEstadoInvalido.class, () -> pedido.confirmar());
//    }

    @Test
    void soloSePuedenAgregarItemsEnBorrador() {
        pedido.agregarItem(teclado, 1);
        pedido.confirmar();

        assertThrows(MovimientoEstadoInvalido.class, () -> pedido.agregarItem(mouse, 1));
    }

}
