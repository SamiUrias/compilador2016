package Parser;

/**
 * Esta clase simula un metodo creado por el parser
 * Created by Samuel on 26/11/2016.
 */
public class Method {
    /*Se declaran los atributos*/
    private String nombre;


    /**
     * Constructor vacio
     */
    public Method(){}

    public Method(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
