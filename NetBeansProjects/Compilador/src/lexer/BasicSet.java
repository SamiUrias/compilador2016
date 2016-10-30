/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexer;

import Automata.OpExtra;
import lexer.BasicAutomatas.BasicAutomataUtilities;
import lexer.BasicAutomatas.IdentAutomata;
import Automata.*;


/**
 *
 * @author Samuel
 */
public class BasicSet {

    private String cadena;
    private boolean isString;               //Si el BasicSet es un String
    private boolean isIdent;                //Si el BasicSet es un Ident
    private boolean isChar;                 //Si el BasicSet es un Char
    private int charNumber;                 //Si es un char, aqui se almacena el numero de ASCII
    private String regularExpression;       //Aqui se almacena la expresion regular creada por el BasicSet

    /**
     * Constructor vacio.
     */
    public BasicSet(){

    }

    /**
     * Constructor con base en una cadena.
     * @param cadena
     */
    public BasicSet(String cadena){
        System.out.println("We are in Basic Set");
        OpExtra.leerPantalla();
        /*Guarda la cadena*/
        this.cadena = cadena;

        isString = stringVerifier(cadena);  //Revisa si la cadena ingresada es un String
        isIdent = identVerifier(cadena);    //Revisa si la cadena ingresada es un Ident
        isChar = charVerifier(cadena);      //Revisa si la cadena ingresada es un Char
    }

//    Este metodo revisa si la cadena ingresada es un String
    private boolean stringVerifier(String cadena){
        boolean isString = false;
        cadena = cadena.trim(); //Elimina espacios en blanco al principio y al final de la linea.
        String firstCharacter, lastCharacter;

        firstCharacter=String.valueOf(cadena.charAt(0));
        lastCharacter=String.valueOf(cadena.charAt(cadena.length()-1));

//        System.out.println("FC: " + firstCharacter);
//        System.out.println("LC: " + lastCharacter);


        if ((firstCharacter.equals("\"")) && (lastCharacter.equals("\""))){
//            System.out.println("This lexema is an String");
            isString = true;
        }

        return isString;
    }

//    Este metodo revisa si la cadena ingresada es un ident
    private boolean identVerifier(String cadena){
        return BasicAutomataUtilities.simulateIdent(cadena);
    }

//    Este metodo revisa si la cadena ingresada es un Char
    private boolean charVerifier(String cadena){
        boolean isChar = false;
        cadena = cadena.trim();

        if (cadena.length() > 3){
            if (cadena.substring(0,4).equals("CHR(")){
                charNumber = Integer.valueOf(cadena.substring(cadena.indexOf("(")+1, cadena.indexOf(")")));
                isChar = true;
            }
        }
        return isChar;
    }

    public boolean isString() {
        return isString;
    }

    public boolean isIdent() {
        return isIdent;
    }

    public boolean isChar() {
        return isChar;
    }

    public int getCharNumber() {
        return charNumber;
    }

    public String getCadena() {
        return cadena;
    }
}
