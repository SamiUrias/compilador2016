package lexer;

import java.util.ArrayList;

/**
 * Esta clase modela un TokenExpr
 * Created by Samuel on 31/10/2016.
 */
public class TokenExpr {

    /*Contiene un arreglo de todos los tekenTerms encontrados*/
    private ArrayList<TokenTerm> tokenTermsArrayList;

    /*Partes del lexema*/
    String[] partes;

    public TokenExpr (String lexema){
        System.out.println("Dentro del constructor del TokenExp");
        separarLasPartesDelLexema(lexema);
    }

    private void separarLasPartesDelLexema(String lexema){
         /*Se separan todos las partes de la cadena por el simbolo '|' para poder generar TokensExp con ellos.*/
        partes = lexema.split("\\|");

        /*Se imprimen todas las partes encontradas. Estas partes son TokenFactors*/
        System.out.println("Todas las partes encontradas en el TokenTerm");
        for(int i = 0; i<partes.length;i++){
            System.out.println(partes[i]);
            TokenTerm tt = new TokenTerm(partes[i]);
        }
    }
}


