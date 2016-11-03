package lexer;

/**
 * Created by Samuel on 31/10/2016.
 */
public class TokenTerm {

//    public TokenTerm(String lexema){
//        String newTokenFactor = "";
//        int cont = 0;
//
//        while(cont<lexema.length()){
//            String actualCharacter = lexema.substring(cont, cont+1);
//
//            /*Si el caracter no es un '{' o un '[' o un '('*/
//            if ((!actualCharacter.equals("{")) || (!actualCharacter.equals("[")) || (!actualCharacter.equals("("))){
//                newTokenFactor += actualCharacter;
//            }
//        }
//
//        System.out.println("El toquen factor encontrado hasta ahora fue: " + newTokenFactor);
//    }

    public TokenTerm(String lexema){
        System.out.println("We are in the token term constructor");
        System.out.println(lexema + " " + "("+lexema.length()+")");
        String newTokenFactor = "";
        int cont = 0;

        while(cont<lexema.length()){
            String actualCharacter = lexema.substring(cont, cont+1);

            /*Si el caracter no es un '{' o un '[' o un '('*/
            if ((actualCharacter.equals("{")) || (actualCharacter.equals("[")) || (actualCharacter.equals("("))){
                System.out.println("Se encontro una cosa rara: \'" + actualCharacter+"\'");
                break;
            }
            else {
                newTokenFactor += actualCharacter;
            }


            cont++; //Aumenta el contador
        }

        System.out.println("El toquen term encontrado hasta ahora fue: " + newTokenFactor);
    }
}
