/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio.pkg2;

import java.util.ArrayList;

/**
 *
 * @author Moises Urias
 */
public class AFD {
    
    private ArrayList<Subset> estados = new ArrayList<Subset>(); 
    
    
    
    /**
     * Constructor de un AFD con base en un automata
     * @param AFN 
     */
    public AFD(Automata afn)
    {
        /*Verifica que aun existan estados no marcados en la pila*/
        boolean hayEstadosNoMarcados = true;
        
        
        /*Se busca el estado inicial del afn*/
        int nodoinicial =0;
        
        for (int i=0;i<afn.getEstados().size();i++)
        {
            
            if (afn.getEstados().get(i).iseInicial() == true){
                System.out.println("Estado inicial CONSTRUCTOR AFD: " + afn.getEstados().get(i).getId());
                nodoinicial = afn.getEstados().get(i).getId();
            }
        }
        
        /*Una ves ya se ha encontrado el estado inicial del afn, se crea un 
          subset con ese estado*/
        Subset subset1 = new Subset(nodoinicial,afn.getTransiciones());
        
        /*Se hace un eClosure de ese estado*/
        subset1 = OpExtra.eClosure(subset1);
        
        
        /*Se agrega el subconjunto inicial a una lista de estados iniciales*/
        estados.add(subset1);
        
        
        /*Se obtiene el alfabeto del automata*/
        ArrayList<String> alfabeto = alfabeto = OpExtra.alfabeto(cadena);
        /*Mientras haya estados no marcados, se hace eclosure y move*/
        while(hayEstadosNoMarcados == true)
        {
            for (int i=0; i<)
        }
    }
    
    
    
    
    public AFD(Subset subsetI, ArrayList<String> alfabeto)
    {
        
        /**
         * Se hace un eClosure con el estado inicial del afn
         */
        Subset moi = OpExtra.eClosure(subsetI);
        
        //subset moi = OpExtra.move(subsetI, null);
        System.out.println(moi);
        
        
        
        
        
    }
}
