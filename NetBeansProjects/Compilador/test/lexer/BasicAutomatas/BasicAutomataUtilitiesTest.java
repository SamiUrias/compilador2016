/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexer.BasicAutomatas;

import Automata.Automata;
import Automata.Generador_de_Automatas;
import java.util.ArrayList;
import lexer.Lexer2;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import lexer.basicAutomatas.BasicAutomataUtilities;
/**
 *
 * @author Samuel Urias
 */
public class BasicAutomataUtilitiesTest {
    

    /**
     * Test of concatAuthomatas method, of class BasicAutomataUtilities.
     */
    @Test
    public void testConcatAuthomatas() {
    }
    
     /**
     * This is the test of the method findAutomata of the BasicAutomataUtilities. This is the
     * test for an automata into the array list and an expected true
     */
    @Test
    public void testFindAutomata1() {
        Generador_de_Automatas instance = new Generador_de_Automatas();
        ArrayList<Automata> testArrayList = new ArrayList<Automata>();
        String letter = "(a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p|q|r|s|t|u|v|w|x|y|z|"
                + "A|B|C|D|E|F|G|H|I|J|K|L|M|N|O|P|Q|R|S|T|U|V|W|X|Y|Z)*";
        Automata automata = instance.createAutomata(letter);
        automata.setNombre("letter");
        testArrayList.add(automata);

        Lexer2 lexer = new Lexer2();
        int expResult = 0;
        int result = BasicAutomataUtilities.findAuthomata("letter", testArrayList);

        assertEquals(expResult, result);
    }

    /**
     * This is the test of the method findAutomata of the BasicAutomataUtilities. This is the
     * test for an automata into the array list and an expected false
     */
    @Test
    public void testFindAutomata2() {
        Generador_de_Automatas instance = new Generador_de_Automatas();
        ArrayList<Automata> testArrayList = new ArrayList<Automata>();
        String letter = "(a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p|q|r|s|t|u|v|w|x|y|z|"
                + "A|B|C|D|E|F|G|H|I|J|K|L|M|N|O|P|Q|R|S|T|U|V|W|X|Y|Z)*";
        Automata automata = instance.createAutomata(letter);
        automata.setNombre("letter");
        testArrayList.add(automata);

        int expResult = -1;
        int result = BasicAutomataUtilities.findAuthomata("digit", testArrayList);

        assertEquals(expResult, result);
    }

    /**
     * This is the test of the method getAutomata of the BasicAutomataUtilities. This is the test
     * for an automata into the array list and an expected true.
     */
    @Test
    public void testGetAutomata1() {
        Generador_de_Automatas instance = new Generador_de_Automatas();
        ArrayList<Automata> testArrayList = new ArrayList<Automata>();
        String letter = "(a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p|q|r|s|t|u|v|w|x|y|z|"
                + "A|B|C|D|E|F|G|H|I|J|K|L|M|N|O|P|Q|R|S|T|U|V|W|X|Y|Z)*";
        Automata automata = instance.createAutomata(letter);
        automata.setNombre("letter");
        testArrayList.add(automata);

        Lexer2 lexer = new Lexer2();
        Automata expResult = automata;
        Automata result = BasicAutomataUtilities.getAutomata("letter", testArrayList);

        assertEquals(expResult, result);
    }
    
    /**
     * This is the test of the method getAutomata of the BasicAutomataUtilities. This is the test
     * for an automata into the array list and an expected false.
     */
    @Test
    public void testGetAutomata2() {
        Generador_de_Automatas instance = new Generador_de_Automatas();
        ArrayList<Automata> testArrayList = new ArrayList<Automata>();
        String letter = "(a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p|q|r|s|t|u|v|w|x|y|z|"
                + "A|B|C|D|E|F|G|H|I|J|K|L|M|N|O|P|Q|R|S|T|U|V|W|X|Y|Z)*";
        Automata automata = instance.createAutomata(letter);
        automata.setNombre("letter");
        testArrayList.add(automata);

        Lexer2 lexer = new Lexer2();
        Automata expResult = null;
        Automata result = BasicAutomataUtilities.getAutomata("digit", testArrayList);

        assertEquals(expResult, result);
    }
    
}
