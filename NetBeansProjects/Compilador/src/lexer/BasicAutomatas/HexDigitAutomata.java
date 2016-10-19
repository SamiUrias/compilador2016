/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexer.BasicAutomatas;

import Automata.Automata;
import Automata.Generador_de_Automatas;

/**
 *
 * @author samuel
 */
public class HexDigitAutomata {
    
    private Automata automata = null;

    /*Constructor*/
    public HexDigitAutomata() {
        Generador_de_Automatas generador = new Generador_de_Automatas();
       
        String digit = "(0|1|2|3|4|5|6|7|8|9)+";
        String hexLetters = "(A|B|C|D|E|F)+";
        Automata automataDigit = generador.createAutomata(digit);
        Automata automataHexLetters = generador.createAutomata(hexLetters);
        Automata automataHexDigit = BasicAutomataUtilities.concatAuthomatas(automataDigit, automataHexLetters);
        
        automataHexDigit.setNombre("hexdigit");
        
        this.automata = automataHexDigit;
    }

    public Automata getAutomata() {
        return automata;
    }

    public void setAutomata(Automata automata) {
        this.automata = automata;
    }
    
    

}
