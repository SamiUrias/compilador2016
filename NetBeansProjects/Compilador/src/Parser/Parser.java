/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import lexer.AnalizadorLexico;
import lexer.Lexer;

/**
 * Esta clase se encarga de parsear el documento ingresado
 * @author Samuel
 */ 
public class Parser {
    
    private ArrayList<Terminal> terminales = new ArrayList();
    private ArrayList<NoTerminal> noTerminales = new ArrayList();
    private ArrayList<String> producciones = new ArrayList(); 
    
    /*ArrayList provisionales*/
    private ArrayList<String> snoterminales = new ArrayList();
    private ArrayList<String> sterminales = new ArrayList();
    
    private String[] lineas;
    
    HashSet<Produccion> produccionesCreadas = new HashSet();
    
    
    /*Se crea una instancia del lexer*/
    private Lexer lexer = new Lexer();
    
    /*Constructor del parser*/
    public Parser()
    {
        
    }
    
    
    /*Se parsea el documento utilizando el segudo metodo*/
    public Parser(String documento)
    {
        /*Se crea un instancia del analizador léxico*/
        AnalizadorLexico al = new AnalizadorLexico();
        StringTokenizer st;
        
        /*Se separa el documento por lineas*/
        lineas = al.devolverLineas(documento);
        
        
        for (int i=0; i<lineas.length;i++){
            /*Se quitan los espacios en blanco del texto*/
            lineas[i] = lineas[i].trim();
            //lineas[i] = lineas[i].replaceAll(" ", "");
            
            /*Se imprimen las lineas en pantalla*/
            System.out.println(lineas[i]);
        }
        
        
        /*Revisa la gramatica de las producciones ingresadas en el documento*/
        this.parserSpecification();
        
        /*Se reconocen las producciones ingresadas en la gramatica*/
        separarProducciones();
        
        /*Se separan los terminales de los no terminales*/
        separarTerminales3();
                
    }

