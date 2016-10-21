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
        boolean result = simuladorAfn.hacerSimulacion("2");
        assertEquals(expResult, result);
    }
    
    @Test
    public void identAutomataTest3() {
        System.out.println("Ident Automata test 3");
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.identAutomata);
        boolean result = simuladorAfn.hacerSimulacion("3");
        assertEquals(expResult, result);
    }
    
    @Test
    public void identAutomataTest4() {
        System.out.println("Ident Automata test 4");
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.identAutomata);
        boolean result = simuladorAfn.hacerSimulacion("a");
        assertEquals(expResult, result);
    }
    
    @Test
    public void identAutomataTest5() {
        System.out.println("Ident Automata test 5");
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.identAutomata);
        boolean result = simuladorAfn.hacerSimulacion("b");
        assertEquals(expResult, result);
    }
    
    @Test
    public void identAutomataTest6() {
        System.out.println("Ident Automata test 6");
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.identAutomata);
        boolean result = simuladorAfn.hacerSimulacion("abc");
        assertEquals(expResult, result);
    }

     
    @Test
    public void identAutomataTest7() {
        System.out.println("Ident Automata test 7");
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.identAutomata);
        boolean result = simuladorAfn.hacerSimulacion("abc1");
        assertEquals(expResult, result);
    }
    
    @Test
    public void identAutomataTest8() {
        System.out.println("Ident Automata test 8");
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.identAutomata);
        boolean result = simuladorAfn.hacerSimulacion("abc123");
        assertEquals(expResult, result);
    }
    
    @Test
    public void identAutomataTest9() {
        System.out.println("Ident Automata test 9");
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.identAutomata);
        boolean result = simuladorAfn.hacerSimulacion("123abc123");
        assertEquals(expResult, result);
    }
    
    @Test
    public void identAutomataTest10() {
        System.out.println("Ident Automata test 10");
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.identAutomata);
        boolean result = simuladorAfn.hacerSimulacion("123ABC123");
        assertEquals(expResult, result);
    }
    
    @Test
    public void identAutomataTest11() {
        System.out.println("Ident Automata test 11");
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.identAutomata);
        boolean result = simuladorAfn.hacerSimulacion("123ABCabc");
        assertEquals(expResult, result);
    }
    
    
    @Test
    public void identAutomataTest12() {
        System.out.println("Ident Automata test 12");
        boolean expResult = false;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.identAutomata);
        boolean result = simuladorAfn.hacerSimulacion("_123ABCabc");
        assertEquals(expResult, result);
    }
    
    @Test
    public void identAutomataTest13() {
        System.out.println("Ident Automata test 13");
        boolean expResult = false;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.identAutomata);
        boolean result = simuladorAfn.hacerSimulacion(".123ABCabc");
        assertEquals(expResult, result);
    }
    
    @Test
    public void identAutomataTest14() {
        System.out.println("Ident Automata test 14");
        boolean expResult = false;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.identAutomata);
        boolean result = simuladorAfn.hacerSimulacion("?123ABCabc");
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
