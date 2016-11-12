package lexer;

import Automata.OpExtra;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Samuel on 31/10/2016.
 */
public class TokenTerm {
    private String regex = "";

    public TokenTerm(String lexema, ArrayList<SSCharacter> chacters){
        System.out.println("We are in the token term constructor");
        System.out.println(lexema + " " + "("+lexema.length()+")");
        String stringNewTokenFactor = "";
        int cont = 0;

        while(cont<lexema.length()){
            String actualCharacter = lexema.substring(cont, cont+1);
            String tokenRegex = "";

            /*Si el caracter no es un '{' o un '[' o un '('*/
            if ((actualCharacter.equals("{")) || (actualCharacter.equals("[")) || (actualCharacter.equals("(")) ||
                    (actualCharacter.equals(")")) || (actualCharacter.equals("]")) || (actualCharacter.equals("}"))){

                System.out.println("Se encontro una cosa rara: \'" + actualCharacter+"\'");
                System.out.printf("Se procedera a procesar lo que se encontro");
                OpExtra.leerPantalla();

                System.out.println("El toquen term encontrado hasta ahora fue: " + stringNewTokenFactor);
                System.out.println("Se va a verificar si el tokenTerm en un token term.");

                TokenFactor tokenfactor = new TokenFactor(stringNewTokenFactor);

                if (tokenfactor.isString){
                    System.out.println("El token factor: \'" + tokenfactor.getLexema()+ "\' es un STRING");
                }
                else if(tokenfactor.isIdent){
                    System.out.println("El token factor: \'" + tokenfactor.getLexema()+ "\' es un IDENT");
                    String nombre = tokenfactor.getLexema();

                    for (int i = 0; i<chacters.size();i++){
                        if (chacters.get(i).getToken().equals(nombre)){
                            System.out.println("Se ha encontrado el ident dentro de los characters previamente generados");
                            regex += chacters.get(i).getLexema().getSet().getRegularExpression();
                            OpExtra.leerPantalla();
                            System.out.println("Regex: " + regex);
                            OpExtra.leerPantalla();
                        }
                    }
                }
                else {
                    System.out.println("El token factor: \'" + tokenfactor.getLexema()+ "\' es un CHAR");
                }

                //Se analiza la 'cosa rara' que se encontro
                if (actualCharacter.equals("{")){
                    System.out.println("Al regex se le agregara el simbolo: '('");
                    OpExtra.leerPantalla();
                    regex+="(";
                }

                if (actualCharacter.equals("}")){
                    System.out.println("Al regex se le agregara el simbolo: ')*'");
                    OpExtra.leerPantalla();
                    regex+=")*";
                }

                stringNewTokenFactor = "";
            }
            else {
                stringNewTokenFactor += actualCharacter;
            }

            cont++; //Aumenta el contador
        }


        System.out.println("Final regex:");
        System.out.println(regex);
       // System.exit(0);
    }
}
