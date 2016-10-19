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
public class LetterAutomataTest {
    
    private Automata automata = null;
    
    public LetterAutomataTest() {
    }
    
    @Before
    public void setUp() {
        LetterAutomata instance = new LetterAutomata();
        this.automata = instance.getAutomata();
    }

    
    /**
     * Test of the constructor method of class LetterAutomata.
     * This is a letter automata. 
     */
    @Test
    public void digitAutomataTest1() {
        System.out.println("Letter Automata 1");
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.automata);
        boolean result = simuladorAfn.hacerSimulacion("a");
        assertEquals(expResult, result);
    }
  
    /**
     * Test of the constructor method of class LetterAutomata.
     * This is a letter automata. 
     */
    @Test
    public void digitAutomataTest2() {
        System.out.println("Letter Automata 2");
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.automata);
        boolean result = simuladorAfn.hacerSimulacion("casa");
        assertEquals(expResult, result);
    }
    
    /**
     * Test of the constructor method of class LetterAutomata.
     * This is a letter automata. 
     */
    @Test
    public void digitAutomataTest3() {
        System.out.println("Letter Automata 3");
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.automata);
        boolean result = simuladorAfn.hacerSimulacion("CASA");
        assertEquals(expResult, result);
    }
    
      
    /**
     * Test of the constructor method of class LetterAutomata.
     * This is a letter automata. 
     */
    @Test
    public void digitAutomataTest4() {
        System.out.println("Letter Automata 4");
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.automata);
        boolean result = simuladorAfn.hacerSimulacion("CaSa");
        assertEquals(expResult, result);
    }
    
     /**
     * Test of the constructor method of class LetterAutomata.
     * This is a letter automata. 
     */
    @Test
    public void digitAutomataTest5() {
        System.out.println("Letter Automata 5");
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.automata);
        boolean result = simuladorAfn.hacerSimulacion("abc");
        assertEquals(expResult, result);
    }
    
    
    /**
     * Test of the constructor method of class LetterAutomata.
     * This is a letter automata. 
     */
    @Test
    public void digitAutomataTest6() {
        System.out.println("Letter Automata 5");
        boolean expResult = false;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.automata);
        boolean result = simuladorAfn.hacerSimulacion("120");
        assertEquals(expResult, result);
    }
    
    
    /**
     * Test of the constructor method of class LetterAutomata.
     * This is a letter automata. 
     */
    @Test
    public void digitAutomataTest7() {
        System.out.println("Letter Automata 5");
        boolean expResult = false;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.automata);
        boolean result = simuladorAfn.hacerSimulacion("0");
        assertEquals(expResult, result);
    }
    
    /**
     * Test of the constructor method of class LetterAutomata.
     * This is a letter automata. 
     */
    @Test
    public void digitAutomataTest8() {
        System.out.println("Letter Automata 5");
        boolean expResult = false;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.automata);
        boolean result = simuladorAfn.hacerSimulacion("012554654");
        assertEquals(expResult, result);
    }
    
    
     /**
     * Test of the constructor method of class LetterAutomata.
     * This is a letter automata. 
     */
    @Test
    public void digitAutomataTest9() {
        System.out.println("Letter Automata 5");
        boolean expResult = false;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.automata);
        boolean result = simuladorAfn.hacerSimulacion("01255fasdf4654");
        assertEquals(expResult, result);
    }
}
