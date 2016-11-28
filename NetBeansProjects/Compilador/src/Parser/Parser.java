/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import Automata.OpExtra;
import com.sun.corba.se.impl.javax.rmi.CORBA.Util;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.*;

/**
 * Esta clase se encarga de parsear el documento ingresado
 * @author Samuel
 */ 
public class Parser {
    /*Almacena todas las producciones*/
    private ArrayList<String> producciones = new ArrayList<>();

    /*Almacena todos los no terminales*/
    private ArrayList<String> noTerminalesArray = new ArrayList<>();

    /*Almacena el documento*/
    private String documento = "";

    /*Almacena la linea */
    private int productionsLine = 0;

    /*Esta bandera se utiliza al momento de separar las producciones para saber si hay que el punto es un punto final
    * o no.*/
    private boolean final_point = false; //SemAction

    /*Esta bandera indica si se ha encontrado un parentesis antes del punto y nos encontramos leyendo una regla
    * semantica*/
    private boolean reading_semantic_rule = false;


    /*Esta variable almacena el parser generado*/
    private String programaParserGenerado = "";


    /**
     * Se almacenan las producciones encontradas.
     */
    private ArrayList<Production> arrayListProductions = new ArrayList<Production>();

    /*Almacena todas las lineas del documento.*/
    private String[] lineas;

    /**
     * Constructor vacio.
     * Por el momento este constructor no hace nada.
     */
    public Parser(){}




    /**
     * Esto inicializa el parser con un documento.
     * @param documento
     */
    public Parser(String documento){
        System.out.println("Dentro del parser");
        System.out.println("Se ha recibido el documento");
        this.documento = documento;
    }


    /**
     * ESTE ES EL CONSTRUCTOR QUE SE ESTA UTILIZANDO ACTUALMENTE.
     * Este metodo se encarga de guiar el proceso de parseo.
     * @param documento
     */
    public void analizar(String documento){
        System.out.println("\n\n->  Dentro del metodo de 'analizar' en el parser. ");
        System.out.println("El largo del documento es:  " + documento.length());
        separarElDocumentoEnLineas();
        checkForProductionsWord();
        separarProducciones(); //Se encuentra todas las proddducciones.
        separarTerminalesYNoTerminales(); //Separa los teminales y los no terminales.
        createParser(); //Crea el parser
        System.out.println("\n\n******************************");
        System.out.println("Se ha terminado de ejecutar el parser");
    }


    /**
     * Este metodo se encarga de revisar si existe o no la palabra 'PRODUCTIONS' dentro del documento.
     */
    private void checkForProductionsWord(){
        int result = productionsWordFinder();
        if (result != -1){
            productionsLine = result;
            System.out.println("Si de ha encontrado la palabra 'Productions'");
            //OpExtra.leerPantalla();
        }
        else{
            System.out.println("Error, dentro del parse no se encontro 'PRODUCTIONS'");
            System.exit(0);
        }
    }

    /**
     * Este metodo separa todas las lineas del documento.
     */
    private void separarElDocumentoEnLineas(){
        lineas = documento.split("\\r?\\n");
        System.out.println("Se ha separado el documento en lineas");
        System.out.println("En total hay: " + lineas.length + " lineas");
    }



    /**
     * Este metodo separa toda las producciones que hay en el archivo.
     * Esto se hace empezando desde 'productionsLine' que es la linea que contiene las producciones.
     * Una vez encontrada esa linea, el proceso de parseo empieza desde la linea siguiente.
     * Esto lo hace buscando el '.'
     * */
    public void separarProducciones(){

        String produccion = "";
        String caracterActual = "";


        int contador = documento.indexOf("PRODUCTIONS"); //Se encuentra la palabra 'PRODUCTIONS'.
        contador += 11; //El contador se coloca exactamente despues de la palabra 'PRODUCTIONS'.

        System.out.println("Contador: " + contador); //Imprimer la posicion actual del contador
        while (contador<documento.length()){
            caracterActual = documento.substring(contador, contador + 1);
            if (!caracterActual.equals(".")){
                produccion += caracterActual;
            }
            else{
                produccion += caracterActual;
                final_point = checkForFinalPoint(contador);


                //Si hay un punto final se agrega lo anteriormente encontrado a una nueva produccion.
                if(final_point) {
                    producciones.add(produccion);
                    System.out.println("Se ha agregado una nueva produccion:");
                    OpExtra.imprirLinea();
                    System.out.println(produccion);
                    OpExtra.imprirLinea();
                    //OpExtra.leerPantalla();


                    final_point = false;    //Se reestablece el valor del final point false
                    produccion = "";        //Se reestablece el valor de la produccion a "" (vacio).
                    System.out.println("Se ha reestablecido el valor del 'final_point'");
                }
            }

            System.out.println(produccion);
            contador++; //Aumenta uno al contador para evitar el ciclo infinito
        }

        System.out.println("Las producciones son: ");
        System.out.println(producciones);
    }

