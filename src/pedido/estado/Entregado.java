package pedido.estado;

public class Entregado extends EstadoBase {
	@Override
    public String nombre() {
        return "ENTREGADO";
    }

    @Override
    public boolean esNotificable(){
        return true;
    }

    @Override
	public boolean estaEntregado() {
		return true;
	}
    
    @Override
	public boolean representaVenta() {
		return true;
	}
}
