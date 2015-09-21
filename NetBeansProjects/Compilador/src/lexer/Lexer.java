/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import Exception.*;

/**
 *
 * @author Moises Urias
 */
public class Lexer {
    int contador = 0; /*Contador para la tabla de simbolos*/
    int NoToken = 0;
    boolean pertenece = false;
    boolean noimaginacion;
    
    StringTokenizer st; /*Se declara un tokeniezer para el analisis*/
    private Hashtable<String,Integer> tabla = new Hashtable<String,Integer>();
    
    /**
     * Este metodo inicializa las palabras clave del lenguaje
     */
    private void inicializar(){
        
    }
    
    public void analizar(String texto) throws IdentException, IOException{
        boolean charactersFound = false;
        int charactersLine = 0; 
        int tokensLine = 0;
        int index;
        
        AnalizadorLexico al = new AnalizadorLexico();
        String[] lineas;
        /*Se le asigna el texto ingresado al tokeniezer*/
        st = new StringTokenizer(texto, " ");

        /*Se analiza la primera linea del texto ingresado*/
        verPrimeraLinea(texto);
        System.out.println("-------------------------------------------------");

        lineas = al.devolverLineas(texto);
        for(int i=0;i<lineas.length;i++){
            System.out.println("Linea "+i+ ": "+lineas[i]);
            
            /*Se reconoce que se hayan encontrado caracteres*/
            if (lineas[i].matches("CHARACTERS")){
                System.out.println("=D Se han entrado declaraciones tipo characters: " + i);
                charactersLine = i; 
                
            }
            
            index = lineas[i].indexOf("=");
            
            if (index == -1)
            {
                System.out.println("Linea sin declaraciones");
            }
            else
            {
                System.out.println("Linea con declaraciones");
                /*Si las declaraciones ocurren despues de la declaracion de characters*/
                if (i>charactersLine){
                    System.out.println("SET");
                    setDcl(lineas[i]);
                    
                }
                else{
                    System.out.println("Esta declaracion esta en el lugar incorrecto");
                }
            }
        }
    }
    
    /**
     * Este metodo devuelve las palabras encontradas en el texto.<p> Estas 
     * palabras son delimitadas por un espacio
     * @param texto
     * @return 
     */
    private ArrayList<String> verTokens(String texto){
        ArrayList<String> tokens = new ArrayList<String>();
        
        
        System.out.println("Los tokens encontrados son: ");
        System.out.println("T: " + texto);
        StringTokenizer st = new StringTokenizer(texto," ");  
        
         while (st.hasMoreTokens()) {
             String token = st.nextToken();
             System.out.println(token);
             
             
             /*Se revisa si la palabra encontrada se encuentra entre las 
                palabras reservadas, de lo contrario esa se agrega a la misma*/
            Integer n = tabla.get(token);
             
             if (n != null) {
                 System.out.println(token + " = " + n);
             }
             else{
                 /*Se comprueba que el token sea parte del lenguaje antes de 
                    ingresarlo a la tabla de valores*/
                 isIdent(token);
                 tokens.add(token);
                 tabla.put(token, contador);
                 contador++;
             }
         } 
        
        
        return tokens; /*Devuelve los tokens encontrados*/
    }
       
    /**
     * Esta funcion verifica si la cadena ingresa es valida al pertenecer al 
     * abecedario o a la cadena de digitos. Ademas de eso verifica si la cadena
     * es o no un numero.
     * @param cadena 
     */
    private boolean isIdent(String cadena){
        boolean identValido = false;
        boolean num = false;
        int i,j,k; /*Variable para los contadores*/
        
        /*Se crea un arraylist con el abecedario*/
        List abecedario = new ArrayList<String>(){{
            add("a"); add("b"); add("c"); add("d"); add("e"); add("f"); 
            add("g"); add("h"); add("i"); add("j"); add("k"); add("l");
            add("m"); add("n"); add("o"); add("p"); add("q"); add("r"); 
            add("s"); add("t"); add("u"); add("v"); add("w"); add("x");
            add("y"); add("z"); add("A"); add("B"); add("C"); add("D"); 
            add("E"); add("F"); add("G"); add("H"); add("I"); add("J");
            add("K"); add("L"); add("M"); add("N"); add("O"); add("P"); 
            add("Q"); add("R"); add("S"); add("T"); add("U"); add("V");
            add("W"); add("X"); add("Y"); add("Z");     
        }};
        
        
        List digitos = new ArrayList<String>(){{
            add("0"); add("1"); add("2"); add("3"); add("4"); add("5");
            add("6"); add("7"); add("8"); add("9");
            
        }};
        
        for (j=0;j<cadena.length();j++) {
            System.out.println("PC");
            
            String caracter = cadena.substring(j, j+1);
            System.out.println("El caracter es: " + caracter);
            
            if (caracter.matches("\n"))
                continue;
            
            /*Verifica si el caracter forma parte del abecedario*/
            for (i = 0; i < abecedario.size(); i++) {
                if (caracter.equals(abecedario.get(i))) {
                    pertenece = true;
                    break;
                }
                else{
                    pertenece = false;
                }
            }
            
            if (pertenece == false) {
                /*Verifica si el caracter forma parte de los digitos*/
                for (k = 0; k < digitos.size(); k++) {
                    if (caracter.equals(digitos.get(k))) {
                        System.out.println("El digito con el que coincide es: " + digitos.get(k));
                        pertenece = true;                        
                        num = true;
                        break;
                    } else {
                        pertenece = false;
                    }
                }
                
                if (pertenece == false) {
                /*Si no pertenece al alfabeto aceptado por el lenguaje, se detiene 
                 el ciclo y se muestra una alerta*/
                System.out.println("La cadena: " + cadena + " contiene un caracter"
                        + " no valido: \'" + caracter + "\'");
                
                break; //Se sale del ciclo
                
                }
            }
            
            if (num == true && j==0){
                System.out.println("La cadena: " + cadena + " es un digito");
                break;
            }
            
            
        }
        
        if (pertenece == true)
            System.out.println("Moi es lo maximo");
        return pertenece;
    }
    
