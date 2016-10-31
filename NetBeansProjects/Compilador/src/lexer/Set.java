/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexer;

import Automata.OpExtra;

import java.util.ArrayList;

/**
 * Esta clase modela un set
 * @author Samuel
 */
public class Set {
    /*Debug variable*/
    boolean setDebug = false;

    /*Almacena basic sets*/
    ArrayList<BasicSet> basicSets;

    //Este arreglo almacena todos los simbolos '+' o '-' encontrados en la cadena.
    ArrayList<String> simbolos = new ArrayList<String>();

    //Este arreglo almacena todos los basicSets generados con base en las partes separadas de la cadena.
    ArrayList<BasicSet> basicSetArray = new ArrayList<BasicSet>();

    //Este arreglo amacena todos los 'SSCharactrs' creados previamente
    ArrayList<SSCharacter> charactersArray = new ArrayList<SSCharacter>();

    //Sirve para hacer el postAnalisis de las expresiones regulares
    boolean pre_Analisis = false;

    //Simbolo que se utilizo en el preAnalisis
    String pre_Analisis_simbolo = "";


    //Almacena una expresion regular
    private String regularExpression = "";
    String nombre = "";

    Set(ArrayList<SSCharacter> charactersArraylist, String ident,  String cadena) {
        this.nombre = ident;/*Asigna el nombre del set*/
        this.charactersArray = charactersArraylist;/*Asigna los SScharacters creados previamente*/

        cadena = cadena.trim();



        //Agrega los simbolos encontrados al arreglo de simbolos
        for (int i = 0; i<cadena.length()-1;i++){
            if (cadena.substring(i, i+1).equals("+") || cadena.substring(i, i+1).equals("-")){
                System.out.println("Se ha encontrado: " + cadena.substring(i, i+1));
                simbolos.add(cadena.substring(i, i+1));
            }
        }

        //Separa la cadena en un array con base a el signo mas y el signo menos
        String[] partes =  cadena.split("-|\\+");

        //Imprime todas las partes encontradas en la cadena
        for(int i = 0; i<partes.length;i++){
            System.out.println(partes[i]);
        }

        //DEBUG
        if(setDebug)
            OpExtra.leerPantalla();


        for (int i = 0; i<partes.length;i++){
            basicSetArray.add(basicSetAnalyzer(partes[i]));
        }

        System.out.println("Se termino de crear basic sets para todoas las pares de la linea");

        //DEBUG
        if(setDebug)
            OpExtra.leerPantalla();

        //Se crean las expresiones regulares con base al tipo de basic set
        for (int i=0; i<basicSetArray.size();i++){
            /*Si se hace mas de una vuelta, significa que hay varias partes que analizar, entonces se procede a
            * hacer ese analisis*/
            if (i>0){
                analizarSimbolos(i);
            }

            System.out.println("Ciclo vuelta " + i);
            createRegularExpression(basicSetArray.get(i));          //Crea una expresin regular de la cadena ingresada
            System.out.println("RX: " + regularExpression);
        }

        System.out.println("For the \'"+nombre+"\'");
        System.out.println("The final regular expression created was: " + regularExpression);

        //DEBUG
        if(setDebug) {
            OpExtra.leerPantalla();
            System.out.println();
        }
    }

    /**
     * Esta funcion altera la expresion regular que se encuentra actualmente en el sistema, dependiendo del simbolo
     * que se haya ingresado.
     * @param simbolo
     */
    private void analizarSimbolos(int simbolo){
        pre_Analisis = true;
        String simboloActual =  simbolos.get(simbolo - 1);
        System.out.println("We are in the symbolanalyzer");
        System.out.println("The symbol that we are analyzing is: " + simboloActual);

        //Analizar el signo +
        if (simboloActual.equals("+")){
            System.out.println("Se analizara el signo +");

            /*A la expresion regular actual se le agregan parentesis*/
            String nueva_expresion_regular = "(" + this.regularExpression + ")*";
            System.out.println(nueva_expresion_regular);

            //Se almacena la nueva expresion regular generada
            regularExpression =nueva_expresion_regular;

            //Se asigna como simbolo de preanalisis el simbolo encontrado
            pre_Analisis_simbolo="+";
        }

        //DEBUG
        if(setDebug)
            OpExtra.leerPantalla();


        System.out.println("Se termino de analizar los simbolos");

    }


    /**
     * Esta funcion devuelve un basicSet con base en una cadena ingresada.
     * @param cadena
     * @return
     */
    private BasicSet basicSetAnalyzer(String cadena){
        System.out.println("We are in the basic set analyzer");
        BasicSet basicSet = new BasicSet(cadena);
        return basicSet;
    }

