package catalogo;


public class Atributo {

    private final String nombre;
    private Object valor;
    private final boolean obligatorio;

    public Atributo(String nombre, boolean obligatorio) {
        this.nombre = nombre;
        this.obligatorio = obligatorio;
    }

    public Atributo(String nombre, Object valor, boolean obligatorio) {
        this(nombre, obligatorio);
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public boolean esObligatorio() {
        return obligatorio;
    }

    public boolean tieneValor() {
        return this.valor != null;
    }
}