    /**
     * Este metodo al igual que en el lexer se encarga de buscar la palabra 'productions' dentro del documento para
     * poder empezar el proceso de parseo.
     */
    private int productionsWordFinder(){
        //Mantiene el control de las lineas
        int cont = 0;
        boolean find = false;

        for (int i = 0; i<lineas.length; i++){
            if (lineas[i].trim().equals("PRODUCTIONS")){
                System.out.println("Dentro del parsers se ha eoncontrado la linea de producciones");
                System.out.println("La linea de producciones es: " + i);
                find = true;
            }
        }

        //Si no se encontro la palabra 'PRODUCTIONS' entonces se devuelve -1
        if (find == false){
            cont  = -1;
        }

        return cont;
    }


    /**
     * Este metodo separa los terminales y los no terminales.
     */
    public void separarTerminalesYNoTerminales(){
        System.out.println("Dentro de separar terminales y no terminales");
        OpExtra.leerPantalla();

        for (int cont = 0; cont < producciones.size(); cont++){
            int equqalPosition = producciones.get(cont).indexOf("=");
            String nombre =  producciones.get(cont).substring(0,equqalPosition).trim();
            String lexema = producciones.get(cont).substring(equqalPosition+1, producciones.get(cont).length());
            System.out.println("nombre: " + nombre);
            System.out.println("Lexema: " + lexema);

            //Se almacena en el arreglo de Producciones las produciones encontradas.
            arrayListProductions.add(new Production(nombre, lexema));
        }

    }


    /**
     * Este metodo calcula 'First' de la lista de producciones
     */
    private void calcularFirst(){
        System.out.println("METODO CALCULAR FIRST");

    }

    private void calcularFollow(){
        System.out.println("METODO CALCULAR follow");
    }


    /**
     * Este metodo se encarga de revisar si el punto que se encontro en  alguna línea es o no un punto final que
     * determina si se ha acabado la produccion. Esto se hace comparando el caracter que tiene antes del punto.
     */
    private boolean checkForFinalPoint (int posicion){
        String previous_character =  "";
        String actual_character = "";
        String next_character = "";
        boolean isFinalPoint = false;

        try{
            System.out.println("Caracter anterior: \'" + documento.substring(posicion -1, posicion) + "\'");
            System.out.println ("Caracter siguiente: \'" + documento.substring(posicion+1, posicion + 2) + "\'");
            //OpExtra.leerPantalla();

            //Se revisa si el caracter anterior era un parentesis o unas comillas '"'
            //Tambien se revisa si despues del punto hay otro parentesis u otras comillas.
            //Se revisa si el caracter siguiente no era un espacio en blanco "".
            if (documento.substring(posicion - 1, posicion).equals("(")){
                System.out.println("El caracter anterior tenia un 'parentesis'");
                reading_semantic_rule = true;
                System.out.println("reading_semantic_rule = true");
                //OpExtra.leerPantalla();
                final_point = false;
            }
            else if ((documento.substring(posicion-1, posicion).equals("\"") && ((documento.substring(posicion+1, posicion+2).equals("\""))))){ //Caracter anterior

                System.out.println("El caracter anterior tenia unas 'comillas'");
                System.out.println("El caracter siguiente tenia unas 'comillas'");
                System.out.println("Este punto esta dentro de comillas");
                //OpExtra.leerPantalla();
                final_point = false;

            }
            else if(documento.substring(posicion+1, posicion+2).equals(")")){
                System.out.println("El caracter siguiente tiene un 'parentesis'");
                reading_semantic_rule = false;
                System.out.println("reading_semantic_rule = false");
                //OpExtra.leerPantalla();
                final_point = false;
            }

            //Si no se cumple ninguno de los requisitos anteriores, entonces se dice que si es un punto final.
            else {
                System.out.println("Se encontro un punto final en la produccion");

                //Se verifica que no se este leyendo una regla semantica
                if(reading_semantic_rule == false) {
                    isFinalPoint = true;
                }
            }
        }
        catch (Exception e){
            System.out.println("Existe un problema con el punto en una produccion");;
        }

        return isFinalPoint;
    }

