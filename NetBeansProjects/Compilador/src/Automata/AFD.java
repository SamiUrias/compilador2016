/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Automata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Moises Urias
 */
public class AFD {
    /**
     * Estos son los estados del AFD
     */
    private ArrayList<Subset> estados = new ArrayList<Subset>();


    /**
     * Estas son las transiciones entre los estados del AFD
     * */
    private ArrayList<TransicionAfd> transiciones = new ArrayList<TransicionAfd>();


    private ArrayList<Transicion> tranciciones2 = new ArrayList<Transicion>();
    /**
     * Este es el contador de nodos del AFD.
     * Se utiliza el mismo contador de nodos que se utilizo para el AFN.
     * */
    private ContadorNodo contador; /*Contador del numero de nodos*/



    /**
     * Constructor de un AFD con base en un automata
     * @param afn
     */
    public AFD(Automata afn)
    {
        System.out.println("\n\nDentro de constructor del AFD");
        
        /*Verifica que aun existan estados no marcados en la pila.
        Se empieza con true, por el primer estado de la pila*/
        boolean hayEstadosNoMarcados = true;

        
        /*Se busca el estado inicial del afn*/
        int nodoinicial =0;

        for (int i=0;i<afn.getEstados().size();i++)
        {
            /*Se obtiene el nodo inicial de AFN*/
            if (afn.getEstados().get(i).iseInicial() == true){
                System.out.println("Estado inicial CONSTRUCTOR AFD: " + afn.getEstados().get(i).getId());
                nodoinicial = afn.getEstados().get(i).getId();
            }
        }
        
        
        /*Una ves ya se ha encontrado el estado inicial del afn, se crea un 
          subset con ese estado*/
        Subset subset1 = new Subset(nodoinicial, afn.getTransiciones());

        /*Se hace un eClosure de ese estado*/
        subset1 = OpExtra.eClosure(subset1);



        System.out.println("Primer e-closure del AFD: " + subset1);
        subset1.ordenar();
        System.out.println("Subset 1 ordenado: " + subset1);

        /*Se le pone nombre al primer estado del AFD*/
        //El nombre del primer estado del AFD por defecto es 1.
        //Como ya se creo el AFN, se renicia el contador de nodos
        ContadorNodo.reset();
        int primer_id_del_subset = contador.getInstance().getContador();
        System.out.println("Contador de nodos: " + primer_id_del_subset);
        subset1.setNombre_subset(primer_id_del_subset);
        System.out.println("El nombre asignado al subset1 es: " + subset1.getNombre_subset());
        
        /*Se agrega el subconjunto inicial a una lista de estados iniciales*/
        estados.add(subset1);
        
        
        /*Se obtiene el alfabeto del automata*/
        ArrayList<String> alfabeto = afn.getAlfabeto();






        /*********** Esto es lo que hay que arreglar ************/
        /*Un vez se ha creado el estado inicial del AFD, el resto de estados se construye de una forma recursiva*/
        /*Mientras haya estados no marcados, se hace e-closure y mover*/
        while(hayEstadosNoMarcados == true)
        {
            System.out.println("Dentro del ciclo while del AFD");
            
            /*Se recorren todos los estados que hay actualmente en el AFD*/
            for (int h=0; h<estados.size();h++)
            {
                /*Se toma el estado actual*/
                Subset estadoActualAnterior = estados.get(h);
                Subset estadoActual = estados.get(h);


                /*Como se esta revisando este esatdo, el esatado se marca como
                  marcado*/
                estadoActual.setMarcado(true);
                
                
                /*Se crea el nuevo estado del AFD*/
                for (int i=0; i<alfabeto.size(); i++) {
                    /*Se aplica la operacion 'mover' al estado actual*/
                    estadoActual = OpExtra.mover(estadoActual, alfabeto.get(i));

                    Subset estadoMove = estadoActual;

//                    System.out.println("El primer move del AFD (estado move): " + estadoMove);
//                    Subset estadoEClosure_move = OpExtra.eClosure(estadoMove);
//                    estadoEClosure_move.ordenar();
//                    System.out.println("estado eEclosure_move: " + estadoEClosure_move);
//                    System.exit(0);

                    /*Se hace eClosure al estado actual*/
                    estadoActual = OpExtra.eClosure(estadoActual);

                    /*Se ordena de menor a mayor el estado actual*/
                    estadoActual.ordenar();
                    System.out.println("Estado actual ordenado: " + estadoActual);  //Nuevo estado del AFD


                    /*Se revista si el estado obtenido despues de realizar el 
                      mover y el eclosure es un nuevo estado de la lista de
                      estados del AFD o ya existe.*/
                    boolean estadoExistente = false;

                    for(int g = 0; g<this.estados.size();g++){ //Todos los estados del AFD

                            /*Subset que posteriormente se añadira a los estados del
                               AFD*/
                        Subset sub = new Subset(); //Subset vacio

                            /*Se toma el arraylist de nodos del estado actual del ciclo que se esta analizando*/
                            /*La forma en que se comparan los subsets es con base a sus nodos*/
                        Subset estado1 = this.estados.get(g);

                            /*Se ordena de menor a mayor los nodos del estado actual del ciclo*/
                        estado1.ordenar();
                        System.out.println("Estado 1: " + estado1);
                        System.out.println("---------------------");

                            /*Compara si son iguales*/
                        if (estadoActual.equals(estado1))
                        {
                            System.out.println("Estado 1: " + estado1);
                            System.out.println("Estado Actual: " + estadoActual);
                            System.out.println("Lo estados son iguales");
                            estadoExistente = true;
                            //
                        }
                        else{
                            System.out.println("Estado 1: " + estado1);
                            System.out.println("Estado Actual: " + estadoActual);
                            System.out.println("Los estados no son iguales");
                            System.out.println("Como no son iguales, se agrega el nuevo estado al conjunto de estados");

                        }

                    }

                        /*Si despues de recorrer todos los estados que actualmente tiene el AFD se encontro que el
                        * estado acual no existe, entonces se agrega el estado a la lista de estado del AFD*/
                    if (estadoExistente == false){
                        System.out.println("----------  DEBUG: CREACION DE ESTADO -----------------");
                        System.out.println("La letra que se esta utilizando es: " + alfabeto.get(i) );
                        estadoActual.setNombre_subset(contador.getInstance().getContador());
                        System.out.println("Estado Actual.nombre = " + estadoActual.getNombre_subset());
                        System.out.println("Se crea la nueva transicion");
                        tranciciones2.add(new Transicion(estadoActualAnterior.getNombre_subset(),alfabeto.get(i),
                                estadoActual.getNombre_subset()));
                        estados.add(estadoActual);
                        System.out.println(estados);
                        System.out.println("tranciciones: " + tranciciones2.toString());

                    }

                }
            }

            System.exit(0);
            
            /*Se revisa que ya no hayan estados marcados*/
            boolean b = false;
            for (int g = 0; g<estados.size();g++)
            {

                if(estados.get(g).isMarcado() == false)
                {
                    b = true;
                }
            }

            hayEstadosNoMarcados = b;

        }

        System.out.println(this.estados);
    }




    public AFD(Subset subsetI, ArrayList<String> alfabeto)
    {

        /**
         * Se hace un eClosure con el estado inicial del afn
         */
        Subset moi = OpExtra.eClosure(subsetI);

        //subset moi = OpExtra.mover(subsetI, null);
        System.out.println(moi);

    }


    public void construccionPorSubconjuntos(Automata afn){

    }
}
