/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exception;

/**
 *
 * @author Moises Urias
 */
public class IdentException extends Exception {

    /**
     * Creates a new instance of <code>IdentException</code> without detail
     * message.
     */
    public IdentException() {
        System.out.println("No es un ident valido");
    }

    /**
     * Constructs an instance of <code>IdentException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public IdentException(String msg) {
        super(msg);
    }
}
