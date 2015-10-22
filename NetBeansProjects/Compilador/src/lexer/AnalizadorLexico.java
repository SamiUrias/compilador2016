package lexer;
/**
 * @author Moises Urias
 */
import java.io.*; import java.util.*;
public class AnalizadorLexico {
    public int linea =1;
    private char vistazo = ' ';
    
    private Hashtable palabras = new Hashtable();
  
    /**
     * Este metodo resrva una palabra en el HashTable palabras
     * @param t 
     */
    public void reservar (Palabra t){ 
        palabras.put(t.nombre, t.valor); 
    }
    
    public AnalizadorLexico()
    {
        //reservar (new Palabra(Etiqueta.TRUE, "true") );
        //reservar (new Palabra(Etiqueta.FALSE, "false") );
    }
    
    
    public Token explorar(String texto) throws IOException {
        int i =0; /*Contador del texto*/
        for(i=0;i<texto.length();i++) {
            vistazo = (char)texto.charAt(i);
            if (vistazo == ' ' || vistazo == '\t') continue;
            else if (vistazo == '\n') linea = linea +1;
            else break;
        }
        
         if (Character.isDigit(vistazo)) {
             int v = 0;
             do {
                 v = 10 * v + Character.digit(vistazo, 10);
                 vistazo = (char)texto.charAt(i);
                 i++; /*Se hace un incremento al apuntado aunque se este fuera
                          del ciclo for*/
             } while (Character.isDigit(vistazo));
             
             return new Num(v);
         }
         
         if (Character.isLetter(vistazo)) {
             StringBuffer b  = new StringBuffer();
             do {
                 b.append(vistazo);
                 vistazo = (char)texto.charAt(i);
                 i++;
             } while (Character.isLetterOrDigit(vistazo));
             
             String s = b.toString();
             Palabra w = (Palabra)palabras.get(s);
             if (w != null) return w;
             //w = new Palabra (Etiqueta.ID, s);
             palabras.put(s, w);
             return w;
         }
         
         Token t = new Token(vistazo);
         vistazo = ' ';
         return t;
    }

    /**
     * Esta funcion devuelve todas las lineas encontradas en el texto
     * @param texto
     * @return 
     */
    public String[] devolverLineas (String texto)
    {
        String[] lineas;
        
        lineas = texto.split("\n");
        return lineas;
    }
    
   
    /**
     * Esta funcion devuelve la ultima linea encontrada en el texto
     * @param texto
     * @return 
     */
    public String devolverUltimaLinea (String texto)    
    {
        String [] lineas;
        lineas = texto.split("\n");
        
        
        return lineas[lineas.length-1];
    }
    
}