    private void createParser(){
        System.out.println("We are in the create parser");
        OpExtra.leerPantalla();


        /*Se analizan todas las producciones para limpiar sus nombres, y sus atributos*/
        for(int i = 0; i<arrayListProductions.size(); i++){
            //Se crea un nuevo metodo
            Method metodo = new Method();

            System.out.println("La produccion: que se va a analizar:  " + arrayListProductions.get(i).getNombre());

            /*Se reemplazan las producciones el arreglo de producciones, por las nuevas producciones que ya tienen
            * analizados los atributos*/
            arrayListProductions.set(i,checkForProductionAtributes(arrayListProductions.get(i)));
        }

//        for(int i=0; i<arrayListProductions.size(); i++){
//            System.out.println("\""+arrayListProductions.get(i).getNombre() + "\""+"    Atributos: " + arrayListProductions.get(i).getAtributos());
//        }

        //Revisa todas las producciones
        for (int i =0; i<arrayListProductions.size();i++){
            int number_of_curly_braces = 0;

            OpExtra.leerPantalla();

            System.out.println("P: " + arrayListProductions.get(i).getNombre());
            System.out.println("L: " + arrayListProductions.get(i).getLexema());
            //Revisa todos los lexemas
           for(int j = 0; j<arrayListProductions.get(i).getLexema().length();j++){


               ArrayList<String> semanticRusles = new ArrayList<>();
               String c = arrayListProductions.get(i).getLexema().substring(j, j+1);
               System.out.println("C: \'" + c +"\'");
               OpExtra.leerPantalla();

               if ((c.equals("\"")) || (c.equals("("))||(c.equals("{"))){
                   System.out.println("Se ha encontrado el simbolo: " + c);
                   OpExtra.leerPantalla();

                   //Se suma uno al contador de curly braces
                   if(c.equals("{")){
                       number_of_curly_braces++;
                   }


               }
           }
        }
    }


    /**
     * Este metodo se encarga de ver si la produccion que se esta analizando tiene atributos o no.
     * Si tiene atribuotos, estos se clasifican adecuadamente.
     */
    private Production checkForProductionAtributes(Production production){
        /*Se revisa si el nombre de la produccion contiene un simbolo '<'. Si contine este simbolo, se encuentran
        * todos los caracteres que esten entre estos dos simbolos, y esto es lo que se considerarian los atributos
        * de la produccion. Se separan los atributos de la produccion y se deja solamente el nombre de la misma*/

        //Nueva produccion
        Production newProduction = production;

        /*Esta bandera se utiliza para saber si se tienen ambos simbolos. El de abrir atributos y el de cerrar
        * atributos.*/
        boolean ambos_simbolos = false;


        if(newProduction.getNombre().contains("<")){
            System.out.println("Esta produccion tiene atributos");

            if (newProduction.getNombre().contains(">")){
                System.out.println("Esta produccion si contiene el simbolo para cerrar los atributos");
                ambos_simbolos = true;
            }
            else{
                System.out.println("ERROR");
                System.out.println("Esta produccion no tiene el simbolo para cerrar los atributos '>'");
                OpExtra.leerPantalla();
                System.exit(0);
            }

            //Solo si se tienen ambos simbolos, se procede a analizar los atributos
            if(ambos_simbolos){
                int apertura_de_atributo = newProduction.getNombre().indexOf("<");
                int cerradura_de_atributo = newProduction.getNombre().indexOf(">");
                System.out.println("Los atributos encontrados son:");
                String atributos =  newProduction.getNombre().substring(apertura_de_atributo+1,cerradura_de_atributo);
                System.out.println(atributos);

                //Se agregan los atributos a la produccion
                newProduction.setAtributos(atributos);
                newProduction.setHaveAtributes(true);

                //Se remueven los atributos del nombre de la produccion
                String nuevo_nombre = newProduction.getNombre().substring(0,apertura_de_atributo);
                newProduction.setNombre(nuevo_nombre);
            }

            System.out.println("Se ha terminado de corregir los atributos de esta produccion");
        }
        else {
            System.out.println("Esta produccion no tiene atributos");
        }


        //Se le quitan los espacios en blanco al nombre por seguridad
        newProduction.setNombre(newProduction.getNombre().trim());

        return newProduction;
    }

    private void createMethodName(Production production){

    }

    /****************************************  GETTERS Y SETTERS *****************************************************/
    public int getProductionsLine() {
        return productionsLine;
    }

    public void setProductionsLine(int productionsLine) {
        this.productionsLine = productionsLine;
    }
}
