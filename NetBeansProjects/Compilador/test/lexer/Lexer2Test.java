/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexer;

import Automata.Automata;
import Automata.Generador_de_Automatas;
import Automata.SimuladorAFN;
import java.util.ArrayList;
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
public class Lexer2Test {

    public Lexer2Test() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of analizar method, of class Lexer2.
     */
    @Test
    public void testAnalizar() {
        System.out.println("analizar");
        String documento = "";
        Lexer2 instance = new Lexer2();
        //instance.analizar(documento);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test del alfabeto del automataba basico "digit".
     */
    @Test
    public void testAlfabetoDigitAutomata() {
        System.out.println("Alfabeto Digit Automata");
        String cadena = "(0|1|2|3|4|5|6|7|8|9)*";
        Generador_de_Automatas instance = new Generador_de_Automatas();
        Automata expResult = null;
        Automata result = instance.createAutomata(cadena);
        ArrayList<String> alfabeto_digit = new ArrayList<String>() {
            {
                add("0");
                add("1");
                add("2");
                add("3");
                add("4");
                add("5");
                add("6");
                add("7");
                add("8");
                add("9");
            }
        };

        ArrayList<String> alfabeto_obtenido = result.getAlfabeto();
        assertEquals(alfabeto_digit, result.getAlfabeto());
    }

   


   
}