    /**
     * Este método se llama desde la interfaz gráfica para parsear un documento
     * @param documento 
     */
    public void parsear(String documento) {
        /*Se crea un instancia del analizador léxico*/
        AnalizadorLexico al = new AnalizadorLexico();
        StringTokenizer st;
        
        /*Se separa el documento por lineas*/
        lineas = al.devolverLineas(documento);
        
        for (int i=0; i<lineas.length;i++){
            /*Se quitan los espacios en blanco del texto*/
            lineas[i] = lineas[i].trim();
            lineas[i] = lineas[i].replaceAll(" ", "");
            
            /*Se imprimen las lineas en pantalla*/
            System.out.println(lineas[i]);
        }
        
        /*Se separan los terminales*/
        separarTerminales(lineas);
        
    }
    
 
    
    
    /*!!!!!!!! Se debe trabajar con la verificacion de errores !!!!!!!!!!!!!!!!*/
    private void separarTerminales(String[] lineas) {
        /*El no termial mas largo*/
        int noTerminalMasLargo=0;
        
        /*Se cuenta la cantidad de lineas a analizar*/
        System.out.println("Las cantidad de lineas a analizar son: " + lineas.length);
        
        /*Se recorren todas las lineas que se han encontrado para saber si son
           producciones validas. Si se encuentran producciones validas, se 
           separan en terminales y no terminales*/
        
        
        /*Primero se separan los no terminales.
          Esto se hace tomando todo lo que se encuentra del lado izquierdo de la
          igualdad*/
        for (int i = 0; i < lineas.length; i++) {
            
            /*Linea actual que se esta trabajando*/
            String lineaActual = lineas[i];
            
            /*Posicion del signo igual*/
            int igual = lineaActual.indexOf("=");

            /*Si se ha encontrado el signo igual es que es es una produccion
               valida*/
            if (igual != -1) {
                
                /*Se asigna como noTerminal el lado izquiero de la igualdad*/
                String noTerminal = lineaActual.substring(0, igual);
                System.out.println("No terminal: " + noTerminal);
                
                /*Se crea un noTerminal con el lado izquierdo de la igualdad*/
                NoTerminal noterminal = new NoTerminal(noTerminal);
                
                /*Si el no terminal encontrado no se encuentra en la lista de 
                   no terminales, entonces se agrega a esta lista*/
                if (!noTerminales.contains(noTerminal)) {
                    this.noTerminales.add(new NoTerminal(noTerminal));
                    
                    /*Se encuentra el no terminal mas largo*/
                    int nt = noTerminal.length();
                    if (nt>noTerminalMasLargo)
                    {
                        noTerminalMasLargo=nt;
                    }
                } 
            }
        }
        
        
        /*Despues que se han separado los no terminales, se procede a escoger
          los terminales*/
        
        /*Se crea un arreglo con la primer letra de los no terminales*/
        ArrayList<String> pnt = new ArrayList(); 
        
        /*Se recorren todos los no terminales y se toma la primer letra*/
        for(int j=0; j<noTerminales.size();j++)
        {
            /*Se toma el primer caracter de no terminal y se almacena en el arreglo*/
            String nt = noTerminales.get(j).getNoTerminal();
            nt=nt.substring(0, 1); 
            
            if (!pnt.contains(nt))
            {
                pnt.add(nt);
            }
            
            //System.out.println("El no terminal mas grande tiene el largo de: " + noTerminalMasLargo);
        }
        
        //DEBUG
        System.out.println(""); /*Inserta un salto de linea*/
        
        
        
        /*-------------- SE IDENTIFICAN LOS TERMINALES------------------------*/
        
        /*Imprime las primeras letras de los no terminales para empezar a analizarlos*/
        for (int k=0; k<pnt.size();k++)
        {
            System.out.println("Primera letra: " + pnt.get(k));
        }
        
        /*Se analizan las lineas, una por una*/
        for (int l=0; l<lineas.length;l++){
            
            //DEBUG
            System.out.println("Adentro del primer for (for de lineas)");
            
            
            String lineaActual = lineas[l];
            
            /*Se reconoce si es una produccion valida*/
            if (lineaActual.indexOf("=")!=-1) {
                
                //DEBUG
                //Si es una producción valida
                System.out.println("Adentro del priemr if");
                System.out.println(lineaActual);
                
                /*Si es una produccion valida, entonces se toma como linea 
                  actual el lado derecho de la igualdad hasta el punto*/
                lineaActual = lineaActual.substring(lineaActual.indexOf("=")+1,lineaActual.length()-1);
                System.out.println(lineaActual);
                
                /*Una vez se tine el lado derecho de la produccion, entonces se
                   procede a evaluarla caracter por caracter*/
                for (int m = 0; m < lineaActual.length(); m++) {
                    System.out.println("Adentro del segundo for (for de caracteres) ");
                    String charActual = lineaActual.substring(m, m + 1); /*Caracter actual que se esta analizando*/

                    System.out.println(charActual);
                    
                    
                    /*Si se el caracter actual no empieza con la primera letra
                      de los no terminales, significa que es un terminal. Como 
                      lo que se ha encontrado es un terminal, este se añade a la
                      lista de terminales*/
                    if (pnt.contains(charActual))
                    {
                        this.terminales.add(new Terminal(charActual));
                    }
                    else
                    {
                        /*Si empieza con alguna letra de los no terminales, se
                          procede a analizar ambas cadenas*/
                        System.out.println("Adentro del else");
                        System.out.println("El no terminal mas largo es: " + noTerminalMasLargo);
                        
                        /*Se revisa el siguiente caracter al caracter actual */
                        for(int nt = 0;nt<noTerminalMasLargo;nt++){ 
                            String sigChar =  lineaActual.substring(m+1,m+2);
                            
                            System.out.println("El caracter actual es: " + charActual);
                            System.out.println("El siguiente caracter es: " + sigChar);
                            
                            /*Se revisan todos los no terminales*/
                            for (int recorridoNt = 0; recorridoNt<noTerminales.size();recorridoNt++){
                                
                            }
                        }
                    }
                }
            }
        }
        
        /*Se sale del programa*/
        System.exit(0);
    }
    
    
    /**
     * Este metodo separa los terminales de los no terminales. 
     * Este metodo utiliza el segundo aloritmo utilizado para separar terminlaes
     *  de no terminales.
     * 
     * Este algoritmo aprovecha que las produccinoes UTILIZAN ESPACIOS para 
     *  identificarlos.
     */
    private void separarProducciones ()
    {
        System.out.println("ST2");
        
        /*La utilizacion de HashSet evita que hayan elementos repetidos*/
        
        
        ArrayList<Produccion> pNuevas;
        
        /*Se revisan todas las producciones encontradas*/
        System.out.println("Las producciones encontradas son:");
        System.out.println(producciones.size());
        for (int i =0; i<producciones.size();i++)
        {
            System.out.println("Dentro del for");
            String produccionActual = producciones.get(i).toString();
            System.out.println(produccionActual);
            
            /*Se reciben las producciones creadas*/
            pNuevas = crearProducciones(produccionActual);
            
            /*Se agregan las nuevas producciones al arreglo de producciones*/
            for (Produccion prod: pNuevas)
            {
                produccionesCreadas.add(prod);
            }

        }
    }
    
    
    
