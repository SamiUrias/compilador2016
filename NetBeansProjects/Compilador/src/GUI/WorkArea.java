/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Component;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Samuel
 */
public class WorkArea extends JTextComponent{
    private static JTextPane jta;
    private static JTextArea lines;
    
    public WorkArea()
    {
        super();
        
        /*En un nuevo hilo se crea la interfaz*/
        javax.swing.SwingUtilities.invokeLater(new Runnable(){
        
        public void run() {
            createGUI();
        }});

    }

    private static void createGUI()
    {
        
        JScrollPane jsp = new JScrollPane();
        jta = new JTextPane();
        lines = new JTextArea("1");
        lines.setBackground(Color.lightGray);
        lines.setEditable(false);
        
       
        
        jta.getDocument().addDocumentListener(new DocumentListener() {
            public String getText() {
                int caretPosition = jta.getDocument().getLength();
                Element root = jta.getDocument().getDefaultRootElement();
                String text = "1"+System.getProperty("line.separator");
                
                for(int i =2;i<root.getElementIndex(caretPosition) +2;i++) {
                    text += i + System.getProperty("line.separator");
                }
                
                return text;
            };
            
            @Override
            public void insertUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        
        /*Propiedades del ScrollBar*/
        jsp.getViewport().add(jta);
        jsp.setRowHeaderView(lines);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        /*
        JFrame jf = new JFrame();
        jf.add(jsp);
        jf.pack();
        jf.setSize(500,500);
        jf.setTitle("Documento en blanco");
        jf.setVisible(true);*/
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
            }
        }
        );
    }

    
    
}
