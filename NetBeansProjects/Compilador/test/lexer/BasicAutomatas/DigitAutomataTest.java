/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexer.BasicAutomatas;

import Automata.Automata;
import Automata.SimuladorAFN;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author samuel
 */
public class DigitAutomataTest {
    Automata digitAutomata = null; 
    
    
    public DigitAutomataTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        DigitAutomata instance = new DigitAutomata();
        this.digitAutomata = instance.getAutomata();
      
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of the constructor method of class DigitAutomata.
     * This is a digit automata. 
     */
    @Test
    public void digitAutomataTest1() {
        System.out.println("DigitAutomata");
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.digitAutomata);
        boolean result = simuladorAfn.hacerSimulacion("1234641987321656467");
        assertEquals(expResult, result);
    }
    
     
    /**
     * Test of the constructor method of class DigitAutomata.
     * This is a digit automata. 
     */
    @Test
    public void digitAutomataTest2() {
        System.out.println("DigitAutomata");
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.digitAutomata);
        boolean result = simuladorAfn.hacerSimulacion("1");
        assertEquals(expResult, result);
    }
    
     
    /**
     * Test of the constructor method of class DigitAutomata.
     * This is a digit automata. 
     */
    @Test
    public void digitAutomataTest3() {
        System.out.println("DigitAutomata");
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.digitAutomata);
        boolean result = simuladorAfn.hacerSimulacion("20");
        assertEquals(expResult, result);
    }

     /**
     * Test of the constructor method of class DigitAutomata.
     * This is a digit automata.
     */
    @Test
    public void digitAutomataTest4() {
        System.out.println("DigitAutomata");
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.digitAutomata);
        boolean result = simuladorAfn.hacerSimulacion("1234641987");
        assertEquals(expResult, result);
    }
    
     /**
     * Test of the constructor method of class DigitAutomata.
     * This is not a digit automata.
     */
    @Test
    public void digitAutomataTest5() {
        System.out.println("DigitAutomata");
        boolean expResult = false;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.digitAutomata);
        boolean result = simuladorAfn.hacerSimulacion("12346419d87321656467m");
        assertEquals(expResult, result);
    }
    /**
     * Test of the constructor method of class DigitAutomata.
     * This is not a digit automata.
     */
    @Test
    public void digitAutomataTest6() {
        System.out.println("DigitAutomata");
        boolean expResult = false;
        SimuladorAFN simuladorAfn = new SimuladorAFN(this.digitAutomata);
        boolean result = simuladorAfn.hacerSimulacion("1234641987321656467m");
        assertEquals(expResult, result);
    }
    
}
