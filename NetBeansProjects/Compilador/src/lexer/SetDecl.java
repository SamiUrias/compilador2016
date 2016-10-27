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
public class SetDecl {

    private Set set;

    /**
     * Constructor vacio.
     */
    public SetDecl() {
    }

    /**
     * Constructor con base a una cadena.
     * @param cadena
     */
    public SetDecl(String cadena) {
        set = new Set(cadena);
    }


}
