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

    GUI gui;
    Lexer2 lexer;
    Parser parser;
    String documento;
    ActionListener actionListenerLexer;
    boolean existeDocumento = false;

    public Controller(GUI gui, Lexer2 lexer){
        this.gui = gui;
        this.lexer = lexer;
        this.parser = new Parser();
    }

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

    public void linkAnalizarDocumento(){
        final Runnable xxxx = new Runnable() {
            @Override
            public void run() {
                revisarSiElDocumentoExiste();
                if (existeDocumento == true) {
                    lexer.analizar(documento);

                    parser.setProductionsLine(lexer.getProductionsLine());
                    parser.analizar(documento);
                    System.out.println("Se ha terminado el proceso del lexer");
                }
                else{
                    System.out.println("No existe el documento");
                }
            }
        };

        Thread parserThread = new Thread(){
            public void run(){
                try {
                    SwingUtilities.invokeLater(xxxx);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

        parserThread.start();

    }

    private void revisarSiElDocumentoExiste(){
        System.out.println("Analizar documento - Controller");
        OpExtra.leerPantalla();

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
