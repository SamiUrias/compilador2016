package Compilador;


import Automata.OpExtra;
import com.sun.xml.internal.bind.v2.model.annotation.RuntimeAnnotationReader;
import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;
import lexer.Lexer2;
import GUI.GUI;
import Parser.Parser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

/**
 * Created by Samuel on 24/11/2016.
 */
public class Controller {

    /*Declaración de atributos del controlador*/
    GUI gui;                                                //Interfaz grafica
    Lexer2 lexer;                                           //Lexer
    Parser parser;                                          //Parser
    String documento;                                       //El documento que se va a analizar

    ActionListener actionListenerLexer;                     //Action listener para la interfaz grafica
    boolean existeDocumento = false;                        //Almacena la posibilidad de que exista el documento


    //Este Runnable es el thread que se utiliza para hacer el parseo del documento.
    final Runnable parsingThread = new Runnable() {
        @Override
        public void run() {
            parsingProcess();
        }
    };



    /**
     * Constructor del controlador
     * @param gui
     * @param lexer
     */
    public Controller(GUI gui, Lexer2 lexer){
        this.gui = gui;
        this.lexer = lexer;
    }

    /**
     * Metodo que se encarga de ver activar el contolador.
     */
    public void control(){
        actionListenerLexer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                linkAnalizarDocumento();
            }
        };

        gui.getVerificarItem().addActionListener(actionListenerLexer);
        System.out.println("Action listener setted");
    }

    /**
     * Al momento que se detecta una acción a travez del action listener, este metodo es invocado.
     */
    public void linkAnalizarDocumento(){
        Thread parserThread = new Thread(){
            public void run(){
                try {
                    SwingUtilities.invokeLater(parsingThread);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        parserThread.start();
    }

    /**
     * Este metodo dirige el proceso general de parseo.
     * Este metoodo se creo para ahorrar lineas en el metodo 'linkAnalizarDocumento'.
     */
    private void parsingProcess(){
        revisarSiElDocumentoExiste();
        if (existeDocumento == true) {
            lexer.analizar(documento);
            System.out.println("Se ha terminado el proceso del lexer");
            parser = new Parser(documento);
            parser.analizar(documento);
        }
        else{
            System.out.println("No existe el documento");
        }
    }

    /**
     * Este metodo es el encaragdo de revisar si existe o no un documento que analizar.
     */
    private void revisarSiElDocumentoExiste(){
        /*Solo si hay algun documento disponible para analizar se analiza*/
        try {
            documento = gui.getDocumento();
            existeDocumento = true;
        }
        catch (NullPointerException e)
        {
            gui.showMsgboxError();
            existeDocumento = false;
        }
    }
}
