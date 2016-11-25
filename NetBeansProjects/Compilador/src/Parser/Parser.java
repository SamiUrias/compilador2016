/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

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
        parsearElDocumento(); //Se parsea el documento ingresado.
    }

    /**
     * Este metodo se encarga de guiar el proceso de parseo.
     * */
    public void parsearElDocumento(){
        separarProducciones();
        separarTerminalesYNoTerminales();
        mostrarMenu();
    }


    /**
     * Este metodo separa toda las producciones que hay en el archivo.
     * Esto lo hace buscando el '.'
     * */
    public void separarProducciones(){
        String produccion = "";
        String caracterActual = "";
        int contador = 0;

        while (contador<documento.length()){
            caracterActual = documento.substring(contador, contador + 1);
            System.out.println(caracterActual);

            if (!caracterActual.equals(".")){
                produccion += caracterActual;
            }
            else{
                produccion += caracterActual;
                producciones.add(produccion);
                produccion = "";
            }

            contador++; //Aumenta uno al contador para evitar el ciclo infinito
        }

        System.out.println("Las producciones son: ");
        System.out.println(producciones);
    }


    /**
     *
     * @param documento
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
}
