/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

/*
 * Esta clase fue modificada por Moises Samuel Urias Moreno para que sirviera
 * como interfaz grafica (GUI) para el proyecto de la clase: Dise�o de lenguajes
 * de programaci�n.
*/


package GUI;

import Automata.RegexAnalyzer;
import Exception.IdentException;
import Parser.Parser;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.Element;
import lexer.*;

/**
 * Demonstration of the top-level {@code TransferHandler}
 * support on {@code JFrame}.
 *
 * 
 * @author Shannon Hickey
 * @author Moises Samuel Urias Moreno
 */
public class GUI extends JFrame {
    
    private static boolean DEMO = false;

    private JDesktopPane dp = new JDesktopPane();
    private DefaultListModel listModel = new DefaultListModel();
    private JList list = new JList(listModel);
    private static int left;
    private static int top;
    private JCheckBoxMenuItem lineNumberingItem;
    private JCheckBoxMenuItem nullItem;
    private JCheckBoxMenuItem thItem;
    private JMenuItem openItem;             /*Added by Moises*/
    private JMenuItem verificarItem;        /*Added by Moises*/
    private JMenuItem newItem;              /*Added by Moises*/
    private JMenuItem parsearItem;          /*Added by Moises*/
    private JMenuItem expresionesRegularesItem; /*Added by Moises*/
    
    /*This should be the consoele of the GUI*/
    private JTextArea text_console;
    
    private Doc documentoActual; /*Es el documento actual con el que se esta trabajando*/

    /*Se crea una instancia del Lexer2*/
    private Lexer2 lexer = new Lexer2(); 
    
    /*Se crea una instancia del parser*/
    private Parser parser = new Parser(); 
    
    /*Se crea una instanacia del analizador de expresiones regulares*/
    private RegexAnalyzer regexAnalyzer;
    
    
    /**
     * Esta clase se utiliza para crear los Documentos (formularios hijos) de la
     * interfaz grafica
     */
    private class Doc extends InternalFrameAdapter implements ActionListener {
        String name;
        JInternalFrame frame;
        TransferHandler th;
        JTextArea area;
        
        
        public Doc(File file) {
            this.name = file.getName();
            try {
                init(file.toURI().toURL());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        
        public Doc(String name) {
            this.name = name;
            init(getClass().getResource(name));

        }
        
       /**
        * Este constructor se utiliza cuando se quiere crear un documento en 
        *  blanco
        */ 
       public Doc(){
           this.name  = "Nuevo documento";
           blancDocument();
       }
        
        private void init(URL url) {
            
            frame = new JInternalFrame(name);
            //frame = new LineNumbering(name);
            frame.addInternalFrameListener(this);
            listModel.add(listModel.size(), this);

            area = new JTextArea();
            area.setMargin(new Insets(5, 5, 5, 5));

        
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                String in;
                while ((in = reader.readLine()) != null) {
                    area.append(in);
                    area.append("\n");
                }
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

            th = area.getTransferHandler();
            area.setFont(new Font("monospaced", Font.PLAIN, 12));
            area.setCaretPosition(0);
            area.setDragEnabled(true);
            area.setDropMode(DropMode.INSERT);
            frame.getContentPane().add(new JScrollPane(area));
            dp.add(frame);
            frame.show();
            if (DEMO) {
                frame.setSize(300, 200);
            } else {
                frame.setSize(400, 300);
            }
            frame.setResizable(true);
            frame.setClosable(true);
            frame.setIconifiable(true);
            frame.setMaximizable(true);
            frame.setLocation(left, top);
            incr();
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    select();
                }
            });
            nullItem.addActionListener(this);
            setNullTH();
            
            
            
        }
        
