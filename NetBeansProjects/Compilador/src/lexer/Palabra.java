/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexer;

/**
 * Esto es lo que se almacena en el HashTabe del AnalizadorLexico.
 * @author Moises Urias
 */
public class Palabra extends Token{
    /*El valor es de tipo object para que pueda recibir cualquier cosa.
      Se tiene preferencia por los String, ya que estos son los que se pueden
      concatenar. Se se puede concatener String, char e Integer*/
    public Object valor;
    public String nombre;
    
    public int id = 0;
    
    
    public Palabra(int tipo, String nombre, Object valor) {
        super(tipo); 
        this.valor = valor;
        this.id = ContadorId.getContador();
        this.nombre = nombre;
    }
    
    /**
     * Constructor vacio
     */
    public Palabra()
    {
        
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
