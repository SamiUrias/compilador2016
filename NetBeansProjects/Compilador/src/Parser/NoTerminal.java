/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

import java.util.Objects;



/**
 *
 * @author Samuel
 */
public class NoTerminal {
    private String NoTerminal;
    
    /*Constructor del NoTerminal*/
    public NoTerminal()
    {
        
    }
    
    
    /*Constructor de NoTerminal con parámetro String*/
    public NoTerminal(String noTerminal)
    {
        this.NoTerminal = noTerminal;
    }

    public String getNoTerminal() {
        return NoTerminal;
    }

    public void setNoTerminal(String NoTerminal) {
        this.NoTerminal = NoTerminal;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        /*Se evalua si los dos objetos son de la misma clase*/
        if (getClass() == obj.getClass()) {
            
            /*Si los dos objetos son de la misma clase, entonces se procede
               a comparar las propiedades de los mismos*/
            NoTerminal tmpNoTerminal = (NoTerminal)obj;
            
            /*Se revisa si los no terminales son iguales*/
            if (this.NoTerminal.equals(tmpNoTerminal.getNoTerminal()))
            {
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
    
    
    /**
     * Regresa el largo del no terminal.
     * El largo del no terminal es igual al numero de<a href="Character.html#unicode"> unidades
     * de caracteres unicode</a> en el no terminal.
     *
     * @return  el largo del la secuencia de caracteres representados por este
     *          objecto.
     */
    public int size()
    {
        return this.NoTerminal.length();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.NoTerminal);
        return hash;
    }
    
    

    @Override
    public String toString() {
        return "NoTerminal{" + "NoTerminal=" + NoTerminal + '}';
    }
    
    
}
