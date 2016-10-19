/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexer.BasicAutomatas;

import Automata.Automata;
import Automata.Generador_de_Automatas;
import Automata.SimuladorAFN;
import java.util.ArrayList;
import lexer.Lexer2;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author Samuel Urias
 */
public class BasicAutomataUtilitiesTest {
    

//    /**
//     * Test of concatAuthomatas method, of class BasicAutomataUtilities.
//     */
//    @Test
//    public void testConcatAuthomatas() {
//    }
//    
//     /**
//     * This is the test of the method findAutomata of the BasicAutomataUtilities. This is the
//     * test for an automata into the array list and an expected true
//     */
//    @Test
//    public void testFindAutomata1() {
//        Generador_de_Automatas instance = new Generador_de_Automatas();
//        ArrayList<Automata> testArrayList = new ArrayList<Automata>();
//        String letter = "(a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p|q|r|s|t|u|v|w|x|y|z|"
//                + "A|B|C|D|E|F|G|H|I|J|K|L|M|N|O|P|Q|R|S|T|U|V|W|X|Y|Z)*";
//        Automata automata = instance.createAutomata(letter);
//        automata.setNombre("letter");
//        testArrayList.add(automata);
//
//        Lexer2 lexer = new Lexer2();
//        int expResult = 0;
//        int result = BasicAutomataUtilities.findAuthomata("letter", testArrayList);
//
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * This is the test of the method findAutomata of the BasicAutomataUtilities. This is the
//     * test for an automata into the array list and an expected false
//     */
//    @Test
//    public void testFindAutomata2() {
//        Generador_de_Automatas instance = new Generador_de_Automatas();
//        ArrayList<Automata> testArrayList = new ArrayList<Automata>();
//        String letter = "(a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p|q|r|s|t|u|v|w|x|y|z|"
//                + "A|B|C|D|E|F|G|H|I|J|K|L|M|N|O|P|Q|R|S|T|U|V|W|X|Y|Z)*";
//        Automata automata = instance.createAutomata(letter);
//        automata.setNombre("letter");
//        testArrayList.add(automata);
//
//        int expResult = -1;
//        int result = BasicAutomataUtilities.findAuthomata("digit", testArrayList);
//
//        assertEquals(expResult, result);
//    }
//
//    /**
//     * This is the test of the method getAutomata of the BasicAutomataUtilities. This is the test
//     * for an automata into the array list and an expected true.
//     */
//    @Test
//    public void testGetAutomata1() {
//        Generador_de_Automatas instance = new Generador_de_Automatas();
//        ArrayList<Automata> testArrayList = new ArrayList<Automata>();
//        String letter = "(a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p|q|r|s|t|u|v|w|x|y|z|"
//                + "A|B|C|D|E|F|G|H|I|J|K|L|M|N|O|P|Q|R|S|T|U|V|W|X|Y|Z)*";
//        Automata automata = instance.createAutomata(letter);
//        automata.setNombre("letter");
//        testArrayList.add(automata);
//
//        Lexer2 lexer = new Lexer2();
//        Automata expResult = automata;
//        Automata result = BasicAutomataUtilities.getAutomata("letter", testArrayList);
//
//        assertEquals(expResult, result);
//    }
//    
//    /**
//     * This is the test of the method getAutomata of the BasicAutomataUtilities. This is the test
//     * for an automata into the array list and an expected false.
//     */
//    @Test
//    public void testGetAutomata2() {
//        Generador_de_Automatas instance = new Generador_de_Automatas();
//        ArrayList<Automata> testArrayList = new ArrayList<Automata>();
//        String letter = "(a|b|c|d|e|f|g|h|i|j|k|l|m|n|o|p|q|r|s|t|u|v|w|x|y|z|"
//                + "A|B|C|D|E|F|G|H|I|J|K|L|M|N|O|P|Q|R|S|T|U|V|W|X|Y|Z)*";
//        Automata automata = instance.createAutomata(letter);
//        automata.setNombre("letter");
//        testArrayList.add(automata);
//
//        Lexer2 lexer = new Lexer2();
//        Automata expResult = null;
//        Automata result = BasicAutomataUtilities.getAutomata("digit", testArrayList);
//
//        assertEquals(expResult, result);
//    }
//    
//     /**
//      * Esta es la prueba para el metodo de orAutomata, el cual con base a dos
//      * automatas ingresados genera un nuevo automata
//      * 
//      * Prueba del primero automata ingresado
//     */
//    @Test
//    public void testOrAutomata1() {
//        
//         /*ident*/
//        Automata orAutomata;
//        
//        /*digit*/
//        Automata digitAutomata = new DigitAutomata().getAutomata();
//        
//        /*letter*/
//        Automata letterAutomata = new LetterAutomata().getAutomata();
//        
//        Automata temp1 = BasicAutomataUtilities.ORAutomata(digitAutomata, letterAutomata);
//        
//        orAutomata = temp1;
//        
//        boolean expResult = true;
//        SimuladorAFN simuladorAfn = new SimuladorAFN(orAutomata);
//        boolean result = simuladorAfn.hacerSimulacion("1");
//        assertEquals(expResult, result);
//    } 
//    
//    /**
//      * Esta es la prueba para el metodo de orAutomata, el cual con base a dos
//      * automatas ingresados genera un nuevo automata
//      * 
//      * Prueba del primero automata ingresado
//     */
//    @Test
//    public void testOrAutomata2() {
//        
//         /*ident*/
//        Automata orAutomata;
//        
//        /*digit*/
//        Automata digitAutomata = new DigitAutomata().getAutomata();
//        
//        /*letter*/
//        Automata letterAutomata = new LetterAutomata().getAutomata();
//        
//        Automata temp1 = BasicAutomataUtilities.ORAutomata(digitAutomata, letterAutomata);
//        
//        orAutomata = temp1;
//        
//        boolean expResult = true;
//        SimuladorAFN simuladorAfn = new SimuladorAFN(orAutomata);
//        boolean result = simuladorAfn.hacerSimulacion("123");
//        assertEquals(expResult, result);
//    } 
//    
//    
//    /**
//      * Esta es la prueba para el metodo de orAutomata, el cual con base a dos
//      * automatas ingresados genera un nuevo automata
//      * 
//      * Prueba del primero automata ingresado
//     */
//    @Test
//    public void testOrAutomata3() {
//        
//         /*ident*/
//        Automata orAutomata;
//        
//        /*digit*/
//        Automata digitAutomata = new DigitAutomata().getAutomata();
//        
//        /*letter*/
//        Automata letterAutomata = new LetterAutomata().getAutomata();
//        
//        Automata temp1 = BasicAutomataUtilities.ORAutomata(digitAutomata, letterAutomata);
//        
//        orAutomata = temp1;
//        
//        boolean expResult = true;
//        SimuladorAFN simuladorAfn = new SimuladorAFN(orAutomata);
//        boolean result = simuladorAfn.hacerSimulacion("1000");
//        assertEquals(expResult, result);
//    } 
//    
//    
//    /**
//      * Esta es la prueba para el metodo de orAutomata, el cual con base a dos
//      * automatas ingresados genera un nuevo automata
//      * 
//      * Prueba del primero automata ingresado
//     */
//    @Test
//    public void testOrAutomata4() {
//        
//         /*ident*/
//        Automata orAutomata;
//        
//        /*digit*/
//        Automata digitAutomata = new DigitAutomata().getAutomata();
//        
//        /*letter*/
//        Automata letterAutomata = new LetterAutomata().getAutomata();
//        
//        Automata temp1 = BasicAutomataUtilities.ORAutomata(digitAutomata, letterAutomata);
//        
//        orAutomata = temp1;
//        
//        boolean expResult = true;
//        SimuladorAFN simuladorAfn = new SimuladorAFN(orAutomata);
//        boolean result = simuladorAfn.hacerSimulacion("1234567890");
//        assertEquals(expResult, result);
//    } 
//    
//    /**
//      * Esta es la prueba para el metodo de orAutomata, el cual con base a dos
//      * automatas ingresados genera un nuevo automata
//      * 
//      * Prueba del segundo automata ingresado
//     */
//    @Test
//    public void testOrAutomata5() {
//        
//         /*ident*/
//        Automata orAutomata;
//        
//        /*digit*/
//        Automata digitAutomata = new DigitAutomata().getAutomata();
//        
//        /*letter*/
//        Automata letterAutomata = new LetterAutomata().getAutomata();
//        
//        Automata temp1 = BasicAutomataUtilities.ORAutomata(digitAutomata, letterAutomata);
//        
//        orAutomata = temp1;
//        
//        boolean expResult = true;
//        SimuladorAFN simuladorAfn = new SimuladorAFN(orAutomata);
//        boolean result = simuladorAfn.hacerSimulacion("a");
//        assertEquals(expResult, result);
//    } 
//    
//    
//     /**
//      * Esta es la prueba para el metodo de orAutomata, el cual con base a dos
//      * automatas ingresados genera un nuevo automata
//      * 
//      * Prueba del segundo automata ingresado
//     */
//    @Test
//    public void testOrAutomata6() {
//        
//         /*ident*/
//        Automata orAutomata;
//        
//        /*digit*/
//        Automata digitAutomata = new DigitAutomata().getAutomata();
//        
//        /*letter*/
//        Automata letterAutomata = new LetterAutomata().getAutomata();
//        
//        Automata temp1 = BasicAutomataUtilities.ORAutomata(digitAutomata, letterAutomata);
//        
//        orAutomata = temp1;
//        
//        boolean expResult = true;
//        SimuladorAFN simuladorAfn = new SimuladorAFN(orAutomata);
//        boolean result = simuladorAfn.hacerSimulacion("abc");
//        assertEquals(expResult, result);
//    } 
//    
//     /**
//      * Esta es la prueba para el metodo de orAutomata, el cual con base a dos
//      * automatas ingresados genera un nuevo automata
//      * 
//      * Prueba del segundo automata ingresado
//     */
//    @Test
//    public void testOrAutomata7() {
//        
//         /*ident*/
//        Automata orAutomata;
//        
//        /*digit*/
//        Automata digitAutomata = new DigitAutomata().getAutomata();
//        
//        /*letter*/
//        Automata letterAutomata = new LetterAutomata().getAutomata();
//        
//        Automata temp1 = BasicAutomataUtilities.ORAutomata(digitAutomata, letterAutomata);
//        
//        orAutomata = temp1;
//        
//        boolean expResult = true;
//        SimuladorAFN simuladorAfn = new SimuladorAFN(orAutomata);
//        boolean result = simuladorAfn.hacerSimulacion("ABC");
//        assertEquals(expResult, result);
//    } 
//    
//    
//     /**
//      * Esta es la prueba para el metodo de orAutomata, el cual con base a dos
//      * automatas ingresados genera un nuevo automata
//      * 
//      * Prueba del segundo automata ingresado
//     */
//    @Test
//    public void testOrAutomata8() {
//        
//         /*ident*/
//        Automata orAutomata;
//        
//        /*digit*/
//        Automata digitAutomata = new DigitAutomata().getAutomata();
//        
//        /*letter*/
//        Automata letterAutomata = new LetterAutomata().getAutomata();
//        
//        Automata temp1 = BasicAutomataUtilities.ORAutomata(digitAutomata, letterAutomata);
//        
//        orAutomata = temp1;
//        
//        boolean expResult = true;
//        SimuladorAFN simuladorAfn = new SimuladorAFN(orAutomata);
//        boolean result = simuladorAfn.hacerSimulacion("ABCabc");
//        assertEquals(expResult, result);
//    } 
//    
//    @Test
//    public void testKleeneAutomata1(){
//        /*Kleene*/
//        Automata kleene;
//        
//        Generador_de_Automatas generador = new Generador_de_Automatas();
//        kleene = generador.createAutomata("a");
//        kleene = BasicAutomataUtilities.kleeneAutomata(kleene);
//        boolean expResult = true;
//        SimuladorAFN simuladorAfn = new SimuladorAFN(kleene);
//        boolean result = simuladorAfn.hacerSimulacion("aa");
//        assertEquals(expResult, result);   
//    }
//    
//    @Test
//    public void testKleeneAutomata2(){
//        /*Kleene*/
//        Automata kleene;
//        
//        Generador_de_Automatas generador = new Generador_de_Automatas();
//        kleene = generador.createAutomata("a");
//        kleene = BasicAutomataUtilities.kleeneAutomata(kleene);
//        boolean expResult = true;
//        SimuladorAFN simuladorAfn = new SimuladorAFN(kleene);
//        boolean result = simuladorAfn.hacerSimulacion("aaa");
//        assertEquals(expResult, result);   
//    }
//    
//    @Test
//    public void testKleeneAutomata3(){
//        /*Kleene*/
//        Automata kleene;
//        
//        Generador_de_Automatas generador = new Generador_de_Automatas();
//        kleene = generador.createAutomata("a");
//        kleene = BasicAutomataUtilities.kleeneAutomata(kleene);
//        boolean expResult = true;
//        SimuladorAFN simuladorAfn = new SimuladorAFN(kleene);
//        boolean result = simuladorAfn.hacerSimulacion("a");
//        assertEquals(expResult, result);   
//    }
//    
//    @Test
//    public void testKleeneAutomata4(){
//        /*Kleene*/
//        Automata kleene;
//        
//        Generador_de_Automatas generador = new Generador_de_Automatas();
//        kleene = generador.createAutomata("a|b");
//        kleene = BasicAutomataUtilities.kleeneAutomata(kleene);
//        boolean expResult = true;
//        SimuladorAFN simuladorAfn = new SimuladorAFN(kleene);
//        boolean result = simuladorAfn.hacerSimulacion("aaabb");
//        assertEquals(expResult, result);   
//    }
//    
//    
//    @Test
//    public void testKleeneAutomata5(){
//        /*Kleene*/
//        Automata kleene;
//        
//        Generador_de_Automatas generador = new Generador_de_Automatas();
//        kleene = generador.createAutomata("a|b");
//        kleene = BasicAutomataUtilities.kleeneAutomata(kleene);
//        boolean expResult = true;
//        SimuladorAFN simuladorAfn = new SimuladorAFN(kleene);
//        boolean result = simuladorAfn.hacerSimulacion("bb");
//        assertEquals(expResult, result);   
//    }
//    
//    @Test
//    public void testKleeneAutomata6(){
//        /*Kleene*/
//        Automata kleene;
//        
//        Generador_de_Automatas generador = new Generador_de_Automatas();
//        kleene = generador.createAutomata("a|b");
//        kleene = BasicAutomataUtilities.kleeneAutomata(kleene);
//        boolean expResult = true;
//        SimuladorAFN simuladorAfn = new SimuladorAFN(kleene);
//        boolean result = simuladorAfn.hacerSimulacion("bbaaaabbbbb");
//        assertEquals(expResult, result);   
//    }
//    
    @Test
    public void testKleeneAutomata7(){
        /*Kleene*/
        Automata kleene;
        
        Generador_de_Automatas generador = new Generador_de_Automatas();
        kleene = generador.createAutomata("(a)*|(b)*");
        kleene = BasicAutomataUtilities.kleeneAutomata(kleene);
        
        System.out.println("Cantidad de estados:" + kleene.getEstados().size());
        
        for (int i = 0; i<kleene.getTransiciones().size(); i++){
            System.out.println(kleene.getTransiciones().get(i)); 
        }
        
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(kleene);
        boolean result = simuladorAfn.hacerSimulacion("bbaaaabbbbb");
        assertEquals(expResult, result);   
    }
    
//     @Test
//    public void testOrAutomata9x() {
//        
//         /*ident*/
//        Automata orAutomata;
//        
//        /*digit*/
//        Automata digitAutomata = new DigitAutomata().getAutomata();
//        
//        /*letter*/
//        Automata letterAutomata = new LetterAutomata().getAutomata();
//        
//        Automata temp1 = BasicAutomataUtilities.ORAutomata(digitAutomata, letterAutomata);
//        
//        orAutomata = BasicAutomataUtilities.kleeneAutomata(temp1);
//          for(int i = 0; i < orAutomata.getEstados().size(); i++){
//            System.out.println(orAutomata.getTransiciones().get(i).toString());
//        }
//          
//         System.out.println("La cantidad de transiciones es: " + orAutomata.getTransiciones().size());
//         System.out.println("La cantidad de nodos es: " + orAutomata.getEstados().size());
//        boolean expResult = true;
//        SimuladorAFN simuladorAfn = new SimuladorAFN(orAutomata);
//        boolean result = simuladorAfn.hacerSimulacion("123");
//        assertEquals(expResult, result);
//    } 
}