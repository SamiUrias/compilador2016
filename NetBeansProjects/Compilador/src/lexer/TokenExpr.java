package lexer;

import Automata.OpExtra;

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

    //Token term
    TokenTerm tokenTerm;

    private ArrayList<SSCharacter> characters;
    public TokenExpr (String lexema, ArrayList<SSCharacter> characters){
        System.out.println("Dentro del constructor del TokenExp");
        separarLasPartesDelLexema(lexema);
        creatTokenTerms(characters);
        System.out.println("Se ha terminado de crear el token expression");
    }

    /*Se separan todos las partes de la cadena por el simbolo '|' para poder generar TokensExp con ellos.*/
    private void separarLasPartesDelLexema(String lexema){
        partes = lexema.split("\\|");
    }

    /**
     * Este metodo crea token terms
     */
    private void creatTokenTerms(ArrayList<SSCharacter> characters) {
        /*Se imprimen todas las partes encontradas. Estas partes son TokenFactors*/
        System.out.println("Todas las partes encontradas en el TokenTerm");
        String regex=""; //regex de los TokenTerms

        for(int i = 0; i<partes.length;i++){
            System.out.println(partes[i]);
            TokenTerm tokenTerm = new TokenTerm(partes[i], characters);

            if(i>0){
                regex+="|";
            }

            regex+=tokenTerm.getRegex();
        }

        /*Se crea un TokenTerm, y se le asigna el regex final creado.*/
        this.tokenTerm = new TokenTerm();
        this.tokenTerm.setRegex(regex);

        System.out.println("Final regex: " + regex);
        OpExtra.leerPantalla();
    }

    public TokenTerm getTokenTerm() {
        return tokenTerm;
    }
}


