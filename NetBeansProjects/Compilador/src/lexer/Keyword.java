package lexer;

import lexer.BasicAutomatas.BasicAutomataUtilities;

/**
 * Created by Samuel on 31/10/2016.
 */
public class Keyword {

    private String nombre;

    private String lexema;

    /**
     * Para este constructor de String se utiliza el nombre que se ha de asignarle al keyword, que seria lo que esta
     * al lado izquierdo del signo igual, y un 'pseudo lexema', lo que corresponde al lado derecho del signo igual.
     *
     * Con base en estos dos argumentos, se construye un keyword.
     * @param nombre
     * @param lexema
     */
    public Keyword(String nombre, String lexema){
        this.nombre = nombre.trim();
        this.lexema = lexemaAnalyzer(lexema);
    }

    /**
     * Este metodo construye una expresion regular para el lexema con base en el pseudoLexema.
     * */
    private String lexemaAnalyzer(String pseudoLexema){
        /*Lexema creado*/
        String lexema = "";

        if (BasicAutomataUtilities.stringVerifier(pseudoLexema)){
            System.out.println("El pseudoLexema es un String");
            lexema = BasicAutomataUtilities.stringRegex(pseudoLexema);
        }

        /*Se verifica si hubo algun error en la creacion del keyboard.
        * Si el lexema esta vacio, significa que no paso la prueba de String y por eso no se pudo crear la expresion
        * regular. */
        if (lexema.equals("")){
            System.out.println("Error: No se pudo crear el keyboard para \'" + this.nombre +"\'");
            System.exit(0); //Temina la ejecucion del programa
        }

        return lexema;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLexema() {
        return lexema;
    }
}
