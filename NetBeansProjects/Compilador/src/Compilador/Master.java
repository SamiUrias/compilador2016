package Compilador;

import GUI.GUI;
import jdk.nashorn.internal.parser.Lexer;
import lexer.Lexer2;

import javax.swing.*;

/**
 * Esta clase pretende contener tanto al Lexer, como al Parser y la Interfaz grafica.
 * Created by Samuel on 24/11/2016.
 */
public class Master {
    /*public static GUI gui;
    public static Lexer2 lexer;
    public static Controller controller;*/

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                UIManager.put("swing.boldMetal", Boolean.FALSE);

                GUI gui = new GUI();
                Lexer2 lexer = new Lexer2();
                Controller controller = new Controller(gui, lexer);
                controller.control();

                //Confirma que se estan utilizando los hilos de una forma correcta
                System.out.println(javax.swing.SwingUtilities.isEventDispatchThread());
            }
        });
    }
}