    /**
     * Crea una expresion regular para la cadena ingresada
     * @return
     */
    private void createRegularExpression(BasicSet basicSet) {
        String regex = "";

        //Si la expresion regular es un String
        if (basicSet.isString() == true){
           regex += stringRegex(basicSet.getCadena());
        }
        //Si la expresion regular es un ident
        else if(basicSet.isIdent()){
            regex += identRegex(basicSet.getCadena());  //La cadena es el nombre que se debe buscar en el
                                                        // arraylist de characters
        }
        else if(basicSet.isChar() == true){
            regex += charRegex(basicSet);
        }

        System.out.println("Created regex: " + regex);

        /*Si es necesario hacer un postAnalisis, este metodo se encargara de hacer la correspondiente
        * modificacion de la expreion regular creada previamente para poder*/
        if (pre_Analisis){
            regex=postAnalisis(regex);
        }

        this.regularExpression += regex;
    }


    /**
     * Este metodo crea un expresion regular para un basicSet de tipo Char
     * @param basicSet
     * @return
     */
    private String charRegex(BasicSet basicSet) {
        String regex = "";
        System.out.println("We are in the char regex");
        System.out.println("Cadena: " + basicSet.getCadena());

        /*Se verifica si el basicSet tiene algun numero*/
        if (basicSet.getCharNumber() >= 0){
            regex = String.valueOf(basicSet.getCharNumber());
        }

        //DEBUG
        if(setDebug)
            OpExtra.leerPantalla();

        return regex;
    }

    /**
     * El postAnalisis se hace cada vez que se crea una nueva expresion regular.
     * Esto se hace dependiendo si anteriormente hubo un preAnalisis o no, de ser asi. Se adapta la nueva expresion
     * regular de tal manera que esta se adapte a la expresion regular tratada previamente por el preAnalisis.
     *
     * Esto se hace ccn el fin de que con ayuda del preAnalisis la expresion regular resultante se adapte a lo esta
     * descrito por la correspondiente asignacion de Characters.
     * @param generatedRegex
     * @return
     */
    private String postAnalisis(String generatedRegex){
        String new_regex = "";
        System.out.println("Estamos en el post analisis");
        System.out.println("El regex en el post analisis es: " + generatedRegex);

        /*Se hace el postAnalisis si el simbolo del preAnalisis es un signo + */
        if (pre_Analisis_simbolo.equals("+")){
            /*Se agregan parentes tipo kleene a la segunda expresion generada*/
            new_regex = "(" + generatedRegex+")*";
        }

        return new_regex;
    }

    /**
     * Crea una expresion regular si la cadena expresada es una String
     * @return
     */
    private String stringRegex(String cadenaIngresada){
        System.out.println("STRING REGEX");
        String cadena = cadenaIngresada.trim();
        String cadenaOr = "";  //Una cadena con or

        //Quita las comillas
        cadena = cadena.substring(1,cadena.length()-1);
        System.out.println(cadena);

        for (int i = 0; i<cadena.length(); i++){
            cadenaOr += cadena.substring(i,i+1);
            cadenaOr += "|";
        }

        System.out.println(cadenaOr);
        //Quita el ultimo signo de or agregado por el ciclo for
        cadenaOr = cadenaOr.substring(0,cadenaOr.length()-1);
        System.out.println(cadenaOr);

        //DEBUG
        if(setDebug)
            OpExtra.leerPantalla();

        return cadenaOr;
    }

    private String identRegex(String ident){
        String regex = "";
        System.out.println("We are in the identRegex");
        System.out.println("Ident: " + ident);


        //DEBUG
        if(setDebug)
            OpExtra.leerPantalla();

        /*Encuentra si existe algun elemento en el array que contenga algun character con el nombre que tiene el ident*/
        int posicion = -1;
        for(int i = 0; i<charactersArray.size();i++){
            if (charactersArray.get(i).getToken().equals(ident))
                    posicion = i;
        }
        System.out.println("ident position: " + posicion);
        if (posicion != -1){
            System.out.println("Si se encontro el ident: \'" +ident +
                    "\' dentro de la lista de Characters generados previamente");

            regex += charactersArray.get(posicion).getLexema().getSet().getRegularExpression();
            System.out.println("Regex del ident: ");
            System.out.println(regex + "\n\n");

        }
        else{
            System.out.println("NO se encontro el ident: " +ident +
                    " dentro de la lista de Characters generados previamente");
            System.exit(0);
        }
        return regex;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRegularExpression() {
        return regularExpression;
    }
}
