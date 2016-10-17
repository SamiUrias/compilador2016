package lexer;
import Automata.*;
import java.util.ArrayList;
/**
 * Esta clase Lexer2 es la clase que se utiliza para hacer el analisis lexico 
 *  para la fase No.2 del proyecto. 
 * @author Samuel on 22/09/2016
 */
public class Lexer2 {
    
    /*Almacena las lineas del archivo de texto*/
    private String lines[];
    
    /*Almacena los automatas básicos*/
    private ArrayList<Automata> basicAuthomatas;
    
    
    
    /**
     * Constructor del Lexer2
     */
    public Lexer2(){
        
    }
    /*
        ident = letter {letter | digit}.
        number = digit {digit}.
        string = '"' {anyButQuote} '"'.
        char = '\'' anyButApostrophe '\''. 
    */

    
 
    public void analizar (String documento){
        System.out.println(documento);
        this.separarLineas(documento);
        this.createBaseAutomatas();
    }
    
    private void separarLineas(String documento){
        String lines[] = documento.split("\\r?\\n");
        for (int i = 0; i<lines.length;i++){
            System.out.println(i+" "+lines[i]);
        };
    }
    
    
    /**
     * Este metodo crea todos los automatas basicos
     */
    private void createBaseAutomatas (){
      
      
    }
    
   
    
    
}
