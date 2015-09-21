/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexer;

/**
 *
 * @author Moises Urias
 */
public class Num extends Token{
    public final int valor;
    public Num(int t) {
        super (Etiqueta.NUM); 
        valor = t;
    }
    
}
