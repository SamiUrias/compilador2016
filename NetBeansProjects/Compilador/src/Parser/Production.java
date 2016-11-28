package Parser;

/**
 * Created by Samuel on 26/11/2016.
 */
public class Production {
    private String nombre;
    private String lexema;
    private String atributos;

    private boolean haveAtributes = false;


    /**
     * Constructor de prouction que utiliza un nombre y un lexema.
     * @param nombre
     * @param lexema
     */
    public Production(String nombre, String lexema){
        this.nombre = nombre;
        this.lexema = lexema;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public String getAtributos() {
        return atributos;
    }

    public void setAtributos(String atributos) {
        this.atributos = atributos;
    }

    public boolean isHaveAtributes() {
        return haveAtributes;
    }

    public void setHaveAtributes(boolean haveAtributes) {
        this.haveAtributes = haveAtributes;
    }
}
