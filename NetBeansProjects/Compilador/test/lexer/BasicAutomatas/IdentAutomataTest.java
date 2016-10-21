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
        System.out.println("Ident Automata test 1");
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.identAutomata);
        boolean result = simuladorAfn.hacerSimulacion("1");
        assertEquals(expResult, result);
    }
    
     @Test
    public void identAutomataTest2() {
        System.out.println("Ident Automata test 2");
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.identAutomata);
        boolean result = simuladorAfn.hacerSimulacion("1");
        assertEquals(expResult, result);
    }
    
     @Test
    public void identAutomataTest3() {
        System.out.println("Ident Automata test 3");
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.identAutomata);
        boolean result = simuladorAfn.hacerSimulacion("1");
        assertEquals(expResult, result);
    }

    /**
     * Test of getAutomata method, of class IdentAutomata.
     */
    @Test
    public void testGetAutomata() {
        IdentAutomata instance = new IdentAutomata();
        Automata automata  = instance.getAutomata();
        
        boolean expResult = true;
        boolean result;
        
        if (automata != null){
            result = true;
        }
        else
            result = false;
        
        assertEquals(expResult, result);
    }

   
}
