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
    int NoToken = 0; /*Cuenta el numero de token*/
    
    /*Se utiliza en el metodo ident para saber si un caracter pertnece o no al ident*/
    boolean pertenece = false; 
    boolean noimaginacion;
    
    /*Esta variable se hace true, si se encuentra*/
    boolean errorGeneral = false;
    
    StringTokenizer st; /*Se declara un tokeniezer para el analisis*/
    private Hashtable<String,Integer> tabla = new Hashtable<String,Integer>();
    
   
    
    
    /**
     * Este metodo analiza el texto ingresado
     * @param texto
     * @throws IdentException
     * @throws IOException 
     */
    public void analizar(String texto) throws IdentException, IOException{
        boolean charactersFound = false;
        int charactersLine = 0; 
        int tokensLine = 0;
        int keywordsLine = 0;
        int index;
        
        
        /*Se crea una nueva instancia de a clase analizadorLexico*/
        AnalizadorLexico al = new AnalizadorLexico();
        
        /*Arreglo de lineas*/
        String[] lineas;
        
        /*Se le asigna el texto ingresado al tokeniezer*/
        st = new StringTokenizer(texto, " ");

        /*Se analiza la primera linea del texto ingresado*/
        verPrimeraLinea(texto);
        System.out.println("-------------------------------------------------");

        /*Devuelve todas las lineas del texto y las almacena en un array*/
        lineas = al.devolverLineas(texto); 
        
        
        for(int i=0;i<lineas.length;i++){
            System.out.println("Linea "+i+ ": "+lineas[i]);
            
            /*Se reconoce que se hayan encontrado caracteres*/
            if (lineas[i].matches("CHARACTERS")){
                System.out.println("=D Se han entrado declaraciones tipo characters: " + i);
                charactersLine = i; 
                
            }
            
            /*Se reconoce que se hayan econtrado keywords*/
            if (lineas[i].matches("KEYWORDS"))
            {
                System.out.println("Se han encontrado delclaraciones tipo keywords:" + i);
                keywordsLine = i;
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
                if ((i>charactersLine) && (keywordsLine == 0)){
                    System.out.println("Declaracion de tipo characters");
                    System.out.println("SET");
                    setDcl(lineas[i]);
                    
                }
                /*Si las delcaraciones ocurren despues de la delcaracion de keywords*/
                else if ((i>keywordsLine) && (keywordsLine !=0)) {
                    System.out.println("Declaracion de tipo keywords");
                    keywordDecl(lineas[1]);
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
            
            
            String caracter = cadena.substring(j, j+1);
            //System.out.println("El caracter es: " + caracter);
            
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
                
            }
            
            if (num == true && j==0){
                System.out.println("La cadena: " + cadena + " es un digito");
                pertenece = true;
                break; 
            }
            
             if (pertenece == false) {
                /*Si no pertenece al alfabeto aceptado por el lenguaje, se detiene 
                 el ciclo y se muestra una alerta*/
                System.out.println("La cadena: " + cadena + " contiene un caracter"
                        + " no valido: \'" + caracter + "\'");

                break; //Se sale del ciclo
                }   
        }
        
        //System.out.println("-------->" + pertenece);
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
    
    
    
    /**
     * Este metodo revisa si la primera linea esta bien escrita
     * @param texto
     * @throws IdentException 
     */
    private void verPrimeraLinea(String texto) throws IdentException
    {
        NoToken = 0; /*Se inicializa la variable en 0*/
        boolean error = false; /**/
        
        /*Se evitan los errores causados por la lectura de tokens, regularmente
        que existan menos tokens de los necesarios para hacer la comprobacion*/
        try {
            
            while (NoToken <=1) {
                /*t es el ident de la primera linea que se tiene que revisar*/
                String t = st.nextToken();
   
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
                        System.out.println("Se ha reconocido el ident."
                                + "\nLa primera linea esta bien escrita");
                        
                        
                        /*Despues de revisar que la primera linea esta bien 
                          escrita, se procede a revisar la ultima linea para 
                          saber si esta esta bien escrita*/
                        verUltimaLinea(texto,t);
                        
                        
                    }else{
                        System.out.println("No se ha reconocido el ident que "
                                + "precede al compiler");;
                    } 
                }

             
                NoToken++; /*Aumenta el numero de tokens leidos*/ 
                
                /*Esto se da si hay error en la primera linea*/
                if (error == true) {
                    System.out.println("Hay un error en la primera linea: " + t);                    
                }
                
            }
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error en la lectura de tokens!");
        }
        
        
        /*Despues de revisar que la primera linea esta bien escrita, se procede
        a revisar la ultima linea para saber si esta esta bien escrita*/
    }
    
    
    private void verUltimaLinea(String texto, String ident) {
        AnalizadorLexico al = new AnalizadorLexico();
        String ultimaLinea = al.devolverUltimaLinea(texto);
        StringTokenizer st = new StringTokenizer(ultimaLinea, " ");
        String token;
        boolean error = true;
        
        
        /*Error 1
          El error 1 consiste en que el primer token de la ultima linea no sea
           la palabra END*/
        token = st.nextToken();
        if (token.startsWith("END")) {
            System.out.println("El primer elemento de la ultima lines esta bien escrito");
            error = false;
        }
        else {
            System.out.println("La ultima linea debe empezar con 'END'");
        }
        
        
        
        /*Error 2
          El error 2 consiste en que ell segundo token de la ultima linea debe 
          de ser igual al ident ingresado en la primera linea*/
        token = st.nextToken();
        
        /*Para poder analizar el token, se le quita el utimo caracter, el cual 
          deberia ser un punto. Al removerle el ultimo caracter, si el mismo es 
          un punto, deberia quedar como token el identificador, el cual asi 
          mismo deberia de ser el mismo identificador que el identificador de la
          primera linea*/
        token = token.substring(0,token.length()-1);
        System.out.println("El identificador esperado es: " + ident);
        System.out.println("El identificador encontrado es: " + token);
        if(token.equals(ident)){
            System.out.println("El segundo elemento de la ultima linea concuerda"
                    + " con el identificador de la primera linea");
            error = false;
        }
        else {
            System.out.println("El identificador de la ultima linea no concuerda"
                    + " con el identificador de la primera linea");
            System.out.println(token);
        }
        
        
        /*Error 3
          El error 3 consiste en que el ultimo token de la ultima linea debe ser
          un punto*/
        if (ultimaLinea.endsWith(".")) {
            System.out.println("La ultima linea termina con punto");
            error = false;
        }
        else
        {
            System.out.println("La ultima linea debe terminar con un punto");
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
        int index2 = 0; //Guarda la ubicacion del punto.
        
        String ident; //Almacena el isIdent de la declaracion
        String asignacion; //Almacena la asignacion
        
        linea = linea.replaceAll("\\s", "");
        
        System.out.println(linea);
        index1 = linea.indexOf("="); //Se asigna el indice de la ubicacion del =
        
        if (linea.endsWith(".")) {
            index2 = linea.length();
            //index2 = linea.indexOf("."); //Se asigna el indice de la ubicacion del .
        }
        
        ident = linea.substring(0, index1); 
        asignacion = linea.substring(index1+1,index2-1);
        
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
    
    
    /**
     * Revisa si lo que se tiene es un basicSet, entonces revisa si lo que se 
     *  tienes es un ident, un string, o un char. Despues de determinar con que
     *  se esta trabajando, se trabaja con base en esto.
     * @param basicSet 
     */
    private void basicSet(String basicSet)
    {
        boolean ident, string, chr;
        
        ident = isIdent(basicSet);
        
        /*Se trabaja el ident*/
        if (ident == false) {
            /*Si no es un ident*/
            System.out.println("No es un ident");
        }
        else {
            /*Si lo que se tiene es un ident*/
            System.out.println("Es un ident");
        }
        
        /*Se trabaja el String*/
        string = isString(basicSet);
        if (string = true)
        {
            System.out.println("Es un String");
        }
        else {
            System.out.println("No es un String");
        }
        
        /*Se trabaja el char*/
        chr = isChar(basicSet);
        if (chr == true)
        {
            System.out.println("Es un Char");
            
            /*Se procede a trabajar el char*/
            this.Char(basicSet); /*Utiliza */
        }
        else
        {
            System.out.println("No es un Char");
        }
    }
    
    
    /**
     * Este metodo verifica si lo que se ha ingresado es un Char, utilizando el
     * metodo de reconocer si lo que se ha ingresado esta entre comillas simples
     * @param linea
     * @return 
     */
    private boolean isChar(String linea)
    {       
        boolean isChar = false; /*Almacena si la linea cumple con ser char o no*/
        
        if (linea.startsWith("'") && linea.endsWith("'")){
            System.out.println("Se ha encontrado un char");
            isChar = true; //Se afirma que se ha encontrado un char
        }
        else{
            System.out.println("No se ha podido encontrar un char");
            isChar = false;
        }
        
        if (linea.startsWith("CHR"))
        {
            isChar = true;
        }
        
        return isChar;
    }
    
    
    private boolean isString(String linea){
        boolean isString = false; /*Almacena si lalinea cumple con ser String o no*/
        
        int index = linea.indexOf("\"");
        if (index != -1){
            isString = true;
            System.out.println("Se ha encontrado un String");
        }
        else {
            isString = false;
            System.out.println("No no se ha encontrado un String");
        }
        
        /*Devuelve si es un string o no*/
        return isString;
    }
    

    /**
     * Si se encuentra un char, este metodo se encarga de trabajar con ello
     */
    private void Char(String linea){
        
        /*Arreglo que contendra*/
        String[] partes = null;
        
        int know = 0; /*Se utiliza para saber si hay o no varias cadenas de
         simbolos*/
        
        know = linea.indexOf("+");
        System.out.println(know);
        
        /*Se evalua si la linea tiene mas de un caracter, y dependiendo de eso
          se procede a trabajarla*/
        if (linea.length() > 3) {
            /*Primero que nada se averigua si se tienen varias cadenas de char.
                Para esto se averigua primero si la cadena ingresada tiene algun signo
                '+' o algun signo '-'*/
            if (know != -1) {
                System.out.println("La linea esta separada por partes");
                /*Se separa la linea por las parte separadas por un +*/
                System.out.println(linea);
                partes = linea.split("\\+");

                /*Se quitan los esacios en blanco de las partes*/
                for (int i = 0; i < partes.length; i++) {
                    partes[i] = partes[i].replace(" ", "");
                }
                
            } else {
                System.out.println("La linea no esta searada por partes");
                partes = new String[1];
                partes[0] = linea;
            }
        }
        else{
            /*Si la el caracter tiene solamente un caracter*/
            System.out.println("La linea solo contiene un caracter");
            String caracter =  linea.substring(1, 2);
            System.out.println("El caracter es: " + caracter);
        }

    }
    
    
    /**
     * Este metodo maneja un keyword en caso de que se econtrase uno
     * @param linea 
     */
    private void keywordDecl (String linea) {
        
    }
    
    
    
}
