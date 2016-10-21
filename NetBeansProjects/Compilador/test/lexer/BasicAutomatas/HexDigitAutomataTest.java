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
public class HexDigitAutomataTest {
    Automata hexDigitAutomata = null; 
    
    public HexDigitAutomataTest() {
    }
    
    @Before
    public void setUp() {
        HexDigitAutomata instance = new HexDigitAutomata();
        this.hexDigitAutomata = instance.getAutomata();
    }

    
    /**
     *Test of the constructor method of class HeDigitAutomata. 
     */
    @Test
    public void testHexDigitAutomata1(){
        System.out.println("HexDigit Automata test 1");
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.hexDigitAutomata);
        boolean result = simuladorAfn.hacerSimulacion("1A");
        assertEquals(expResult, result);
    }
    
    /**
     *Test of the constructor method of class HeDigitAutomata. 
     */
    @Test
    public void testHexDigitAutomata2(){
        System.out.println("HexDigit Automata test 2");
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.hexDigitAutomata);
        boolean result = simuladorAfn.hacerSimulacion("1B");
        assertEquals(expResult, result);
    }
    
    /**
     *Test of the constructor method of class HeDigitAutomata. 
     */
    @Test
    public void testHexDigitAutomata3(){
        System.out.println("HexDigit Automata test 3");
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.hexDigitAutomata);
        boolean result = simuladorAfn.hacerSimulacion("1C");
        assertEquals(expResult, result);
    }
    
    /**
     *Test of the constructor method of class HeDigitAutomata. 
     */
    @Test
    public void testHexDigitAutomata4(){
        System.out.println("HexDigit Automata test 4");
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.hexDigitAutomata);
        boolean result = simuladorAfn.hacerSimulacion("1D");
        assertEquals(expResult, result);
    }
    
    /**
     *Test of the constructor method of class HeDigitAutomata. 
     */
    @Test
    public void testHexDigitAutomata5(){
        System.out.println("HexDigit Automata test 5");
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.hexDigitAutomata);
        boolean result = simuladorAfn.hacerSimulacion("1E");
        assertEquals(expResult, result);
    }
    
    /**
     *Test of the constructor method of class HeDigitAutomata. 
     */
    @Test
    public void testHexDigitAutomata6(){
        System.out.println("HexDigit Automata test 6");
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.hexDigitAutomata);
        boolean result = simuladorAfn.hacerSimulacion("1F");
        assertEquals(expResult, result);
    }
    
    /**
     *Test of the constructor method of class HeDigitAutomata. 
     */
    @Test
    public void testHexDigitAutomata7(){
        System.out.println("HexDigit Automata test 7");
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.hexDigitAutomata);
        boolean result = simuladorAfn.hacerSimulacion("10A");
        assertEquals(expResult, result);
    }
    
     
    /**
     *Test of the constructor method of class HeDigitAutomata. 
     */
    @Test
    public void testHexDigitAutomata8(){
        System.out.println("HexDigit Automata test 8");
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.hexDigitAutomata);
        boolean result = simuladorAfn.hacerSimulacion("10AB");
        assertEquals(expResult, result);
    }
    
    /**
     *Test of the constructor method of class HeDigitAutomata. 
     */
    @Test
    public void testHexDigitAutomata9(){
        System.out.println("HexDigit Automata test 9");
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.hexDigitAutomata);
        boolean result = simuladorAfn.hacerSimulacion("10ABCDEF");
        assertEquals(expResult, result);
    }
    
    
    /**
     *Test of the constructor method of class HeDigitAutomata. 
     */
    @Test
    public void testHexDigitAutomata10(){
        System.out.println("HexDigit Automata test 10");
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.hexDigitAutomata);
        boolean result = simuladorAfn.hacerSimulacion("10ADEF");
        assertEquals(expResult, result);
    }
    
    
    /**
     *Test of the constructor method of class HeDigitAutomata. 
     */
    @Test
    public void testHexDigitAutomata11(){
        System.out.println("HexDigit Automata test 11");
        boolean expResult = false;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.hexDigitAutomata);
        boolean result = simuladorAfn.hacerSimulacion("10");
        assertEquals(expResult, result);
    }
    
    /**
     *Test of the constructor method of class HeDigitAutomata. 
     */
    @Test
    public void testHexDigitAutomata12(){
        System.out.println("HexDigit Automata test 12");
        boolean expResult = false;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.hexDigitAutomata);
        boolean result = simuladorAfn.hacerSimulacion("467467");
        assertEquals(expResult, result);
    }
    
    /**
     *Test of the constructor method of class HeDigitAutomata. 
     */
    @Test
    public void testHexDigitAutomata13(){
        System.out.println("HexDigit Automata test 13");
        boolean expResult = false;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.hexDigitAutomata);
        boolean result = simuladorAfn.hacerSimulacion("adfa");
        assertEquals(expResult, result);
    }
    
    /**
     *Test of the constructor method of class HeDigitAutomata. 
     */
    @Test
    public void testHexDigitAutomata14(){
        System.out.println("HexDigit Automata test 14");
        boolean expResult = false;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.hexDigitAutomata);
        boolean result = simuladorAfn.hacerSimulacion("ABCDEF");
        assertEquals(expResult, result);
    }
    
 
}
