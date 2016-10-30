/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexer;

import Automata.OpExtra;

import java.util.ArrayList;

/**
 *
 * @author Samuel
 */
public class Set {
    ArrayList<BasicSet> basicSets;

    //Este arreglo almacena todos los simbolos '+' o '-' encontrados en la cadena.
    ArrayList<String> simbolos = new ArrayList<String>();

    //Este arreglo almacena todos los basicSets generados con base en las partes separadas de la cadena.
    ArrayList<BasicSet> basicSetArray = new ArrayList<BasicSet>();
    ArrayList<SSCharacter> charactersArray = new ArrayList<SSCharacter>();

    //Almacena una expresion regular
    private String regularExpression = "";
    String nombre = "";

    Set(ArrayList<SSCharacter> charactersArraylist, String ident,  String cadena) {
        /*Asigna el nombre del set*/
        this.nombre = ident;
        this.charactersArray = charactersArraylist;

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

        OpExtra.leerPantalla();
        for (int i = 0; i<partes.length;i++){
            basicSetArray.add(basicSetAnalyzer(partes[i]));
        }

        System.out.println("Se termino de crear basic sets para todoas las pares de la linea");
        OpExtra.leerPantalla();

        //Se crean las expresiones regulares con base al tipo de basic set

        for (int i=0; i<basicSetArray.size();i++){
            createRegularExpression(basicSetArray.get(i));          //Crea una expresin regular de la cadena ingresada
        }
    }

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
            regex = stringRegex(basicSet.getCadena());
        }
        //Si la expresion regular es un ident
        else if(basicSet.isIdent()){
            regex = identRegex(basicSet.getCadena());  //La cadena es el nombre que se debe buscar en el
                                                        // arraylist de characters
        }

        System.out.println("Created regex: " + regex);
        this.regularExpression = regex;
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

        OpExtra.leerPantalla();

        return cadenaOr;
    }

    private String identRegex(String ident){
        System.out.println("We are in the identRegex");
        System.out.println("Ident: " + ident);
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

            regularExpression = charactersArray.get(posicion).getLexema().getSet().getRegularExpression();
            System.out.println("Regex del ident: ");
            System.out.println(regularExpression + "\n\n");

        }
        else{
            System.out.println("NO se encontro el ident: " +ident +
                    " dentro de la lista de Characters generados previamente");
            System.exit(0);
        }
        return "";
    }

    public String getNombre() {
        return nombre;
    }

    public String getRegularExpression() {
        return regularExpression;
    }
}
