/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexer;

import Automata.OpExtra;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.ArrayList;

/**
 *
 * @author Moi
 */
public class Token {

    /*Nombre del token*/
    private String nombre;

    /*tokenExpr*/
    private TokenExpr tokenExp;

    /*Almacena el resultado de evaluar si el lexema tiene un punto al final*/
    private boolean hasEndLinePoint;

    /*Almacena el resultado de evaluar si tiene 'EXCEPT KEYWORDS'*/
    private boolean hasExceptKeywords;

    /*Constructor del token*/
    public Token(String nombre, String lexema, ArrayList<SSCharacter> characters) {
        String lexemaToken = "";

        System.out.println("We are in the Token constructor");

        /*Asigna el nombre del token*/
        this.nombre = nombre.trim();

        /*Si se encontro un punto al final del lexema, se procede a crear el tokenExpr para ese lexema.
        * Para esto se procede a utilizar el mismo metodo utilizado para crear los characters.
        * TokenExpr es el encargado de hacer la union de todos los TokenTerms encontrados.*/
        this.hasEndLinePoint = checkForFinalPoint(lexema);

        /*Si el lexema no tiene un punto al finalizar, se generar un error.
        * Por el momento no se detiene la ejecucion del programa*/
        if(!checkForFinalPoint(lexema)){
            System.out.println("ERROR!!\nEl token\'"+nombre+"\n");
            OpExtra.leerPantalla();
        }

        this.hasExceptKeywords = checkForExceptKeywords(lexema);

        if (hasExceptKeywords){
            lexema = removeExceptKeywords(lexema);
        }

        /*Una vez se ha verificado que la expresion tenga un punto para finalizar, y se haya eliminado los
        * 'EXCEPT KEYWORDS' del lexema, entonces se procede a generar el tokenExpr*/

        this.tokenExp = new TokenExpr(lexema, characters);


        System.out.println("De regreso del token expression en el token"); //<---------------
    }

    private String removeExceptKeywords(String lexema){
        /*Almacena el nuevo lexema sin las keywords*/
        String new_lexema="";

        /*El nuevo lexema es el lexema sin 'EXCEPT KEYWORDS' y sin los espacios en blanco circundantes*/
        new_lexema=lexema.substring(0,lexema.indexOf("EXCEPT KEYWORDS")).trim();
        System.out.println("El new lexema es:");
        System.out.println(new_lexema);
        OpExtra.imprirLinea();
        return new_lexema;
    }

    /**
     * Este metodo revisa el lexema para ver si este contiene 'EXCEPT KEYWORDS'
     * @param lexema
     * @return
     */
    private boolean checkForExceptKeywords(String lexema){
        boolean hasExceptKeywords = false;

        if(lexema.contains("EXCEPT KEYWORDS")){
            System.out.println("It has except keywords");
            OpExtra.leerPantalla();
            hasExceptKeywords = true;
        }

        return hasExceptKeywords;
    }
    /**
     * La gramatica de los Token exigen que tengan un punto al final
     * @param lexema
     * @return
     */
    private boolean checkForFinalPoint(String lexema){
        boolean haveEndLinePoint = false;

        if (lexema.substring(lexema.length()-1, lexema.length()).equals(".")){
            System.out.println("Se encontro un punto en el token \'"+nombre+"\'");
            haveEndLinePoint = true;
        }

        return haveEndLinePoint;
    }

    
    
}