    /**
     * Este metodo lo que hace 
     * @param texto 
     */
    private void verCadenas(String texto){
        /*Se almacena la primera linea hasta encontrar un retorno de carro*/
        StringBuilder b = new StringBuilder();
        
        int cont = 0; /*Contador*/
       
        boolean salto = false;
        String c;        
        do{
            c = texto.substring(cont, cont + 1);
            if (!(c.equals("\n"))){
                System.out.println(c);
                b.append(c);
                cont++;
            }
            else {salto = true;}
        } while (cont<texto.length());
        
        if (salto = false){
            System.out.println("Error. No existe retorno de carro en la primera linea.");
        }
        
        System.out.println(b.toString());
        
        String k = b.toString();
        System.out.println(k);
        /*Se verifica que la primera linea del texto este bien escrita*/
        if (k.startsWith("Cocol = \"C")){
            System.out.println("Ok");
            
        }
        else {
            System.out.println("no Ok");        
        }
      
    }
    
    
    private void verPrimeraLinea(String texto) throws IdentException
    {
        NoToken = 0; /*Se inicializa la variable en 0*/
        boolean error = false; /**/
        
        /*Se evitan los errores causados por la lectura de tokens, regularmente
        que existan menos tokens de los necesarios para hacer la comprobacion*/
        try {
            
            while (NoToken <=1) {
                String t = st.nextToken();
               /* if (NoToken == 0) {
                    if (t.startsWith("Cocol")) {
                        System.out.println("Reconoció el primer token COCOL");
                        error = false;
                    } else {
                        error = true;
                        System.out.println("No reconocio el primer token");
                    }
                }
                
                if (NoToken == 1) {
                    if (t.startsWith("=")) {
                        System.out.println("Reconocio el segundo token =");
                        error = false;
                    } else {
                        error = true;
                    }
                }*/
                
                if (NoToken == 0) {
                    System.out.println("Token 0");
                    if (t.startsWith("COMPILER")) {
                        System.out.println("Reconocio el tercer token COMPILER");
                        error = false;
                    } else {
                        error = true;
                    }
                }
                
                if (NoToken == 1)
                {
                    System.out.println("Token 1: " + t);
                    boolean esIdent = isIdent(t);
                    
                    if (esIdent == true){
                        System.out.println("Se ha reconocido el ident");
                    }else{
                        System.out.println("No se ha reconocido el ident que "
                                + "precede al compiler");;
                    } 
                }

                /*Esto ocurre despues de que se ha reconocido la primera linea*/
                /*if(NoToken == 4)
                {
                    System.out.println(t);
                    System.out.println(isIdent(t));
                }*/
                NoToken++; /*Aumenta el numero de tokens leidos*/ 
                
                /*Esto se da si hay error en la primera linea*/
                if (error == true) {
                    System.out.println("Hay un error en la primera linea: " + t);                    
                }
            }
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error en la lectura de tokens!");
        }
    }
    
    
    private void analizarLinea(String linea){
        if (linea.equals("\n")){
            System.out.println("Linea");
        }
    }
    
    /**
     * Este metodo realiza la operacion Set en una linea 
     * @param linea 
     */
    private void setDcl(String linea){
        /*Se le quitan los espacios en blanco, las tabulaciones y los retornos
          de carro de a la linea ingresada*/
        int index1; //Guarda el indice donde se encuentra el signo =
        int index2; //Guarda la ubicacion del punto.
        
        String ident; //Almacena el isIdent de la declaracion
        String asignacion; //Almacena la asignacion
        
        linea = linea.replaceAll("\\s", "");
        
        System.out.println(linea);
        index1 = linea.indexOf("="); //Se asigna el indice de la ubicacion del =
        
        if (linea.endsWith(".")) {
            
            
            index2 = linea.indexOf("."); //Se asigna el indice de la ubicacion del .
        }
        
        ident = linea.substring(0, index1); 
        asignacion = linea.substring(index1,index2);
        
        System.out.println("Ident: " + ident);
        System.out.println("Asignacion: " + asignacion );
        
        
        /*Revisa si el isIdent esta bien. Si el isIdent esta bien hecho se procede 
          a hacer el Set*/
        if(isIdent(ident) == true)
        {
            set(asignacion);
        }
        
    }
    
    private void set(String asignacion)
    {
        /*Revisa si la asignacion contiene un signo +*/
        //FALTA HACER ESTA ACCION
        
        basicSet(asignacion);
    }
    
    private void basicSet(String basicSet)
    {
        boolean ident, string, chr;
        
        ident = isIdent(basicSet);
        chr = isChar(basicSet);
        
    }
    
    
    private boolean isChar(String linea)
    {
        
        boolean isChar = false; /*Almacena si la linea es cumple con ser char o no*/
        
        if (linea.startsWith("'") && linea.endsWith("'")){
            System.out.println("Se ha encontrado un char");
            isChar = true; //Se afirma que se ha encontrado un char
        }
        else{
            System.out.println("Lo que se ha encontrado no es un char");
        }
        
        return false;
    }
}
