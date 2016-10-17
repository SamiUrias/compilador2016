package lexer.basicAutomatas;

import Automata.Automata;
import Automata.Generador_de_Automatas;

/**
 * This class represents the basicAutomata "digit"
 * @author Samuel Urias
 * @date 10/17/2016
 * @version 1.0
 */
public class DigitAutomata {
    
    /**
     * Stores the generatedAutomata
     */
    private Automata automata = null;
    
    /*Constructor*/
    public DigitAutomata(){
        /*An instance of an automata generator*/
        Generador_de_Automatas generador = new Generador_de_Automatas();
        
        /*digit*/
        String digit = "(0|1|2|3|4|5|6|7|8|9)*";
        
        Automata automata = generador.createAutomata(digit);
        automata.setNombre("digit");
        
        this.automata = automata;
    }

    public Automata getAutomata() {
        return automata;
    }

    public void setAutomata(Automata automata) {
        this.automata = automata;
    }
    
    
            
}
