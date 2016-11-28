package ProgramaGenerado;


import Automata.*;
import lexer.Keyword;
import lexer.SSCharacter;
import lexer.Token;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @date 27/11/2016
 *
 * Esta clase modela el programa generado del lexer.
 * Esta clase indica si una palabra pertener a algo ingresado previamente en lexer.
 * @author Moises Urias
 */
public class AnalizadorLexico {

    /*Delcaracion de atributos de la clase*/

    /*Almacena los 'characters' generados por el lexer*/
    private ArrayList<SSCharacter> charactersArrayList;

    /*Almacena los 'token' generados por el lexer*/
    private ArrayList<Token> tokensArrayList;

    /*Almacena los 'keywords' generados por el lexer*/
    private ArrayList<Keyword> keywordsArrayList;


    //Almacena todos los automatas generados ...
    ArrayList<Automata> automatasGenerados = new ArrayList<Automata>();


    //Almacena todos los simuladores generados
    ArrayList<SimuladorAFN> simuladoresArrayList = new ArrayList<SimuladorAFN>();

    /**
    * Constructor vacio.
     * Actualmente NO tiene uso.
    * */
    public AnalizadorLexico(){}

    /**
     * Este metodo inicializa un programa generado con base en los arreglos de charactesr, keywords y tokens
     * creados por el lexer.
     * @param characters
     * @param keywords
     * @param tokens
     */
    public AnalizadorLexico(ArrayList<SSCharacter> characters, ArrayList<Keyword> keywords, ArrayList<Token> tokens){
        this.charactersArrayList = characters;
        this.keywordsArrayList = keywords;
        this.tokensArrayList = tokens;
    }


    /**
     * Este metodo imprime el titulo.
     * Este metodo se utiliza para hacer debbug en consola.
     */
    public void imprimirTitulo(){
        System.out.println("\n\n");
        System.out.println("******************************************************************************");
        System.out.println("**********                    PROGRAMA  GENERADO                    **********");
        System.out.println("******************************************************************************");
    }


    /**
     * Este metodo crea los automatas de todas las expresiones regulares creadas por el lexer.
     */
    public void crearAutomatas(){


        /*Crea los automatas de CHARACTERS*/
        for (int i = 0; i<charactersArrayList.size();i++){

            //Almacena el character actual
            //SSCharacter characterActual =  charactersArrayList.get(i);


            //Imprimer los nombre de los characters
            System.out.println(charactersArrayList.get(i).getToken());

            //Se obtiene la expresion regular del Character
            String lexema = charactersArrayList.get(i).getRegularExpression();
            System.out.println(lexema);

            Generador_de_Automatas generador_de_automatas = new Generador_de_Automatas();
            //Se crea un automata con base a la expresion regular ingresada
            Automata automata = generador_de_automatas.createAutomata(lexema);

            //Se le pone nombre al automata
            automata.setNombre(charactersArrayList.get(i).getToken());

            //Se le coloca el tipo de automata correspondiente
            //0 = Characters
            automata.setLexer_kind(0);


            //Se agrega el automata generado al arreglo de automatas
            automatasGenerados.add(automata);
        }


        /*Crea los automatas de KEYWORS*/
        for (int i = 0; i<keywordsArrayList.size();i++){
            //Imprimer los nombre de los characters
            System.out.println(keywordsArrayList.get(i).getNombre());

            //Se obtiene la expresion regular del Character
            String lexema = keywordsArrayList.get(i).getLexema();
            System.out.println(lexema);

            Generador_de_Automatas generador_de_automatas = new Generador_de_Automatas();
            //Se crea un automata con base a la expresion regular ingresada
            Automata automata = generador_de_automatas.createAutomata(lexema);

            //Se le pone nombre al automata
            automata.setNombre(keywordsArrayList.get(i).getNombre());

            //Se le coloca el tipo de automata correspondiente
            //0 = Characters
            automata.setLexer_kind(1);

            //Se agrega el automata generado al arreglo de automatas
            automatasGenerados.add(automata);
        }

        System.out.println("Se van a generar los tokens");
        OpExtra.leerPantalla();

        /*Crea los automatas de TOKENS*/
        for (int i = 0; i<tokensArrayList.size();i++){
            //Imprimer los nombre de los tokens
            System.out.println(tokensArrayList.get(i).getNombre());

            //Se obtiene la expresion regular del tokens
            String lexema = tokensArrayList.get(i).getFullLexema();
            System.out.println(lexema);

            Generador_de_Automatas generador_de_automatas = new Generador_de_Automatas();
            //Se crea un automata con base a la expresion regular ingresada
            Automata automata = generador_de_automatas.createAutomata(lexema);

            //Se le pone nombre al automata
            automata.setNombre(tokensArrayList.get(i).getNombre());

            //Se le coloca el tipo de automata correspondiente
            //0 = Characters
            automata.setLexer_kind(2);

            //Se agrega el automata generado al arreglo de automatas
            automatasGenerados.add(automata);
        }


    }

