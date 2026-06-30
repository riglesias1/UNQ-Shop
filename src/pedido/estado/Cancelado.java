package pedido.estado;

public class Cancelado extends EstadoBase {
	@Override
    public String nombre() {
        return "CANCELADO";
    }
}
