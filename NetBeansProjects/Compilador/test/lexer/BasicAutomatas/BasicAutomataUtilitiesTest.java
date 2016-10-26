/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexer.BasicAutomatas;

import Automata.Automata;
import Automata.Generador_de_Automatas;
import Automata.OpExtra;
import Automata.SimuladorAFN;
import Automata.Subset;
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
        System.out.println("Find Automata test 1");
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
        System.out.println("Find Automata test 2");
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
        System.out.println("Get Automata test 1");
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
        System.out.println("Get Automata test 2");
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
    
     /**
      * Esta es la prueba para el metodo de orAutomata, el cual con base a dos
      * automatas ingresados genera un nuevo automata
      * 
      * Prueba del primero automata ingresado
     */
    @Test
    public void testOrAutomata1() {
        System.out.println("Or Automata test 1");
         /*ident*/
        Automata orAutomata;
        
        /*digit*/
        Automata digitAutomata = new DigitAutomata().getAutomata();
        
        /*letter*/
        Automata letterAutomata = new LetterAutomata().getAutomata();
        
        Automata temp1 = BasicAutomataUtilities.ORAutomata(digitAutomata, letterAutomata);
        
        orAutomata = temp1;
        
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(orAutomata);
        boolean result = simuladorAfn.hacerSimulacion("1");
        assertEquals(expResult, result);
    } 
    
    /**
      * Esta es la prueba para el metodo de orAutomata, el cual con base a dos
      * automatas ingresados genera un nuevo automata
      * 
      * Prueba del primero automata ingresado
     */
    @Test
    public void testOrAutomata2() {
        System.out.println("Or Automata test 2");
         /*ident*/
        Automata orAutomata;
        
        /*digit*/
        Automata digitAutomata = new DigitAutomata().getAutomata();
        
        /*letter*/
        Automata letterAutomata = new LetterAutomata().getAutomata();
        
        Automata temp1 = BasicAutomataUtilities.ORAutomata(digitAutomata, letterAutomata);
        
        orAutomata = temp1;
        
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(orAutomata);
        boolean result = simuladorAfn.hacerSimulacion("123");
        assertEquals(expResult, result);
    } 
    
    
    /**
      * Esta es la prueba para el metodo de orAutomata, el cual con base a dos
      * automatas ingresados genera un nuevo automata
      * 
      * Prueba del primero automata ingresado
     */
    @Test
    public void testOrAutomata3() {
        System.out.println("Or Automata test 3");
         /*ident*/
        Automata orAutomata;
        
        /*digit*/
        Automata digitAutomata = new DigitAutomata().getAutomata();
        
        /*letter*/
        Automata letterAutomata = new LetterAutomata().getAutomata();
        
        Automata temp1 = BasicAutomataUtilities.ORAutomata(digitAutomata, letterAutomata);
        
        orAutomata = temp1;
        
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(orAutomata);
        boolean result = simuladorAfn.hacerSimulacion("1000");
        assertEquals(expResult, result);
    } 
    
    
    /**
      * Esta es la prueba para el metodo de orAutomata, el cual con base a dos
      * automatas ingresados genera un nuevo automata
      * 
      * Prueba del primero automata ingresado
     */
    @Test
    public void testOrAutomata4() {
        System.out.println("Or automata test 4");
         /*ident*/
        Automata orAutomata;
        
        /*digit*/
        Automata digitAutomata = new DigitAutomata().getAutomata();
        
        /*letter*/
        Automata letterAutomata = new LetterAutomata().getAutomata();
        
        Automata temp1 = BasicAutomataUtilities.ORAutomata(digitAutomata, letterAutomata);
        
        orAutomata = temp1;
        
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(orAutomata);
        boolean result = simuladorAfn.hacerSimulacion("1234567890");
        assertEquals(expResult, result);
    } 
    
    /**
      * Esta es la prueba para el metodo de orAutomata, el cual con base a dos
      * automatas ingresados genera un nuevo automata
      * 
      * Prueba del segundo automata ingresado
     */
    @Test
    public void testOrAutomata5() {
        System.out.println("Or Automata test 5");
        
         /*ident*/
        Automata orAutomata;
        
        /*digit*/
        Automata digitAutomata = new DigitAutomata().getAutomata();
        
        /*letter*/
        Automata letterAutomata = new LetterAutomata().getAutomata();
        
        Automata temp1 = BasicAutomataUtilities.ORAutomata(digitAutomata, letterAutomata);
        
        orAutomata = temp1;
        
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(orAutomata);
        boolean result = simuladorAfn.hacerSimulacion("a");
        assertEquals(expResult, result);
    } 
    
    
     /**
      * Esta es la prueba para el metodo de orAutomata, el cual con base a dos
      * automatas ingresados genera un nuevo automata
      * 
      * Prueba del segundo automata ingresado
     */
    @Test
    public void testOrAutomata6() {
        System.out.println("Or Automata test 6");
         /*ident*/
        Automata orAutomata;
        
        /*digit*/
        Automata digitAutomata = new DigitAutomata().getAutomata();
        
        /*letter*/
        Automata letterAutomata = new LetterAutomata().getAutomata();
        
        Automata temp1 = BasicAutomataUtilities.ORAutomata(digitAutomata, letterAutomata);
        
        orAutomata = temp1;
        
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(orAutomata);
        boolean result = simuladorAfn.hacerSimulacion("abc");
        assertEquals(expResult, result);
    } 
    
     /**
      * Esta es la prueba para el metodo de orAutomata, el cual con base a dos
      * automatas ingresados genera un nuevo automata
      * 
      * Prueba del segundo automata ingresado
     */
    @Test
    public void testOrAutomata7() {
        System.out.println("Or automata test 7");
         /*ident*/
        Automata orAutomata;
        
        /*digit*/
        Automata digitAutomata = new DigitAutomata().getAutomata();
        
        /*letter*/
        Automata letterAutomata = new LetterAutomata().getAutomata();
        
        Automata temp1 = BasicAutomataUtilities.ORAutomata(digitAutomata, letterAutomata);
        
        orAutomata = temp1;
        
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(orAutomata);
        boolean result = simuladorAfn.hacerSimulacion("ABC");
        assertEquals(expResult, result);
    } 
    
    
     /**
      * Esta es la prueba para el metodo de orAutomata, el cual con base a dos
      * automatas ingresados genera un nuevo automata
      * 
      * Prueba del segundo automata ingresado
     */
    @Test
    public void testOrAutomata8() {
        System.out.println("Or automata test 8");
         /*ident*/
        Automata orAutomata;
        
        /*digit*/
        Automata digitAutomata = new DigitAutomata().getAutomata();
        
        /*letter*/
        Automata letterAutomata = new LetterAutomata().getAutomata();
        
        Automata temp1 = BasicAutomataUtilities.ORAutomata(digitAutomata, letterAutomata);
        
        orAutomata = temp1;
        
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(orAutomata);
        boolean result = simuladorAfn.hacerSimulacion("ABCabc");
        assertEquals(expResult, result);
    } 
    
    @Test
    public void testKleeneAutomata1(){
        System.out.println("Kleene automata test 1");
        /*Kleene*/
        Automata kleene;
        
        Generador_de_Automatas generador = new Generador_de_Automatas();
        kleene = generador.createAutomata("a");
        kleene = BasicAutomataUtilities.kleeneAutomata(kleene);
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(kleene);
        boolean result = simuladorAfn.hacerSimulacion("aa");
        assertEquals(expResult, result);   
    }
    
    @Test
    public void testKleeneAutomata2(){
        System.out.println("Kleene automata test 2");
        /*Kleene*/
        Automata kleene;
        
        Generador_de_Automatas generador = new Generador_de_Automatas();
        kleene = generador.createAutomata("a");
        kleene = BasicAutomataUtilities.kleeneAutomata(kleene);
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(kleene);
        boolean result = simuladorAfn.hacerSimulacion("aaa");
        assertEquals(expResult, result);   
    }
    
    @Test
    public void testKleeneAutomata3(){
        System.out.println("Kleene Automata test 3");
        /*Kleene*/
        Automata kleene;
        
        Generador_de_Automatas generador = new Generador_de_Automatas();
        kleene = generador.createAutomata("a");
        kleene = BasicAutomataUtilities.kleeneAutomata(kleene);
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(kleene);
        boolean result = simuladorAfn.hacerSimulacion("a");
        assertEquals(expResult, result);   
    }
    
    @Test
    public void testKleeneAutomata4(){
        /*Kleene*/
        Automata kleene;
        
        Generador_de_Automatas generador = new Generador_de_Automatas();
        kleene = generador.createAutomata("a|b");
        kleene = BasicAutomataUtilities.kleeneAutomata(kleene);
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(kleene);
        boolean result = simuladorAfn.hacerSimulacion("aaabb");
        assertEquals(expResult, result);   
    }
    
    
    @Test
    public void testKleeneAutomata5(){
        /*Kleene*/
        Automata kleene;
        
        Generador_de_Automatas generador = new Generador_de_Automatas();
        kleene = generador.createAutomata("a|b");
        kleene = BasicAutomataUtilities.kleeneAutomata(kleene);
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(kleene);
        boolean result = simuladorAfn.hacerSimulacion("bb");
        assertEquals(expResult, result);   
    }
    
    @Test
    public void testKleeneAutomata6(){
        System.out.println("Kleene Automata test 6");
        
        /*Kleene*/
        Automata kleene;
        Generador_de_Automatas generador = new Generador_de_Automatas();
        kleene = generador.createAutomata("a|b");
        kleene = BasicAutomataUtilities.kleeneAutomata(kleene);
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(kleene);
        boolean result = simuladorAfn.hacerSimulacion("bbaaaabbbbb");
        assertEquals(expResult, result);   
    }
    
    @Test
    public void testKleeneAutomata7(){
        System.out.println("Kleene Automata test 7");
        /*Kleene*/
        Automata kleene;
        
        Generador_de_Automatas generador = new Generador_de_Automatas();
        kleene = generador.createAutomata("(a)*|(b)*"); //((a)*|(b)*)*
        kleene = BasicAutomataUtilities.kleene(kleene);
        
         /*Se genera un nuevo subset con el estado inicial del automata*/
        Subset subset = new Subset(kleene.getEstadoInicial(), kleene.getTransiciones());
//        subset = OpExtra.eClosure(subset);
//        System.out.println(subset);
//        OpExtra.imprirLinea();
//        OpExtra.leerPantalla();
        
        boolean expResult = true;
        SimuladorAFN simuladorAfn = new SimuladorAFN(kleene);
        boolean result = simuladorAfn.hacerSimulacion("a");
        assertEquals(expResult, result);   
    }
    
//    @Test
//    public void testCombineAlphabets1(){
//        System.out.println("Combiene Alfabets test 1");
//         Automata digit = new DigitAutomata().getAutomata();
//         Automata letter = new LetterAutomata().getAutomata();
//         
//         System.out.println(digit.getAlfabeto());
//         System.out.println(letter.getAlfabeto());
//         
//         System.out.println(BasicAutomataUtilities.combineAlphabets(digit, letter));
//         ArrayList<String> expArrayList = new ArrayList<String>(){{
//          add(0);
//         }};
//         ArrayList<String> resultArrayList;
//         assertEquals(, resultArrayList);
//    }
}