    /**
     * Este metodo crea simuladores para cada uno de los automatas previamente generados
     */
    public void crearSimuladores(){
        //Se crea un simulador por cada uno de los automatas previamente generados
        for(int i=0; i<automatasGenerados.size(); i++){
            simuladoresArrayList.add(new SimuladorAFN(automatasGenerados.get(i)));
        }
    }

    /******************** GETTERS Y SETTERS*/
    public ArrayList<SSCharacter> getCharactersArrayList() {
        return charactersArrayList;
    }

    public void setCharactersArrayList(ArrayList<SSCharacter> charactersArrayList) {
        this.charactersArrayList = charactersArrayList;
    }

    public ArrayList<Token> getTokensArrayList() {
        return tokensArrayList;
    }

    public void setTokensArrayList(ArrayList<Token> tokensArrayList) {
        this.tokensArrayList = tokensArrayList;
    }

    public ArrayList<Keyword> getKeywordsArrayList() {
        return keywordsArrayList;
    }

    public void setKeywordsArrayList(ArrayList<Keyword> keywordsArrayList) {
        this.keywordsArrayList = keywordsArrayList;
    }


    /**
     *ELIMINAR ESTE METODO DESPUES -.-
     * Este metodo se utiliza para probar como funcionan los simuladores creados previamente.
     */
    public void tesing(){
       boolean exit=false;
       while(!exit){
           System.out.println("Ingrese UN caracter");
           Scanner sc = new Scanner(System.in);

           String cadena_leida = "";
           while(true){
               cadena_leida = sc.nextLine();
               if(cadena_leida.length()>0){
                   break;
               }
           }
           System.out.println("El caracter que se obtuvo del usuario fue: \'" + cadena_leida + "\'");

           boolean found_once=false;                                    //Revisa si se encontro la cadena_leida
           boolean exceptKeywordFound = false;                          //Revisa si tiene except keywords


           //Encuentra la cadena_leida dentro de los simuladores.
           for(int i =0; i<simuladoresArrayList.size(); i++){

               boolean success = simuladoresArrayList.get(i).simular(cadena_leida);


               //Si la cadena_leida se encontro dentro de los simuladores
               if(success){
                   System.out.println("Keywords");
                   String nombre = simuladoresArrayList.get(i).getAutomata().getNombre();
                   for(int j=0;j<keywordsArrayList.size();j++){
                       if(nombre.equals(keywordsArrayList.get(j).getNombre())){
                           System.out.println("esta cosa es un keyword");



                           //if(tokensArrayList.get(j).g)
                           exceptKeywordFound = true;
                           OpExtra.leerPantalla();
                           break;
                       }
                   }
                   System.out.println("La cadena: \'" + cadena_leida+"\' pertenece al automata: \'"+simuladoresArrayList.get(i).getAutomata().getNombre()+"\'");
                   found_once = true;
               }
           }

           if(!exceptKeywordFound) {
               for (int i = 0; i < simuladoresArrayList.size(); i++) {
                   boolean success = simuladoresArrayList.get(i).simular(cadena_leida);
                   //Si se encontro el automata
                   if (success) {
                       System.out.println("=D");
                       System.out.println("La cadena: \'" + cadena_leida + "\' pertenece al automata: \'" + simuladoresArrayList.get(i).getAutomata().getNombre() + "\'");
                       found_once = true;
                   }
               }
           }


           if(found_once == false){
               System.out.println("D:");
           }

           if(cadena_leida.equals("exit")){
               System.out.println("Exit found");
               exit=true;
           }
       }
    }
}