        /**
         * Crea un nuevo documento en blanco
         */
        private void blancDocument(){
            
            frame = new JInternalFrame(name);
            frame.addInternalFrameListener(this);
            listModel.add(listModel.size(), this);

            area = new JTextArea();
            area.setMargin(new Insets(5, 5, 5, 5));
            
            th = area.getTransferHandler();
            area.setFont(new Font("monospaced", Font.PLAIN, 12));
            area.setCaretPosition(0);
            area.setDragEnabled(true);
            area.setDropMode(DropMode.INSERT);
            frame.getContentPane().add(new JScrollPane(area));
            dp.add(frame);
            frame.show();
            if (DEMO) {
                frame.setSize(300, 200);
            } else {
                frame.setSize(400, 300);
            }
            frame.setResizable(true);
            frame.setClosable(true);
            frame.setIconifiable(true);
            frame.setMaximizable(true);
            frame.setLocation(left, top);
            incr();
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    select();
                }
            });
            nullItem.addActionListener(this);
            setNullTH();
        }

        public void internalFrameClosing(InternalFrameEvent event) {
            listModel.removeElement(this);
            nullItem.removeActionListener(this);
        }

        public void internalFrameOpened(InternalFrameEvent event) {
            int index = listModel.indexOf(this);
            list.getSelectionModel().setSelectionInterval(index, index);
        }

        public void internalFrameActivated(InternalFrameEvent event) {
            int index = listModel.indexOf(this);
            list.getSelectionModel().setSelectionInterval(index, index);
        }

        public String toString() {
            return name;
        }
        
        public void select() {
            try {
                frame.toFront();
                frame.setSelected(true);
            } catch (java.beans.PropertyVetoException e) {}
        }
        
        public void actionPerformed(ActionEvent ae) {
            setNullTH();
        }
        
        public void setNullTH() {
            if (nullItem.isSelected()) {
                area.setTransferHandler(null);
            } else {
                area.setTransferHandler(th);
            }
        }
    }

    private TransferHandler handler = new TransferHandler() {
        public boolean canImport(TransferHandler.TransferSupport support) {
            if (!support.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                return false;
            }

            if (lineNumberingItem.isSelected()) {
                boolean copySupported = (COPY & support.getSourceDropActions()) == COPY;

                if (!copySupported) {
                    return false;
                }

                support.setDropAction(COPY);
            }

            return true;
        }

        public boolean importData(TransferHandler.TransferSupport support) {
            if (!canImport(support)) {
                return false;
            }
            
            Transferable t = support.getTransferable();

            try {
                java.util.List<File> l =
                    (java.util.List<File>)t.getTransferData(DataFlavor.javaFileListFlavor);

                for (File f : l) {
                    new Doc(f);
                }
            } catch (UnsupportedFlavorException e) {
                return false;
            } catch (IOException e) {
                return false;
            }

            return true;
        }
    };

    private static void incr() {
        left += 30;
        top += 30;
        if (top == 150) {
            top = 0;
        }
    }
    
    /**
     * Constructor
     */
    public GUI() {
        super("...");
        setJMenuBar(createMenuBar());
        //getContentPane().add(createDummyToolBar(), BorderLayout.NORTH);

        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, list, dp);
        sp.setDividerLocation(120);
        getContentPane().add(sp);
        //new Doc("sample.txt");
        //new Doc("sample.txt");
        //new Doc("sample.txt");

        list.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }
                
                Doc val = (Doc)list.getSelectedValue();
                if (val != null) {
                    val.select();
                    System.out.println(val.name);
                    documentoActual = val; /*Se asigna el valor del docmento actual*/
                } 
             }
        });
        
        final TransferHandler th = list.getTransferHandler();
