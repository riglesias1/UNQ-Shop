package pedido.estado;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import catalogo.Categoria;
import catalogo.Producto;
import envio.EnvioExpress;
import envio.MetodoEnvio;
import pago.ApiBilleteraVirtual;
import pago.MetodoPago;
import pago.PagoBilleteraVirtual;
import pedido.Inventario;
import pedido.Pedido;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


public class CanceladoTest {
    private Inventario inventario;
    private Pedido pedido;
    private Producto producto;

    @BeforeEach
    void setUp() {
        inventario = new Inventario();
        producto = new Producto("P-1", "Producto", null, "Marca", Categoria.ELECTRONICA, 1000);
        inventario.incrementar(producto, 10);

        pedido = new Pedido(inventario, mock(MetodoEnvio.class), mock(MetodoPago.class));
        pedido.setMetodoEnvio(new EnvioExpress(500d, 0d));
        pedido.setMetodoPago(new PagoBilleteraVirtual(mock(ApiBilleteraVirtual.class)));
        pedido.agregarItem(producto, 1);
    }

    @Test
    void cancelarEnBorradorNoDevuelveStockNiGeneraNotaDeCredito() {
        pedido.cancelar();

        assertEquals("CANCELADO", pedido.nombreEstado());
        assertTrue(pedido.getNotasCredito().isEmpty());
        assertEquals(10, inventario.stockDe(producto));
    }

    @Test
    void cancelarEnConfirmadoDevuelveStockYReembolsaProductoYEnvio() {
        pedido.confirmar();
        pedido.cancelar();

        assertEquals(10, inventario.stockDe(producto));
        assertEquals(1, pedido.getNotasCredito().size());
        assertEquals(1000 + 500, pedido.getNotasCredito().get(0).getMonto(), 0.001);
    }

    @Test
    void cancelarEnPreparacionDevuelveStockYReembolsaProductoYEnvio() {
        pedido.confirmar();
        pedido.prepararEnvio();
        pedido.cancelar();

        assertEquals(10, inventario.stockDe(producto));
        assertEquals(1000 + 500, pedido.getNotasCredito().get(0).getMonto(), 0.001);
    }

    @Test
    void cancelarEnEnviadoReembolsaSoloElProductoYNoDevuelveStock() {
        pedido.confirmar();
        pedido.prepararEnvio();
        pedido.enviar();
        pedido.cancelar();

        assertEquals(9, inventario.stockDe(producto));
        assertEquals(1, pedido.getNotasCredito().size());
        assertEquals(1000, pedido.getNotasCredito().get(0).getMonto(), 0.001);
    }

}
