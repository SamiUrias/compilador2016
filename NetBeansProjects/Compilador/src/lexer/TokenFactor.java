package lexer;

import lexer.BasicAutomatas.BasicAutomataUtilities;

/**
 * Created by Samuel on 31/10/2016.
 */

public class TokenFactor {

    /*Banderas*/
    boolean isString = false;
    boolean isIdent = false;
    boolean isChar = false;

    public TokenFactor(String lexema){
        System.out.println("We are in the token factor constructor");
        System.out.println("El tokenFacto que se intentara crear es: \'"+lexema+"\'");
        createTokenFactor(lexema);
    }

    private void createTokenFactor(String lexema){
        checkType(lexema);
    }

    private void checkType(String lexema){
        isString = stringVerifier(lexema);  //Revisa si el lexema ingresada es un String
        isIdent = identVerifier(lexema);    //Revisa si el lexema ingresada es un Ident
        isChar = charVerifier(lexema);      //Revisa si el lexema ingresada es un Char


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

        if((cadena.substring(0,1).equals("\'")) && (cadena.substring(cadena.length()-1,cadena.length()).equals("\'"))){
            isChar = true;
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
}
