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
public class Palabra extends Token{
    public final String lexema;
    public Palabra(int t, String s) {
        super(t); lexema = new String(s);
    }
    
}
