package pedido.estado;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.Categoria;
import catalogo.Producto;
import envio.MetodoEnvio;
import excepciones.MovimientoEstadoInvalido;
import pago.MetodoPago;
import pedido.Inventario;
import pedido.Pedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class TransicionesEstadosTest {
	private Inventario inventario;
    private Pedido pedido;
    private Producto producto;

    @BeforeEach
    void setUp() {
        inventario = mock(Inventario.class);
        pedido = new Pedido(inventario, mock(MetodoEnvio.class), mock(MetodoPago.class));
        producto = new Producto("P-1", "Producto", null, "Marca", Categoria.ELECTRONICA, 1000);
        pedido.agregarItem(producto, 2);
    }

    @Test
    void cicloDeVidaCompletoDeBorradorHastaEntregado() {
        pedido.confirmar();
        assertEquals("CONFIRMADO", pedido.nombreEstado());

        pedido.prepararEnvio();
        assertEquals("EN_PREPARACION", pedido.nombreEstado());

        pedido.enviar();
        assertEquals("ENVIADO", pedido.nombreEstado());

        pedido.entregar();
        assertEquals("ENTREGADO", pedido.nombreEstado());
    }

    @Test
    void estadoConfirmarDescuentaElStockDeCadaProductoPorSuCantidad() {
        pedido.confirmar();

        verify(inventario).decrementar(producto, 2);
    }

    @Test
    void enBorradorNoSePuedeEnviarNiPrepararElEnvio() {
        assertThrows(MovimientoEstadoInvalido.class, () -> pedido.enviar());
        assertThrows(MovimientoEstadoInvalido.class, () -> pedido.prepararEnvio());
    }

    @Test
    void estadoEntregadoNoPermiteEstadosExtra() {
        pedido.confirmar();
        pedido.prepararEnvio();
        pedido.enviar();
        pedido.entregar();

        assertThrows(MovimientoEstadoInvalido.class, () -> pedido.cancelar());
        assertThrows(MovimientoEstadoInvalido.class, () -> pedido.enviar());
    }

    @Test
    void alCambiarDeEstadoNotificaAlObservador() {
        List<String> cambios = new ArrayList<>();

        pedido.suscribir((p, anterior, nuevo) ->
            cambios.add(anterior.nombre() + "->" + nuevo.nombre()));

        pedido.confirmar();

        assertEquals(List.of("BORRADOR->CONFIRMADO"), cambios);
    }

}
