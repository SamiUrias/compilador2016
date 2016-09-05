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
public class Produccion<T> {
    
    private T cabeza;
    private T cuerpo;
    
    private int posicion;
    
    
    /**
     * Constructor de la produccion
     * @param head
     * @param body 
     */
    public Produccion(T head,T body)
    {
        this.cabeza = head;
        this.cuerpo = body;
    }
    
    public Produccion()
    {
        
    }
    
     
     @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final Produccion<?> other = (Produccion<?>) obj;
        
        /*Compara las cabezas de las producciones*/
        if (!Objects.equals(this.cabeza, other.cabeza)) {
            return false;
        }
        
        /*Compara los cuerpos de las producciones*/
        if (!Objects.equals(this.cuerpo, other.cuerpo)) {
            return false;
        }
        
        /*Compara las posiciones de las producciones*/
        if (this.posicion != other.posicion) {
            return false;
        }
        
        /*Si no hay nada que impida la igualdad de las producciones, se
          devuelve verdadero*/
        return true;
    }

    public T getCabeza() {
        return cabeza;
    }

    public void setCabeza(T cabeza) {
        this.cabeza = cabeza;
    }

    public T getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(T cuerpo) {
        this.cuerpo = cuerpo;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    @Override
    public String toString() {
        return "Produccion{" + "cabeza=" + cabeza + ", cuerpo=" + cuerpo + '}';
    }
    
    
}