    private void separarTerminales3()
    {
        /*Se revisan las producciones*/
        for(Produccion prod:produccionesCreadas)
        {
            /*Si el no temrminal no se encuentra ya en la lista de los no
               terminales, se agrega este a la lista*/
            if (!snoterminales.contains(prod.getCabeza()))
            {
                snoterminales.add(((String)prod.getCabeza()).trim());
            }
        }
         
        System.out.println("No terminales:");
        for(String nt:snoterminales)
        {
            System.out.println(nt.toString());
        }
        
       
        /*Se hace un trim al lado derecho de las producciones*/
        /*Se toma el cuerpo de la produccion*/
        
        for (Produccion prod:produccionesCreadas)
        {
            String body = (String)prod.getCuerpo();
            //System.out.println("Cuerpo de la produccion: " + body);
            String[] objetos;
            
            objetos = body.split(" ");
            
            for(String ob:objetos)
            {
                if (!snoterminales.contains(ob.trim()))
                {
                    if (!sterminales.contains(ob.trim()))
                    {
                        sterminales.add(ob.trim());
                    }
                }
            }
            
            
        }
        
        System.out.println("\nterminales");
        for(String t: sterminales)
        {
            System.out.println(t.toString());
        }
        
    }

    
    public void first()
    {
        /*Se revisan las producciones y se revisa si lo que se esta
           revisando es un terminal o un no teminal*/
        for (Produccion prod:produccionesCreadas)
        {
            
        }
    }
    
    
    public void follow()
    {
        
    }
    
    /**
     * Este metodo crea las producciones encontradas despues de leer el texto
     */
    private ArrayList<Produccion> crearProducciones(String p)
    {
        System.out.println("CREAR PRODUCCIONES");
        /*Se analiza la cabeza*/
        int equal = 0;
        String head, body;
        Stack<String> pila = new Stack();
        ArrayList<Produccion> producciones = new ArrayList();
        
        equal = p.indexOf("=");
        head = p.substring(0,equal);
        head.trim();
        
        body = p.substring(equal +1, p.length()-1);
        body.trim();
        
        
        //DEBUG
        System.out.println("");
        System.out.println("Head: " + head);
        System.out.println("Body: " + body);
        //---------------------------------------------------
        
        /*Se analiza el cuerpo*/
        /*Se introduce el cuerpo a la pila*/
        pila.push(body);
        
        /*Mientra la pila no este vacia se separan las producciones.
            Si no contiene un or, se toma todo el cuerpo como el
               cuerpo de la produccion*/
        while(!pila.isEmpty()) {
            /*Se toma lo que esta en la pila*/
            String actual = String.valueOf(pila.pop());
            /*Si tiene un OR, entonces se separa la primera parte*/
            if (actual.contains("|"))
            {
                int or = actual.indexOf("|");
                Produccion prod = new Produccion (head,actual.substring(0,or).trim());
                System.out.println(prod);
                
                producciones.add(prod);
                
                /*Se agrega a la pila el resto de la cadena despues del OR*/
                pila.push(actual.substring(or+1));
            }
            else
            {
               Produccion prod = new Produccion(head,actual);
                System.out.println(prod);
            }
        }
        
        return producciones;
    }
    
   
     /**
     * Revisa que la primera linea del archivo este bien escrita.
     */
    private boolean revisarPrimeraLinea(String linea1)
    {
        /*Revisa que la priemera linea este aceptada o no*/
        boolean lineaAceptada = false;
        
        if (linea1.equals("PRODUCTIONS"))
            lineaAceptada = true;
        else
            lineaAceptada = false;
        
        return lineaAceptada;
    }
    
    
    
