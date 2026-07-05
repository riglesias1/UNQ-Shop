package pedido.estado;

public class Cancelado extends EstadoBase {
	@Override
    public String nombre() {
        return "CANCELADO";
    }

    @Override
	public boolean estaCancelado() {
		return true;
	}
}
