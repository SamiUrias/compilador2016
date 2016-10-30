package lexer;
import Automata.*;
import java.util.ArrayList;

import lexer.BasicAutomatas.BasicAutomataUtilities;

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

    /*Almacena los charactersArraylist creados*/
    private ArrayList<SSCharacter> charactersArraylist;

    //************************************* ERRORES *****************************************//

    /*Almacena el resultado si hubo algun error en la primera linea*/
    private boolean firstLineError = false;

    /*Esta variable almacena la ubicacion o estado actual del escanner para determinar lo que se debe hacer:
    * 0: Verificacion del nombre del archivo
    *
    * 1: Verificacion de caracterres
    *
    * 2: Verificacion de keywords
    *
    * 3: Verificacion de tokens
    * */
    private int analisis_actual = 0;
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
        separarLineas(this.documento); //Separa el archivo en lineas
        createBaseAutomatas();
        firstLineError = verify_correct_starting_and_ending_point(this.documento); //FIX
        charactersAnalyzer();

    }

    /**
     * This method analyze the 'CHARACTER' section of the document.
     */
    private void charactersAnalyzer(){
        /*Se inicializa el arreglo de characters*/
        charactersArraylist = new ArrayList<>();


        /*Cambia el estado del proyecto a analisis de caracteres*/
        analisis_actual = 1;
        System.out.println("CharactersAnalyzer");
        //OpExtra.imprirLinea();


        /*If there's no error.
        * Se busca en el documento si existe la palabra 'charactersArraylist'. Si esta existe, entonces se analiza el codigo
        * en busca de 'charactersArraylist'. Se asume que el resto de lo que se encuentra en el proyecto son charactersArraylist hasta
        * que se */
        if (characterWordFinder() != -1){

            /*Analiza todo el documento en busca de 'charactersArraylist'*/
            for (int i = characterWordFinder() + 1; i<lines.length; i++){
                //System.out.println(lines[i]);

                /*Remove the end line point*/
                String line =  lines[i].substring(0, lines[i].length()-1);
                System.out.println(line);


                //Verificador de caracter. Esto se deberia de ejecutar si el estado actual del proyecto se encuentra
                // en verificacion de caracteres
                //Crear un nuevo metodo
                int equalPosition = equalPositionFinder(line);
                if (equalPosition != -1){
                    String characterName = line.substring(0, equalPosition -1);
                    System.out.println("Character name: " + characterName);
                    String lexema = line.substring(equalPosition +1,line.length());
                    System.out.println("Lexema: " + lexema);

                    charactersArraylist.add(new SSCharacter(charactersArraylist, characterName, lexema));
                }
            }

        }

    }

    /**
     * Este metodo verifica si existe un punto al final de la linea.
     */
    private void endPointFinder(){

    }


    /**
     * Este metodo revisa si se ha llegado al punto de keywords
     * @param cadena
     * @return
     */
    private boolean checkForKeyword(String cadena){
        boolean isKeyword = false;
        if (cadena.trim().equals("KEYWORDS"))
            isKeyword = true;

        return isKeyword;
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


        /*Find charactersArraylist line*/
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