    /**
     * Revisa que la gramatica del archivo de 'parserSpecification' este bien 
     *  escrita y que cumple con la gramatica de Coco/R
     */
    private void parserSpecification()
    {
        /*Esta bandera indica si hubo o no algun error en la gramatica*/
        boolean errorEnLaGramatica = false;
        
        
        System.out.println("ParserSpecification");
        
        /*Se analiza la primera linea*/ 
        boolean linea1Aceptada = revisarPrimeraLinea(lineas[0]);
        
        /*Si la primera linea esta bien escrita, se procede a revisar el resto
           del documento.*/
        if (linea1Aceptada == true)
        {
            errorEnLaGramatica = false;
            System.out.println("La primera linea del parserSpecification esta "
                    + "bien escrita\n");
        }
        else
        {
            errorEnLaGramatica = true;
            /*Si la linea del documento esta mal escrita manda un mensaje al
               usuario
            */
            System.out.println("La primera linea del parserSpecificatios esta "
                    + "mal escrita. Se esperaba \"productions\"");
        }
        
        
        /*Si la primera linea fue acetpada se procede a analizar el resto del 
           documento. El resto del documento se revisa linea por linea*/
        if (linea1Aceptada == true)
        {
            /*Se revisa que las producciones econtrdas sean producciones
               validas*/
            for (int contador1 = 1; contador1<lineas.length;contador1++){
                /*Se toma la linea actual*/
                
                String lineaActual = "";
                lineaActual = lineas[contador1];
                
                /*Se imprime la linea actual*/
                System.out.println("Linea actual: " + lineaActual);
                
                /*Se revisa si es una produccion valida*/
                CharSequence equal = "=";
                CharSequence or = "|";
                CharSequence punto = ".";
                
                boolean punto_encontrado = false;
                
                if (lineaActual.contains(equal))
                {
                    System.out.println("Se econtro un signo igual");
                    
                    /*Mientras no se encuentre un punto en la linea se busca 
                       en la linea siguiente*/
                    while (punto_encontrado == false) {     
                        System.out.println("DW: " + lineaActual);
                        if (lineaActual.contains(punto)) {
                            punto_encontrado = true;
                            producciones.add(lineaActual);
                        } else {
                            /*Si no se encuentra el punto, significa que la
                             produccion esta incompleta y se busca un signo or*/
                            if (lineaActual.contains(or)) {
                                /*Si la linea actual se le agrega la linea siguiente*/
                                lineaActual = lineaActual + lineas[contador1 + 1];
                                contador1++;
                            }
                            
                        }
                    }
                }
            }
        }
        
        /*
        System.out.println("\n\nLas producciones encontradas en la gramatica son: ");
        for (int contador2 = 0; contador2<producciones.size(); contador2++){
            System.out.println(producciones.get(contador2).toString());
        }
        
                */
        
       if (errorEnLaGramatica == false) 
        System.out.println("LA GRAMATICA DE LAS PRODUCCIONES ESTA BIEN ESCRITA");
       else
            System.out.println("Se encontraron errores en la gramatica");
    }
    
    
    
   
}
