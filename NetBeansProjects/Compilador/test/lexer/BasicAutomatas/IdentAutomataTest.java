/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexer.BasicAutomatas;

import Automata.Automata;
import Automata.SimuladorAFN;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author samuel
 */
public class IdentAutomataTest {
    
    Automata identAutomata = null;
    
    public IdentAutomataTest() {
    }
    
    @Before
    public void setUp() {
       IdentAutomata instance = new IdentAutomata();
       this.identAutomata = instance.getAutomata();  
    }

    @Test
    public void identAutomataTest1() {
        System.out.println("Ident Automata");
        System.out.println("---------------");
        
        for(int i = 0; i < identAutomata.getEstados().size(); i++){
            System.out.println(identAutomata.getTransiciones().get(i).toString());
        }
        
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.identAutomata);
        boolean result = simuladorAfn.hacerSimulacion("1");
        assertEquals(expResult, result);
    }

    /**
     * Test of getAutomata method, of class IdentAutomata.
     */
//    @Test
//    public void testGetAutomata() {
//        IdentAutomata instance = new IdentAutomata();
//        Automata automata  = instance.getAutomata();
//        
//        boolean expResult = true;
//        boolean result;
//        
//        if (automata != null){
//            result = true;
//            System.out.println("Transciones del test");
//            System.out.println("------------------");
//            System.out.println(automata.getTransiciones());
//        }
//        else
//            result = false;
//        
//        assertEquals(expResult, result);
//    }

   
}
