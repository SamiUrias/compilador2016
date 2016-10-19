
package PendientesDeEliminar;

import Automata.Automata;
import Automata.ContadorNodo;
import Automata.Nodo;
import Automata.OpExtra;
import Automata.RegexConverter;
import Automata.SimuladorAFN;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Samuel Urias on 18/09/2016.
 * Esta clase esta basada en el main para que el codigo este más ordenado.
 *
 * Esta clase analiza una expresion regular y mediante un Automata Finito Deteminista (AFD) o un Automata Finito
 *  No determinsta (AFN).
 */
public class RegexAnalyzer {

    private static String p; /*Direccion donde se crea la imagen del automata*/

    /***
     * Si esta variable es verdadera, entonces se ejecutan todas las instrucciones que se utilian para debugear
     * los AFN
     */
    boolean debugAFN = false;

    /*Variables globales*/

    private boolean printAFN = true; /*Si esta variable es verdadera, se dibuja el AFN*/
    private boolean simularAFN = false; /*Si esta variable es verdadera, se hace la simulacion del AFN*/



    /*Almacena un arraylist con el alfabeto del automata*/
    private ArrayList<String> alfabeto;

    /*Almacena el nodo inicial del AFN*/
    private int nodoinicialafn = 0;

    /*Almacena el nodo inicial del AFN*/
    private int nodoinicial = 0;

    /*Almacena el nodo final del AFN*/
    private int nodofinalafn = 0;


    /*Permite realizar una simulacion del AFN*/
    private SimuladorAFN simulador;


    /*Esta variable almacena el tiempo al principio y el final de la ejecución del programa*/
    private long time1 = 0;
    private long time = 0;


    /*Almacena la cadena con postfix*/
    private String cadena_postfix = "";


    /*AFN*/
    Automata aut = null;

    /**
     * Constructor del RegexAnalizer
     * */
    public RegexAnalyzer(String cadena){
        /*Se almacena el tiempo actual en la variable teimpo 1*/
        this.time1 = System.currentTimeMillis();

        /*Se quitan los espacios en blanco a la cadena antes de aplicarle el postfix*/
        cadena = cadena.trim();
        cadena = cadena.replace(" ", "");

        /*Se le convierte la cadena de infix a postfix y se alamcena en la variable 'cadena_postfix'*/
        cadena_postfix = (RegexConverter.infixToPostfix(cadena));

        /*Se encuentra el alfabeto de la expresion regular ingresada*/
        alfabeto = OpExtra.alfabeto(cadena_postfix);

    }


    /**
     * Este metodo crea un Automata Finito No determinista (AFN)
     * */
    public void crearAFN(){

        /*Se crea el automata y se le asigna el alfabeto que se va a utilizar*/
        aut = new Automata(alfabeto);


        /*Aqui crean los nodos del automata*/
        int j=0; /*Contador*/
        while (j<cadena_postfix.length())
        {
            aut.crearAutomata(cadena_postfix.substring(j, j+1));
            j++;
        }

        /*AUTOMATA CREADO*/
        aut = aut.automatas.pop();


        ArrayList<Nodo> arnodo = aut.getEstados();

        for (int i=0; i<arnodo.size();i++)
        {

            if (arnodo.get(i).iseInicial() == true){
                nodoinicial = arnodo.get(i).getId();
                nodoinicialafn = arnodo.get(i).getId();
            }

            if (arnodo.get(i).iseFinal() == true){
                nodofinalafn = arnodo.get(i).getId();
            }
        }

         /*Se resta el tiempo actual del sistema con el tiempo de inicio y ese
           es tiempo total de la ejecucion*/
        time = System.currentTimeMillis()-time1;

    }

    public void imprimirEstadoDelAFN(){

        System.out.println("\nLa cantidad de estados es: " + ContadorNodo.getContador()+"\n");

        System.out.println("\nCantidad de transiciones: " + aut.transiciones.size()+"\n");

        System.out.println("\nEl tiempo total del programa es: " + time + " milisegundos\n");

        /*Crea un subset*/
        System.out.println("El nodo inicial a introducir en el subset es: " +nodoinicial);

        System.out.println("La cantidad de transiciones en el automata es: " + aut.getTransiciones().size());


        System.out.println(aut.getAlfabeto() + "ALFABETO");
        System.out.println(alfabeto);
        aut.setAlfabeto(alfabeto);
        System.out.println("El afabeto que posee el automata es: " + aut.getAlfabeto());
    }


    public boolean simularAFN(String cadena){

        /*odela si la cadena es aceptada o no*/
        boolean cadena_aceptada = false;

        simulador = new SimuladorAFN(aut);
        if(this.simularAFN) {
            cadena_aceptada = simulador.hacerSimulacion(cadena);
        }

        return cadena_aceptada;
    }

    /**
     * Este metodo almacena el texto con la informacion del AFN (Automata finito no determinista)
     * */
    public void generarTextoAFN(){
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

        System.out.println("TEXTO: " + texto);

         /*Se guarda el automata*/
         this.guardar(texto);

    }


    /**
     * Este metodo crea un archivo de texto con las instrucciones para el la libreria 'graphviz' para que posterioremte
     * se grafique el automata.
     */
    public void graficarGrafoAFN(){
        String texto = "";

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

         /*Guarda la imagen*/
        if (printAFN==true){
            guardarImagen(texto);
            llamarAlGraphViz();
        }
    }

    /**Este códifo fue tomado de:
     http://jconnexion.blogspot.com/2011/12/jfilechooser-para-abrir-yo-guardar.html
     el 20 de agosto del 2015

     Se utiliza para guardar el afn generado*/
    private void guardar(String text){
        System.out.println("Se ha abierto un cuadro de dialogo para guardar el texto!");
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
                    p = PATH;
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

        System.out.println("Se ha guardado el texto del AFN con exito");
    }


    /**
     * Se utiliza para guardar la imagen(.dot)
     * @param text
     */
    private void guardarImagen(String text){
        if (text.matches("[[ ]*[\n]*[\t]]*")) {//compara si en el JTextArea hay texto sino muestrtra un mensaje en pantalla
            JOptionPane.showMessageDialog(null,"No hay texto para guardar!", "Oops! Error", JOptionPane.ERROR_MESSAGE);
        }else{
            System.out.println("Se abrio el cuadro de dialogo");
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
                    p = PATH;
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



    /**
     * Este metodo llama al graphviz con un process builder
     */
    private void llamarAlGraphViz(){
        try {
            /*Direccion donde esta el ejecutable de graphviz*/
            String dotPath = "d:\\Program Files (x86)\\Graphviz2.38\\bin\\dot.exe";

            System.out.println(p);
            String fileInputPath = p;
            String fileOutputPath = p+".jpg";

            String tParam = "-Tjpg";
            String tOParam = "-o";

            String[] cmd = new String[5];
            cmd[0] = dotPath;
            cmd[1] = tParam;
            cmd[2] = fileInputPath;
            cmd[3] = tOParam;
            cmd[4] = fileOutputPath;

            Runtime rt = Runtime.getRuntime();

            rt.exec( cmd );

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }

    }


    /***     GETTERS AND SETTERS **/
    public String getCadena_postfix() {
        return cadena_postfix;
    }

    public void setCadena_postfix(String cadena_postfix) {
        this.cadena_postfix = cadena_postfix;
    }

    public ArrayList<String> getAlfabeto() {
        return alfabeto;
    }

    public void setAlfabeto(ArrayList<String> alfabeto) {
        this.alfabeto = alfabeto;
    }
}
