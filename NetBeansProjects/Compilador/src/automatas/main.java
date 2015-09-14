/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatas;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Moises Urias
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /*Almacena un arraylist con el alfabeto del automata*/
        ArrayList<String> alfabeto;
        int nodoinicialafn = 0;
        int nodofinalafn = 0;
        
        // TODO code application logic here
        
        /*Esta variable almacena el tiempo al principio de la ejecución del 
           programa*/
        long time1 = System.currentTimeMillis();
        
        
        System.out.println("Ingrese la expresion regular: ");
        
        
        Scanner scaner = new Scanner(System.in);
        String cadena = scaner.nextLine();
        
        //String cadena = "(b | b)*abb(     a  | b ) * ";
        
        
        
        
        cadena = cadena.trim();
        cadena = cadena.replace(" ", "");
        
        cadena = (RegexConverter.infixToPostfix(cadena));
        
        System.out.println("Cadena con posfix: \n" + cadena+"\n\n\n");
         
        
        
        
        /*Imprime el alfabeto del automata ingresado */
        System.out.println("El alfabeto del automata es:");
        alfabeto = OpExtra.alfabeto(cadena);
        System.out.println(alfabeto);
        
        /*Se crea el automata*/
        Automata aut = new Automata();
        
        /*Se le asigna al nuevo automata el alfabeto que este utilizara*/
        aut.setAlfabeto(alfabeto);
        
        /*Aqui crean los nodos del automata*/
        int j=0; /*Contador*/
        while (j<cadena.length())
        {
            aut.crearAutomata(cadena.substring(j, j+1));
            j++;
        }
        
                
        System.out.println("\nLa cantidad de estados es: " + 
                ContadorNodo.getContador()+"\n");
 
        
        
        /*AUTOMATA CREADO*/
        aut = aut.automatas.pop(); 

        
        
         
        System.out.println("\nCantidad de transiciones: " + aut.transiciones.size()+"\n");
        
        ArrayList<Nodo> arnodo = aut.getEstados();
        int nodoinicial =0;
        for (int i=0; i<arnodo.size();i++)
        {
            
            if (arnodo.get(i).iseInicial() == true){
                System.out.println("Estado inicial: " + arnodo.get(i).getId());
                nodoinicial = arnodo.get(i).getId();
                nodoinicialafn = arnodo.get(i).getId();
            }
            
            if (arnodo.get(i).iseFinal() == true){
                System.out.println("Estado final: " + arnodo.get(i).getId());
                nodofinalafn = arnodo.get(i).getId();
            }
        }
        
        
          /*Se resta el tiempo actual del sistema con el tiempo de inicio y ese
           es tiempo total de la ejecucion*/
       long time = System.currentTimeMillis()-time1;
        System.out.println("\nEl tiempo total del programa es: " + time + " milisegundos\n");
        
        
        
        
        /*Crea un subset*/
        System.out.println("El nodo inicial a introducir en el subset es: " +nodoinicial);
       
        System.out.println("La cantidad de transiciones en el automata es: " + aut.getTransiciones().size());
        
        
        
        System.out.println(aut.getAlfabeto() + "ALFABETO");
        System.out.println(alfabeto);
        aut.setAlfabeto(alfabeto);
        System.out.println("El afabeto que posee el automata es: " + aut.getAlfabeto());
        
        
        
        /*Se genera el archivo con al AFN*/
        String texto = ""; /*Texto que en el que se almacen al informacino a guardar*/
        texto = texto + "El nodo inicial del AFN es: " + String.valueOf(nodoinicialafn) + "\n";
        texto = texto +"El nodo final del AFN es: " + String.valueOf(nodofinalafn) + "\n";
        texto = texto + "El alfabeto del AFN es: " + alfabeto + "\n\n";
        texto = texto + "El tiempo total de la simulacion del afn es: "+(System.currentTimeMillis()-time1)+"\n\n";
        texto = texto + "Las transiciones del afn son:\n ";
         for (int i=0; i<aut.transiciones.size();i++)
        {   
            
            texto = texto + i +": "+ aut.transiciones.get(i)+"\n";
        }
         
        texto = texto + "\n\nSiendo '!' lo que equivale al simbolo epsilon";
         
        guardar(texto);
        
        
        
        /*__________________________________________________________________*/
        /*Se grafica el grafo*/
        texto = "";
        
        texto = texto + "digraph G\n"; 
        texto = texto +"{\n";
        texto = texto + "node [shape=circle];";
        texto = texto + "node [style=filled];";
        texto = texto + "node [fillcolor=\"#EEEEEE\"];";
        texto = texto + "edge [color=\"#31CEF0\"];";
        
        for (int i=0; i<aut.transiciones.size();i++)
        {   
            
           texto = texto + aut.transiciones.get(i).getNodoInicial() + " -> " +
                   aut.transiciones.get(i).getNodoFinal() + "[label=\""+
                   aut.getTransiciones().get(i).getSimbolo()+"\"];";
        }
         
        texto = texto + "rankdir=LR;\n}";

        guardarImagen(texto);
        
        
        AFD afd = new AFD(aut);
        
}
        
        
        
         
        
       
        
        
    
    
    
    /*Este códifo fue tomado de: 
      http://jconnexion.blogspot.com/2011/12/jfilechooser-para-abrir-yo-guardar.html
      el 20 de agosto del 2015*/
    private static void guardar(String text){
   
        
        if (text.matches("[[ ]*[\n]*[\t]]*")) {//compara si en el JTextArea hay texto sino muestrtra un mensaje en pantalla
            JOptionPane.showMessageDialog(null,"No hay texto para guardar!", "Oops! Error", JOptionPane.ERROR_MESSAGE);
        }else{
            
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("todos los archivos *.txt", "txt"));//filtro para ver solo archivos txt
            fileChooser.setSelectedFile(new File("AFN.txt"));
            int seleccion = fileChooser.showSaveDialog(null);
            try{
                if (seleccion == JFileChooser.APPROVE_OPTION){//comprueba si ha presionado el boton de aceptar
                    File JFC = fileChooser.getSelectedFile();
                    String PATH = JFC.getAbsolutePath();//obtenemos el path del archivo a guardar
                    PrintWriter printwriter = new PrintWriter(JFC);
                    printwriter.print(text);//escribe en el archivo todo lo que se encuentre en el JTextArea
                    printwriter.close();//cierra el archivo
                    
                    //comprobamos si a la hora de guardar obtuvo la extension y si no se la asignamos
                    if(!(PATH.endsWith(".txt"))){
                        File temp = new File(PATH+".txt");
                        JFC.renameTo(temp);//renombramos el archivo
                    }
                    
                    JOptionPane.showMessageDialog(null,"Guardado exitoso!", "Guardado exitoso!", JOptionPane.INFORMATION_MESSAGE);
                }
            }catch (Exception e){//por alguna excepcion salta un mensaje de error
                JOptionPane.showMessageDialog(null,"Error al guardar el archivo!", "Oops! Error", JOptionPane.ERROR_MESSAGE);
            }
        }           
    }
   
    
        private static void guardarImagen(String text){
   
        
        if (text.matches("[[ ]*[\n]*[\t]]*")) {//compara si en el JTextArea hay texto sino muestrtra un mensaje en pantalla
            JOptionPane.showMessageDialog(null,"No hay texto para guardar!", "Oops! Error", JOptionPane.ERROR_MESSAGE);
        }else{
            
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("todos los archivos *.dot", "dot"));//filtro para ver solo archivos txt
            fileChooser.setSelectedFile(new File("AFNimagen.dot"));
            int seleccion = fileChooser.showSaveDialog(null);
            try{
                if (seleccion == JFileChooser.APPROVE_OPTION){//comprueba si ha presionado el boton de aceptar
                    File JFC = fileChooser.getSelectedFile();
                    String PATH = JFC.getAbsolutePath();//obtenemos el path del archivo a guardar
                    PrintWriter printwriter = new PrintWriter(JFC);
                    printwriter.print(text);//escribe en el archivo todo lo que se encuentre en el JTextArea
                    printwriter.close();//cierra el archivo
                    
                    //comprobamos si a la hora de guardar obtuvo la extension y si no se la asignamos
                    if(!(PATH.endsWith(".dot"))){
                        File temp = new File(PATH+".dot");
                        JFC.renameTo(temp);//renombramos el archivo
                    }
                    
                    JOptionPane.showMessageDialog(null,"Guardado exitoso!", "Guardado exitoso!", JOptionPane.INFORMATION_MESSAGE);
                }
            }catch (Exception e){//por alguna excepcion salta un mensaje de error
                JOptionPane.showMessageDialog(null,"Error al guardar el archivo!", "Oops! Error", JOptionPane.ERROR_MESSAGE);
            }
        }           
    }
    
}
