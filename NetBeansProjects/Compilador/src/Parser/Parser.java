/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import Automata.OpExtra;

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

    /*Almacena todos los terminales*/
    private ArrayList<String> terminalesArray = new ArrayList<>();

    private ArrayList<String> getNoTerminales = new ArrayList<>();

    /*Almacena el documento*/
    private String documento = "";

    /*Almacena la linea */
    private int productionsLine = 0;

    /*Esta bandera se utiliza al momento de separar las producciones para saber si hay que el punto es un punto final
    * o no.*/
    private boolean final_point = false; //SemAction

    /**
     * Constructor vacio
     */
    public Parser(){}

    /**
     * Esto inicializa el parser con un documento.
     * @param documento
     */
    public Parser(String documento){
        System.out.println("Se ha recibido el documento");
        this.documento = documento;
        //analizar(); //Se parsea el documento ingresado.
    }

    /**
     * Este metodo se encarga de guiar el proceso de parseo.
     * */
    public void analizar(){
        separarProducciones();
        separarTerminalesYNoTerminales();
        mostrarMenu();
    }

    /**
     * ESTE ES EL CONSTRUCTOR QUE SE ESTA UTILIZANDO ACTUALMENTE.
     * Este metodo se encarga de guiar el proceso de parseo.
     * @param documento
     */
    public void analizar(String documento){
        System.out.println("\n\n->  Dentro del metodo de 'analizar' en el parser. ");
        System.out.println("El largo del documento es:  " + documento.length());
        separarProducciones(); //Se encuentra todas las proddducciones.


    }
    /**
     * Este metodo separa toda las producciones que hay en el archivo.
     * Esto lo hace buscando el '.'
     * */
    public void separarProducciones(){
        String produccion = "";
        String caracterActual = "";
        int contador = 0;

        //Ajustar contador
        System.out.println("Se ajusta el contador para que tenga el valor donde se encontro la palabra characters.");
        contador = productionsLine;

        System.out.printf("Contador: " + contador);
        System.out.println("L: " + documento.length());

        while (contador<documento.length()){
            caracterActual = documento.substring(contador, contador + 1);
            System.out.println(caracterActual);

            if (!caracterActual.equals(".")){
                produccion += caracterActual;
            }
            else{
                produccion += caracterActual;
                checkForFinalPoint(contador);


                //Si hay un punto final se agrega lo anteriormente encontrado a una nueva produccion.
                if(final_point) {
                    producciones.add(produccion);
                    System.out.println("Se ha agregado una nueva produccion");
                    OpExtra.imprirLinea();
                }
            }

            contador++; //Aumenta uno al contador para evitar el ciclo infinito
        }

        System.out.println("Las producciones son: ");
        System.out.println(producciones);
    }


    /**
     * Este metodo separa los terminales y los no terminales.
     */
    public void separarTerminalesYNoTerminales(){
        for (int cont = 0; cont < producciones.size(); cont++){
            int equqalPosition = producciones.get(cont).indexOf("=");
            String terminal =  producciones.get(cont).substring(0,equqalPosition).trim();
            System.out.println("Terminal: " + terminal);
            terminalesArray.add(terminal);
        }

        System.out.println("AT: " + terminalesArray);
    }

    public void mostrarMenu(){
        int op  = 0;
        while (op != 3){
            System.out.println("Menu:");
            System.out.println("1. First:");
            System.out.println("2. Follow:");
            System.out.println("3. Salir:");
            Scanner scanner = new Scanner(System.in);
            try{
                op = Integer.valueOf(scanner.nextInt());
            }
            catch (Exception e){
                System.out.println("Se debe de ingresar un numero!");
            }

            switch (op){
                case 1:{
                    calcularFirst();
                    break;
                }
                case 2:{
                    calcularFollow();
                    break;
                }
                case 3:{
                    System.exit(0);
                }
                default:{
                    System.out.println("No es una opcion valida");
                }
            }
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
            //Se revisa el caracter anterior
            if (documento.substring(posicion - 1, posicion).equals("(")){
                System.out.println("El caracter anterior tenia un 'parentesis");
                OpExtra.leerPantalla();
            }
            //Si no se cumple ninguno de los requisitos anteriores, entonces se dice que si es un punto final.
            else {
                isFinalPoint = true;
            }
        }
        catch (Exception e){
            System.out.println("Existe un problema con el punto en una produccion");;
        }

        return isFinalPoint;
    }
    /****************************************  GETTERS Y SETTERS *****************************************************/
    public int getProductionsLine() {
        return productionsLine;
    }

    public void setProductionsLine(int productionsLine) {
        this.productionsLine = productionsLine;
    }
}
