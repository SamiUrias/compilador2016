package lexer;
import Automata.*;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import lexer.BasicAutomatas.BasicAutomataUtilities;
import sun.nio.ch.WindowsAsynchronousChannelProvider;

/**
 * Esta clase Lexer2 es la clase que se utiliza para hacer el analisis lexico 
 *  para la fase No.2 del proyecto. 
 * @author Samuel on 22/09/2016
 */
public class Lexer2 {
    /*Documento que se va a analizar*/
    private String documento;
    
    /*Almacena las lineas del archivo de texto*/
    private String lines[];
    
    /*Almacena los automatas básicos*/
    private ArrayList<Automata> basicAuthomatas;
    
    /*Nombre del compilador*/
    private String compiler_name = "";

    /*Almacena el resultado si hubo algun erro en la primera linea*/
    private boolean firstLineError = false;
    
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
        this.documento =  documento;
        //System.out.println(this.documento);
        this.separarLineas(this.documento);
        createBaseAutomatas();
        firstLineError = verify_correct_starting_and_ending_point(this.documento);
        charactersAnalyzer();

    }

    /**
     * This method analyze the 'CHARACTER' section of the document.
     */
    private void charactersAnalyzer(){
        System.out.println("CharactersAnalyzer");
        OpExtra.imprirLinea();
        /*If there's no error*/
        if (characterWordFinder() != -1){

            /*Analiza todo el documento en busca de 'characters'*/
            for (int i = characterWordFinder() + 1; i<lines.length; i++){
                //System.out.println(lines[i]);
                /*Remove the end line point*/
                String line =  lines[i].substring(0, lines[i].length()-1);
                System.out.println(line);

                //Crear un nuevo metodo
                int equalPosition = equalPositionFinder(line);
                if (equalPosition != -1){
                    String characterName = line.substring(0, equalPosition -1);
                    System.out.println("Character name: " + characterName);
                    String lexema = line.substring(equalPosition +1,line.length());
                    System.out.println("Lexema: " + lexema);
                    SSCharacter character = new SSCharacter(lexema);
                }
            }

        }

    }

    /**
     * Este metodo verifica si existe un punto al final de la linea.
     */
    private void endPointFinder(){

    }

    /*This method finds the equal position*/
    private int equalPositionFinder(String cadena){
        return cadena.indexOf("=");
    }


    /**
     * This method finds the 'CHARACTERS' line
     * @return
     */
    private int characterWordFinder(){
        /*Aqui se almacena la linea en donde se encuentra la palabra
            'CHARACTERS'. De no se encontrada devolvera -1*/
        int characterLine =  -1;


        /*Find characters line*/
        for (int i = 0; i<lines.length; i++){
            String actualLine = lines[i];

            if (actualLine.equals("CHARACTERS")){
                // System.out.println("Characters found =D");
                characterLine = i;
                break; //Termina el ciclo for
            }
        }

        return characterLine;
    }
    
    private void separarLineas(String documento){
        this.lines = documento.split("\\r?\\n");
        for (int i = 0; i<lines.length;i++){
            System.out.println(i+" "+lines[i]);
        };
    }
    
    
    /**
     * Este metodo crea todos los automatas basicos
     */
    private void createBaseAutomatas (){
        this.basicAuthomatas = BasicAutomataUtilities.getBasicAutomatas();
        System.out.println("------- Automatas encontrados en la lista ------------");
        for (int i=0; i<basicAuthomatas.size();i++){
            System.out.println(basicAuthomatas.get(i).getNombre());
        }
        System.out.println("-------------------------------------------------------");
    }
    
    private boolean verify_correct_starting_and_ending_point(String documento){
        boolean error = false;
        String error_log = "";
        
        /*Se revisa la primera linea*/
        String linea1 = lines[0];
        int space_position = 0;
        
        space_position = linea1.indexOf(" ");
        String part1 = linea1.substring(0, space_position);
        
        error = firstLineCompilerVerify(part1);
        error_log += firstLineCompilerErrorLogger(part1);
  
        
        String part2 = linea1.substring(space_position+1, linea1.length());
        error_log += firstLineIdentErrorLogger(part1);
            
        if (error == false){
             error = !BasicAutomataUtilities.simulateIdent(part2);
        }

        /*Se imprimen los errores encontrados*/
        System.out.println(error_log);
        
        return error;
    }
    
    /**
     * 
     * @param part1
     * @return 
     */
    private boolean firstLineCompilerVerify(String part1){
        /*Variable declaration*/
        boolean error = false;
        
        error = !part1.equals("COMPILER");
        return error;
    }
    
    /**
     * 
     * @param part1
     * @return 
     */
    private String firstLineCompilerErrorLogger(String part1){
        String error_log = "";
        if (!part1.equals("COMPILER"))
            error_log += "No se encontro la palabra 'COMPILER'\n";
        
        return error_log;
    }
    
    /**
     * 
     * @param part2
     * @return 
     */
    private String firstLineIdentErrorLogger(String part2){
        String error_log = "";
        if (BasicAutomataUtilities.simulateIdent(part2)){
            System.out.println(BasicAutomataUtilities.simulateIdent(part2));
            error_log += "No se encontro un 'ident' en la primera linea";
        }
           
        return error_log;
    }
    
    
}
