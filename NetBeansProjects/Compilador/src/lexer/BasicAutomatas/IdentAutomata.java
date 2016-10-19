/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexer.BasicAutomatas;

import Automata.Automata;

/**
 * Esta clase modela un automata de tipo 'Ident'
 * @author samuel
 */
public class IdentAutomata {
    
    /*ident*/
    private Automata automata = null;
    
    public IdentAutomata(){
        
        /*ident*/
        Automata identAutomata;
        
        /*digit*/
        Automata digitAutomata = new DigitAutomata().getAutomata();
        
        /*letter*/
        Automata letterAutomata = new LetterAutomata().getAutomata();
        
        Automata temp1 = BasicAutomataUtilities.ORAutomata(digitAutomata, letterAutomata);
        identAutomata = BasicAutomataUtilities.kleeneAutomata(temp1);
        this.automata = identAutomata;
    }

    public Automata getAutomata() {
        return automata;
    }

    public void setAutomata(Automata automata) {
        this.automata = automata;
    }
    
    
}
