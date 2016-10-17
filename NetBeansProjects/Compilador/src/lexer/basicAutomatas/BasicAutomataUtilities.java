package lexer.basicAutomatas;

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
}