/*
        nullItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (nullItem.isSelected()) {
                    list.setTransferHandler(null);
                } else {
                    list.setTransferHandler(th);
                }
            }
        });
        thItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if (thItem.isSelected()) {
                    setTransferHandler(handler);
                } else {
                    setTransferHandler(null);
                }
            }
        });*/
        
        
        dp.setTransferHandler(handler);
    }

    private static void createAndShowGUI(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        GUI test = new GUI();
        test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if (DEMO) {
            test.setSize(493, 307);
        } else {
            test.setSize(800, 600);
        }
        test.setLocationRelativeTo(null);
        test.setVisible(true);
        test.list.requestFocus();
    }
    
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
	        UIManager.put("swing.boldMetal", Boolean.FALSE);
                createAndShowGUI(args);
            }
        });
    }
    
    private JToolBar createDummyToolBar() {
        JToolBar tb = new JToolBar();
        JButton b;
        b = new JButton("New");
        b.setRequestFocusEnabled(false);
        tb.add(b);
        b = new JButton("Open");
        b.setRequestFocusEnabled(false);
        tb.add(b);
        b = new JButton("Save");
        b.setRequestFocusEnabled(false);
        tb.add(b);
        b = new JButton("Print");
        b.setRequestFocusEnabled(false);
        tb.add(b);
        b = new JButton("Preview");
        b.setRequestFocusEnabled(false);
        tb.add(b);
        tb.setFloatable(false);
        return tb;
    }
    
    
    /**
     * Se crea la barra de menu
     * @return 
     */
    private JMenuBar createMenuBar() {
        JMenuBar menubar = new JMenuBar();
          
        
        JMenu archivo = new JMenu("Archivo"); /*Crea un nuevo elemento de menu*/
        menubar.add(archivo); /*Se agrega ese elemento de menu a la barra de menu*/
        
        JMenu ejecucion = new JMenu("Ejecucion");
        menubar.add(ejecucion);
        
        
        
        
        openItem = new JMenuItem("Abrir"); /*Se crea una instancia de elemento de menu*/
        archivo.add(openItem); /*Se agrega el elemento open item al menu archivo*/
        
        newItem = new JMenuItem("Nuevo");
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        archivo.add(newItem);
        
        
        verificarItem = new JMenuItem("Analisis lexico");
        verificarItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, ActionEvent.CTRL_MASK));
        ejecucion.add(verificarItem);
        
        parsearItem = new JMenuItem("Parsear");
        ejecucion.add(parsearItem);
        
        
        
        JMenu demo = new JMenu("Demo");
        demo.setMnemonic(KeyEvent.VK_D);
        menubar.add(demo);
        
        thItem = new JCheckBoxMenuItem("Use Top-Level TransferHandler");
        thItem.setMnemonic(KeyEvent.VK_T);
        demo.add(thItem);

        nullItem = new JCheckBoxMenuItem("Remove TransferHandler from List and Text");
        nullItem.setMnemonic(KeyEvent.VK_R);
        demo.add(nullItem);

        lineNumberingItem = new JCheckBoxMenuItem("LineNumbering");
        lineNumberingItem.setMnemonic(KeyEvent.VK_C);
        demo.add(lineNumberingItem);
        
        
                    /*Se agregan los action listeners*/
        
        /*Se agrega el action listener de verificarItem
           este action listener llama al metodo verificarItemActionPerformed*/
        verificarItem.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                /*Se llama al metodo verficarItemActionPerformed*/
                verificarItemActionPerformed(e);
            }
        });
        
        
        parsearItem.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                parsearItemActionPerformed(e);
            }
        });
       
        
        /*Se agrega el action listener de openItem
            este action listenner llama al metodo openItemActionPerformed*/
        openItem.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                openItemActionPerformed(e);
            }
        });
        
        newItem.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                newItemActionPerformed(e);
            }
        });
        
        
        return menubar;
    }
    
    private JMenu createDummyMenu(String str) {
        JMenu menu = new JMenu(str);
        JMenuItem item = new JMenuItem("[Empty]");
        item.setEnabled(false);
        menu.add(item);
        return menu;
    }
    
    
    /**
     * Este metodo es el action listener correspondiente a la accion del 
     *  menuItem verificar, el cual llama al metodo 
     *  verificarItemActionPerformed
     * @param evt 
     */
    private void verificarItemActionPerformed(ActionEvent evt){
        String documento = null; boolean existeDocumento = false;
        //System.out.println("Moi es lo maximo =D\nAqui va la opcion de verificar");
        //System.out.println(documentoActual.area.getText());
        JOptionPane.showMessageDialog(this, "Verificar el texto ingresado");
        
        
        /*Solo si hay algun documento disponible para analizar se analiza*/
        
        try {
            documento = documentoActual.area.getText();
            existeDocumento = true;
        }
        catch (NullPointerException e)
        {
            System.out.println("No hay documento que se pued analizar");
            JOptionPane.showMessageDialog(this, "No hay documento para analizar","Error",JOptionPane.ERROR_MESSAGE);
            existeDocumento = false;
        }
        
        
        if (existeDocumento == true) {
            this.lexer.analizar(documento);
        }   
        
    }
    
    
    private void parsearItemActionPerformed(ActionEvent evt){
         String documento = null; boolean existeDocumento = false;
        //System.out.println("Moi es lo maximo =D\nAqui va la opcion de verificar");
        //System.out.println(documentoActual.area.getText());
        JOptionPane.showMessageDialog(this, "Verificar el texto ingresado");
        
        
        /*Solo si hay algun documento disponible para analizar se analiza*/
        
        try {
            documento = documentoActual.area.getText();
            existeDocumento = true;
        }
        catch (NullPointerException ex)
        {
            System.out.println("No hay documento que se pued analizar");
            JOptionPane.showMessageDialog(this, "No hay documento para analizar","Error",JOptionPane.ERROR_MESSAGE);
            existeDocumento = false;
        }
        
        
        if (existeDocumento == true) {
            this.parser = new Parser(documento);
        }
    }
    
    
    
    private void openItemActionPerformed(ActionEvent evt) {
        abrirArchivo();
    }
    
    
   
    
    /*Este metodo abre un archivo de texto*/
    private void abrirArchivo() {
        
        String texto = "";
        /*Se crea un file chooser*/
        final JFileChooser fc = new JFileChooser();
        
        int returnVal = fc.showOpenDialog(this);
        
        /*Si el usuario acepta abrir el archivo*/
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
            File file = fc.getSelectedFile();
            new Doc(file);
        
        }
        else{
            System.out.println("No se ha seleccionado ningun archivo para abri");
        }
    
    
    }
    
    private void newItemActionPerformed(ActionEvent evt){
        new Doc(); /*Se crea un nuevo documento en blanco*/
        
    }
}
