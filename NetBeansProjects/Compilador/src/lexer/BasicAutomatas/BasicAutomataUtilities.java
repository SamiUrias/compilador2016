package lexer.BasicAutomatas;

import Automata.Automata;
import Automata.Nodo;
import Automata.Transicion;
import java.util.ArrayList;

/**
 * This class provides auxiliary methods for the creation of complex automata.
 * @author Samuel Urias
 * @date 10/17/2016
 * @version 1.0
 */
public class BasicAutomataUtilities {
    
    /**
     * This method returns an ArrayList of all the basic automata.
     */
    public static ArrayList<Automata> getBasicAutomatas(){
        ArrayList<Automata> basicAutomataArrayList = new ArrayList<>();
        
        basicAutomataArrayList.add(new DigitAutomata().getAutomata());
        basicAutomataArrayList.add(new LetterAutomata().getAutomata()); 
        basicAutomataArrayList.add(new HexDigitAutomata().getAutomata());
        
        return basicAutomataArrayList;
    }
    
    
    
     /**
        *This method find an automata of the basics automata ArrayList by its 
        *name and depends if the automata exits or not, it return the position
        *in the ArrayList if exits of -1 if not.
     */
    public static int findAuthomata(String name, ArrayList<Automata> arrayOfAutomatas){
        /*This flag represent if the authomata was found in the ArrayList*/
        int exist = -1;
        
        for (int i = 0; i<arrayOfAutomatas.size(); i++){
            String name_of_the_authomata = arrayOfAutomatas.get(i).getNombre();
            if (name_of_the_authomata.equals(name)){
                exist = i;
            }
        }
        
        return exist;
    }
    
