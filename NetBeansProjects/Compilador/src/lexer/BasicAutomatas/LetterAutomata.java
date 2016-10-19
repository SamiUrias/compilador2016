/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexer.BasicAutomatas;

import Automata.Automata;
import Automata.Generador_de_Automatas;

/**
 * This class represents the basicAutomata "letter"
 * @author Samuel Urias
 * @date 10/17/2016
 * @version 1.0
 */
public class LetterAutomata {
    
     /**
     * Stores the generatedAutomata
     */
    private Automata automata = null;

    public LetterAutomata() {
        
        /*An instance of an automata generator*/
        Generador_de_Automatas generador = new Generador_de_Automatas();
        
       /*letter*/
        String letter = "(a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p|q|r|s|t|u|v|w|x|y|z|"
                + "A|B|C|D|E|F|G|H|I|J|K|L|M|N|O|P|Q|R|S|T|U|V|W|X|Y|Z)*";
        
        
        Automata automata = generador.createAutomata(letter);
        automata.setNombre("letter");
        
        this.automata = automata;
    }

    public Automata getAutomata() {
        return automata;
    }

    public void setAutomata(Automata automata) {
        this.automata = automata;
    }
    
    
    
}
