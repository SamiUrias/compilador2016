/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexer;

/**
 *
 * @author Samuel
 */
/**
 *
 * @author Samuel
 */
public class SSCharacter {

    private String token;
    private SetDecl lexema;

    /**
     * Constructor vacio
     */
    public SSCharacter(){

    }

    /**
     * Constructor con base a una cadena. 
     * @param cadena
     */
    public SSCharacter(String cadena){
        lexema = new SetDecl(cadena);
    }
}