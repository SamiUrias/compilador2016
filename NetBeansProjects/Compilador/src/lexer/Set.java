/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexer;

import java.util.ArrayList;

/**
 *
 * @author Samuel
 */
public class Set {
    ArrayList<BasicSet> basicSets;

    Set(String cadena) {
        basicSets = basicSetAnalyzer(cadena);
    }

    private ArrayList<BasicSet> basicSetAnalyzer(String cadena){
        ArrayList<BasicSet> basicSetsFound = null;
        BasicSet dummy = new BasicSet(cadena);

        System.out.println("We are in the basic set analyzer");
        return basicSetsFound;
    }
}
