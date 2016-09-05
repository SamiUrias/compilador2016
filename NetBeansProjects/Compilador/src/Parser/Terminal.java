/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Parser;

/**
 * Esta clase modela un terminal
 * @author Samuel
 */
public class Terminal {
    
    /*Almacena el terminal*/
    private String terminal;
    
    public Terminal()
    {
        
    }
    
    public Terminal(String t)
    {
        this.terminal = t;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }
    
    
}
