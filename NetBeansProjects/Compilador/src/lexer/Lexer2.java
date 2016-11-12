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

    /*Almacena los keywords creados*/
    private ArrayList<Keyword> keywordArrayList;


    /*Almacena los Tokens creados*/
    private ArrayList<Token> tokensArrayList;

    /*Esta es la linea que actualmente se eta recorriendo del archivo*/
    private int linea_actual = 0;
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
    *
    * 4: Verificacion de producciones
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

        /*Una vez terminado el analisis de 'Characters' se procede a analizar las 'Keywords'.
        * Para poder analizar las keywords se espera que en el analisis de characters de haya detectado
        * la linea que contiene la palabra 'KEYWORDS' y  se haya cambiado el estado del programa para leer keywords.
        *
        * Si no se cambio el estado del programa para leer keywords, entonces es porque no se encontro una linea
        * cuya totalidad fuera la palabra 'KEYWORDS'
        * */

        /*Modo Keywords*/
        if (analisis_actual==2){
            lexerKeywordAnalisis();
        }

        /*Una vez terminado el analisis de 'Keywords' se procede a analizar los 'Tokens'.
        * Para poder analizar los Tokens se espera que en el analisis de keywords de haya detectado
        * la linea que contiene la palabra 'TOKENS' y  se haya cambiado el estado del programa para leer Tokens.
        *
        * Si no se cambio el estado del programa para leer keywords, entonces es porque no se encontro una linea
        * cuya totalidad fuera la palabra 'TOKENS'
        * */

        /*Modo Tokens*/
        if(analisis_actual == 3){
            lexerTokenAnalisis();
        }

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


        /*Si no hay error.
        * Se busca en el documento si existe la palabra 'characters'. Si esta existe, entonces se analiza el codigo
        * en busca de 'characters'. Se asume que el resto de lo que se encuentra en el proyecto son charactersArraylist
        * hasta que se encuentra la palabra 'KEYWORDS'.*/
        if (characterWordFinder() != -1){

            /*Analiza todo el documento en busca de 'characters'*/
            for (int i = characterWordFinder() + 1; i<lines.length; i++){
                //Se asigna como linea actual la linea que actualmente esta recorriendo el ciclo for
                linea_actual = i;


                if(checkForKeyword(lines[i])){
                    System.out.println("KEYWORDS FOUND");
                    analisis_actual = 2;
                    break;
                }
                //System.out.println(lines[i]);

                /*Se remueve el punto al final de la linea*/
                /*Verifica si hay error en la escritura de la linea si no encuentra un punto
                    al final de la misma*/
                String line =  endPointFinder(lines[i], i);
                //String line =  lines[i].substring(0, lines[i].length()-1);
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
     * Este metodo se encarga de hacer el analisis para las keywords encontradas en el texto
     */
    private void lexerKeywordAnalisis(){
        /*Se inicializa el arraylist*/
        keywordArrayList = new ArrayList<>();

        System.out.println("Estamos en el 'lexerKeywordAnalisis' ");
        System.out.println(lines[linea_actual]);

        /*La linea actual con la que se supone que se entra a este metodo es la linea en la que se encuentra
        * la palabra 'KEYWORDS'. Debido a esto se le suma uno (1) al contador de linea actual antes de entrar al
        * ciclo que analiza las keywords*/

        //Se le suma uno a la linea actual
        linea_actual++;

        /*Se analiza el documento en busca de keywords*/
        for(int i = linea_actual; i<lines.length; i++){

            //Se asigna como linea actual la linea el contador del ciclo actual
            linea_actual = i;

            if (checkForTokens(lines[i])){
                System.out.println("TOKENS FOUND");
                analisis_actual = 3;
                break;
            }

            /*Se remueve el punto al final de la linea*/
            /*Verifica si hay error en la escritura de la linea si no encuentra un punto
                al final de la misma*/
            String line =  endPointFinder(lines[i], i);
            //String line =  lines[i].substring(0, lines[i].length()-1);
            System.out.println(line);

            int equalPosition = equalPositionFinder(line);
            if (equalPosition != -1){
                String name = line.substring(0, equalPosition );
                System.out.println("Name: " + name);
                String lexema = line.substring(equalPosition +1,line.length());
                System.out.println("Lexema: " + lexema);

                keywordArrayList.add(new Keyword(name,lexema));

            }
        }

    }

    /**
     * Este metodo se encarga de hacer el analisis para los tokens encontradas en el texto
     */
    private void lexerTokenAnalisis() {
        System.out.println("We are in the token Analisis");
        System.out.println(lines[linea_actual]);


        /*Se asumen que al entrar en este metodo se esta en la linea que contiene la palabra
        * 'TOKENS'. Se le agrega uno a la linea actual para poder hacer el analisis.*/
        linea_actual++;

        for(int i = linea_actual; i<lines.length; i++){

            /*Revisa si se ha llegado al punto de producciones en el analisis del archivo*/
            if(checkForProduction(lines[i])){
                System.out.println("PRODUCTIONS FOUND");
                analisis_actual = 4;
                break;
            }

            /*En esa ocasion no se remueve el punto al final de la linea, ya que es un requisito para los tokens que
               estos terminen con punto.Se remueve el punto al final de la linea*/
            String line =  lines[i];
            System.out.println(line);

            int equalPosition = equalPositionFinder(line);
            if (equalPosition != -1){
                String name = line.substring(0, equalPosition -1);
                System.out.println("Name: " + name);
                String lexema = line.substring(equalPosition +1,line.length());
                System.out.println("Lexema: " + lexema);

                Token token = new Token (name,lexema,charactersArraylist);
                tokensArrayList.add(token);
                System.out.println("Se ha agregado un nuevo token a la lista de tokens");

            }


        }

    }

    /**
     * Este metodo verifica si existe un punto al final de la linea.
     */
    private String endPointFinder(String line, int line_number){
        String line_without_point ="";

        if (line.substring(line.length()-1, line.length()).equals(".")){
            line_without_point = line.substring(0, line.length()-1);
        }
        else{
            System.out.println("ERROR!\nEn la linea " + line_number + " no se encontro un punto '.' al final." );
            OpExtra.imprirLinea();
            System.out.println(line);
            System.exit(0);
        }


        return line_without_point;
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

    /**
     * Este metodo revisa si se ha llegado al punto de producciones
     * @param cadena
     * @return
     */
    private boolean checkForProduction(String cadena){
        boolean isProduction = false;
        if (cadena.trim().equals("PRODUCIONS"))
            isProduction = true;

        return isProduction;
    }

    /**
     * Este metodo revisa si se ha llegado al punto de tokens
     * @param cadena
     * @return
     */
    private boolean checkForTokens(String cadena){
        boolean isToken = false;
        if(cadena.trim().equals("TOKENS"))
            isToken = true;

        return isToken;
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
