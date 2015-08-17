/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laboratorio.pkg2;

/**
 * Esta clase tiene operaciones adicionales que son de utilidad al programa
 * principal
 * @author Moises Urias
 */
public class OpExtra {
    
    /**
     * Realiza un eClosure en base a un subset
     * @param subset 
     */
    public Subset eClosure(Subset subset)
    {
        /*Recorre todas las transiciones del subset*/
        for (int i=0;i<subset.getTransiciones().size();i++)
        {
            
            Transicion trans = subset.getTransiciones().get(i);
            
            if (trans.getNodoInicial()==)
        }
    }
    
    
    
}