    /**
     * 
     * @param name
     * @param arrayOfAutomatas 
     */
    public static Automata getAutomata(String name, ArrayList<Automata> arrayOfAutomatas){
        /*Founded automata*/
        Automata automata = null;
        
        /*Position of the automata*/
        int position = findAuthomata(name, arrayOfAutomatas);
        
        //If the automta exist
        if (position != -1){
            automata = arrayOfAutomatas.get(position);
        }
        
        return automata;
    }
    
    
    /**
     * Este metodo se utiliza para concatenar dos automatas ya formados.
     * Este metodo es básicamente el mismo metodo de la clase 'Automata'.
     * @param automata1
     * @param automata2 
     */
    public static Automata concatAuthomatas(Automata automata1, Automata automata2){
         int cont=0; /*Contador*/
       
        while(cont<automata1.getEstados().size())
        {
          Nodo nodo = automata1.getEstados().get(cont);
          
          if (nodo.iseFinal()==true){
            
              /*Se recorre el otro automata para buscar su estado inicial
                 y concatenarlos*/
              for(int i =0; i<automata2.getEstados().size();i++){
                  Nodo nodo2 = automata2.getEstados().get(i);
                  
                  if (nodo2.iseInicial()==true)
                  {
                     
                      /*Se crea una transicion entre ambos automatas */
                      nodo2.seteInicial(false);
                      automata1.transiciones.add(new Transicion(nodo.getId(),"!",nodo2.getId()));
                      
                      nodo.seteFinal(false);
                      
                      break;
                  }
              }
                  
          }  
          
          cont++;
        }
        
        /*Se agrega al automata1 todos los nodos y transiciones del automata2*/
        ArrayList<Nodo> antiguosEstadosDelAutomata = automata1.getEstados();
        for(int i=0;i<automata2.getEstados().size();i++)
        {
          antiguosEstadosDelAutomata.add(automata2.getEstados().get(i));
        }
        automata1.setEstados(antiguosEstadosDelAutomata);
        /*Se agregan todas las transiciones del automata2 al automata1*/
        for(int i=0;i<automata2.transiciones.size();i++)
        {
            automata1.transiciones.add(automata2.transiciones.get(i));
        }
        
        return automata1;
    }
    
      
    /**
     * Crea un automata en base a dos automatas proporcionados.<p>
     * Se usa para la expresion OR entre dos automatas
     * @param automata1
     * @param automata2 
     */
    public static Automata ORAutomata(Automata automata1, Automata automata2)
    {
        int cont =0; /*Contador*/
        Automata automata = new Automata(); /*Automata que contendra */
        
        Nodo nodoI = new Nodo(); /*Se crea un nodo inicial*/
        Nodo nodoF = new Nodo(); /*Se crea un nodo final*/
        
        
        /*Se recorre el primer automata*/
        while (cont<automata1.getEstados().size())
            {              
                
                /*Nodo provisional para nodos de los automatas*/
                Nodo estado = automata1.getEstados().get(cont);
                
                if (estado.iseInicial() == true)
                {
                    
                    
                    automata.transiciones.add(new Transicion(nodoI.getId(),"!",estado.getId()));
                   
                    
                    
                    
                    estado.seteInicial(false);
                    automata1.getEstados().set(cont, estado);
                }
                
                if (estado.iseFinal()==true)
                {
                    
                    automata.transiciones.add(new Transicion(estado.getId(),"!",nodoF.getId()));
                     
                    
                  
                    
                    estado.seteFinal(false);
                    automata1.getEstados().set(cont, estado);
                }
                
                cont++;
                
            }
        
        /*Se reinicia el contador a cero*/
        cont=0;
       
        
        /*Se recorren los estados del segundo automata*/
        while (cont<automata2.getEstados().size())
            {
                                
                /*Nodo provisional para nodos de los automatas*/
                Nodo estado = automata2.getEstados().get(cont);
                
                if (estado.iseInicial() == true)
                {
                    automata.transiciones.add(new Transicion(nodoI.getId(),"!",estado.getId()));
                  
                    estado.seteInicial(false);
                    automata2.getEstados().set(cont, estado);
                }
                
                if (estado.iseFinal()==true)
                {
                    automata.transiciones.add(new Transicion(estado.getId(),"!",nodoF.getId()));
                    
                    
                    
                    estado.seteFinal(false);
                    automata2.getEstados().set(cont, estado);
                }
                
                cont++;
                
            }
        
        
        cont =0; /*Reinicia el contrador a cero*/
        
        /*Asigna todos los nodos del automata1 al nuevo automata*/
        while(cont<automata1.getEstados().size())
        {
            automata.agregarEstado(automata1.getEstados().get(cont));
            cont++;
        }
        
        
        cont=0; /*Reinicia el contador a cero*/
        
        /*Asignna todos los nodos del automata2 al nuevo automata*/
        while(cont<automata2.getEstados().size())
        {
            automata.agregarEstado(automata2.getEstados().get(cont));
            cont++;
            
        }
        
        cont=0; /*Reinicia el contrador a cero*/
        
        /*Asinga las transiciones del atuomata1 al nuevo automata*/
        while(cont<automata1.transiciones.size())
        {
            automata.transiciones.add(automata1.transiciones.get(cont));
            cont++; //Se suma uno al contador
        }
        
        /*Se reinicia a cero el contrador*/
        cont = 0;
        
        /*Asina las transiciones del automata2 al nuevo automata*/
        while(cont<automata2.transiciones.size()){
            automata.transiciones.add(automata2.transiciones.get(cont));
            cont++;
        }
        
        /*se asignna un nuevo nodo inicial al automata*/
        nodoI.seteInicial(true);
        automata.agregarEstado(nodoI);
        
        /*Se asigna un nuevo nodo final al automata*/
        nodoF.seteFinal(true);
        automata.agregarEstado(nodoF);
    
        /*Devuelve el automata*/
        return automata;
    }
    
    
      /**
     * Devuevelve una automata despues de aplicarle la operacion kleene al 
     *  automata ingresado
     * @param automata1
     * @return 
     */
    public static Automata kleeneAutomata(Automata automata1)
    {
        
        Automata automata = automata1;
        
        Nodo nodoI = new Nodo(); /*Nuevo nodo inicial*/
        Nodo nodoF = new Nodo(); /*Nuevo nodo final*/
        
        int cont=0; /*Contador*/
        
        /*Se crean temporalmente estados iniciales y finales*/
        Nodo tEstadoInicial = null;
        Nodo tEstadoFinal = null;
        
        /*Se recorre el automata para encontrar el estado inicial*/
        while (cont<automata1.getEstados().size())
        {
            Nodo estado=automata1.getEstados().get(cont);
            if (estado.iseInicial()==true)
            {
                /*Se crea la transicion entre el nodo inicial del kleene y el 
                  nodo inicial del automata que se esta analizando*/
                automata.transiciones.add(new Transicion(nodoI.getId(),"!",estado.getId()));
                
                tEstadoInicial = estado;
                estado.seteInicial(false);
                
                /*Arreglo temporal de estados*/
                ArrayList tempEstados = automata.getEstados();
                
                
                /*Se le quita la propiedad de estado inicial*/
                tempEstados.set(cont, estado);
                
                /*Se le asigna al automata los estados temporales donde se 
                    removio el el estado inicial*/
                /*Se le quita la propiedad de estado inicial*/
                automata.setEstados(tempEstados);
            }

             /*Se crea la transicion entre el nodo final del kleene y el
                  nodo final del automata que se esta analizando*/
            if (estado.iseFinal() == true)
            {
                automata.transiciones.add(new Transicion(estado.getId(),"!",nodoF.getId()));
                

                tEstadoFinal = estado;
                estado.seteFinal(false);
                
                
                 
                /*Arreglo temporal de estados*/
                ArrayList tempEstados = automata.getEstados();
                
                
                /*Se le quita la propiedad de estado inicial*/
                tempEstados.set(cont, estado);
                
                /*Se le asigna al automata los estados temporales donde se 
                    removio el el estado inicial*/
                /*Se le quita la propiedad de estado inicial*/
                automata.setEstados(tempEstados);
            }
            
            cont++;
        }
        
        /*Se crea una transicion entre el estado final y el estado inicial 
            del automata original*/
            automata.transiciones.add(new Transicion(tEstadoFinal.getId(),"!",tEstadoInicial.getId()));
           
            /*Se agrga la transicion epsilon entre el primer nuevo estado 
            inicial y el nuevo estado final*/
            automata.transiciones.add(new Transicion(nodoI.getId(),"!",nodoF.getId()));
           
            /*Se agregan los nuevos nodos al automata*/
            
            nodoF.seteFinal(true);
            nodoI.seteInicial(true);
            
            automata.agregarEstado(nodoF);
            automata.agregarEstado(nodoI);
        
            /*Deuvelve el automata generado*/
        return automata;
    }
    
    
     /**
     * Devuevelve una automata despues de aplicarle la operacion kleene al 
     *  automata ingresado
     * @param automata1
     * @return 
     */
    public static Automata kleene(Automata automata1)
    {
        
        Automata automata = automata1;
        
        Nodo nodoI = new Nodo(); /*Nuevo nodo inicial*/
        Nodo nodoF = new Nodo(); /*Nuevo nodo final*/
        
        int cont=0; /*Contador*/
        
        /*Se crean temporalmente estados iniciales y finales*/
        Nodo tEstadoInicial = null;
        Nodo tEstadoFinal = null;
        
        /*Se recorre el automata para encontrar el estado inicial*/
        while (cont<automata1.estados.size())
        {
            Nodo estado=automata1.estados.get(cont);
            if (estado.iseInicial()==true)
            {
                /*Se crea la transicion entre el nodo inicial del kleene y el 
                  nodo inicial del automata que se esta analizando*/
                automata.transiciones.add(new Transicion(nodoI.getId(),"!",estado.getId()));
                
                tEstadoInicial = estado;
                estado.seteInicial(false);
                
                /*Se le quita la propiedad de estado inicial*/
                automata.estados.set(cont, estado);
            }

             /*Se crea la transicion entre el nodo final del kleene y el
                  nodo final del automata que se esta analizando*/
            if (estado.iseFinal() == true)
            {
                automata.transiciones.add(new Transicion(estado.getId(),"!",nodoF.getId()));
                

                tEstadoFinal = estado;
                estado.seteFinal(false);
                
                /*Se le quita la propiedad de estado final*/
                automata.estados.set(cont, estado);
            }
            
            cont++;
        }
        
        /*Se crea una transicion entre el estado final y el estado inicial 
            del automata original*/
            automata.transiciones.add(new Transicion(tEstadoFinal.getId(),"!",tEstadoInicial.getId()));
           
            
            /*Se agrga la transicion epsilon entre el primer nuevo estado 
            inicial y el nuevo estado final*/
            automata.transiciones.add(new Transicion(nodoI.getId(),"!",nodoF.getId()));
           
            /*Se agregan los nuevos nodos al automata*/
            
            nodoF.seteFinal(true);
            nodoI.seteInicial(true);
            
            automata.estados.add(nodoF);
            automata.estados.add(nodoI);
        
            /*Deuvelve el automata generado*/
        return automata;
    }

}
