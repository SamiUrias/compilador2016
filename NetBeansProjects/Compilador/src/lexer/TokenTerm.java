package lexer;

import Automata.OpExtra;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Samuel on 31/10/2016.
 */
public class TokenTerm {
    private String regex = "";

    private ArrayList<SSCharacter> chacters;
    /**
     * Constructor vacio
     */
    public TokenTerm (){};

    /**
     * Constructor que toma como parametros un lexema y un ArrayList de 'SSCharacters'
     * @param lexema
     * @param chacters
     */
    public TokenTerm(String lexema, ArrayList<SSCharacter> chacters){
        this.chacters = chacters; //Asigna los characters
        System.out.println("We are in the token term constructor");
        System.out.println(lexema + " " + "("+lexema.length()+")");
        String stringNewTokenFactor = "";
        int cont = 0;

        String stringLiteral = "";
        boolean stringToAdd = false; //Esta bandera marca si se ha encontrado un ' \" '

        while(cont<lexema.length()){
            String actualCharacter = lexema.substring(cont, cont+1);


            /*Si el caracter no es un '{' o un '[' o un '('.
            * Esto solo se hará si no se ha encontrado un ' \" ', de lo contrario solo se agregará lo que se encuentre*/
            if (
                    (actualCharacter.equals("{")) ||
                    (actualCharacter.equals("[")) ||
                    (actualCharacter.equals("(")) ||
                    (actualCharacter.equals(")")) ||
                    (actualCharacter.equals("]")) ||
                    (actualCharacter.equals("}"))
                    ){


                if ((stringToAdd == false)){

                    System.out.println("StringToAdd = false");
                    System.out.println("Se encontro una cosa rara: \'" + actualCharacter+"\'");
                    System.out.println("Se procedera a procesar lo que se encontro");
                    OpExtra.leerPantalla();

                    stringNewTokenFactor = stringNewTokenFactor.trim(); //Se le quitan los espacios en blanco

                    System.out.println("El toquen term encontrado hasta ahora fue: " + stringNewTokenFactor);
                    System.out.println("Se va a verificar si el tokenTerm en un token term.");

                    /*Verifica si es String, Ident o Char*/
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
                                //OpExtra.leerPantalla();
                                System.out.println("Regex: " + regex);
                                //OpExtra.leerPantalla();
                            }
                        }
                    }
                    else {
                        System.out.println("El token factor: \'" + tokenfactor.getLexema()+ "\' es un CHAR");
                    }

                    //Se analiza la 'cosa rara' que se encontro
                    if (actualCharacter.equals("{")){
                        System.out.println("Al regex se le agregara el simbolo: '('");
                        //OpExtra.leerPantalla();
                        if(cont!=0){
                            regex+="|";
                        }
                        regex+="(";
                    }

                    if (actualCharacter.equals("}")){
                        System.out.println("Al regex se le agregara el simbolo: ')*'");
                        //OpExtra.leerPantalla();
                        regex+=")*";
                    }


//                    regex = createRegex(stringNewTokenFactor, actualCharacter);
                    stringNewTokenFactor = "";
                }
                else {
                    System.out.printf("AC: " + actualCharacter);
                    stringLiteral += actualCharacter;
                }
            }
            else if  (actualCharacter.equals("\"")) {
                /* Cambia el valor actual de la variable booleana stringToAdd.
                *  Esto se hace con el fin de que si es la primera vez que se encuentran las comillas, el programa
                *  empiece a adjuntar lo que se haya dentro de las comillas a la variable String 'stringLiteral'.
                *  Y cuando se encuentran las comillas por segunda vez, lo que esta en 'stringLiteral se
                *  agrega a la expresión regular.*/

                System.out.println("Se han encontrado unas comillas =D ");
                System.out.println("El valor actual de stringToAdd es: " + stringToAdd);
                OpExtra.leerPantalla();

                if (stringToAdd == false){
                    stringToAdd = true;
                }
                else {
                    System.out.println("StringLiteral: " + stringLiteral);
                    stringToAdd = false;
                    regex += stringLiteral;
                    stringLiteral = ""; //Se limpia el valor de 'stringToAdd'
                }
            }
            else {
                if (stringToAdd){
                    System.out.println("AC: " + actualCharacter);
                    stringLiteral += actualCharacter;
                }

                stringNewTokenFactor += actualCharacter;
            }

            cont++; //Aumenta el contador
        } //END: While

        //Begin: Analisis del caso extraño
        //Aqui se debe analizar el caso extraño
        System.out.println("SNTF:");
        System.out.println(stringNewTokenFactor);
        OpExtra.leerPantalla();
        stringNewTokenFactor = stringNewTokenFactor.trim();
        /*Primera parte del analisis del caso extraño: Se revisa si el new token factor es mayor a uno*/
        if(stringNewTokenFactor.length()>1){

            /* Segunda parte:
             * Si es mayor a uno, se compara el utlimo simbolo.
             * Esto se hace comparando su ultimo simbolo, el cual no deberia de ser un simbolo especial.
             */
            if(
                    (!(stringNewTokenFactor.substring(stringNewTokenFactor.length()-1, stringNewTokenFactor.length()).equals("{"))) &&
                    (!(stringNewTokenFactor.substring(stringNewTokenFactor.length()-1, stringNewTokenFactor.length()).equals("}"))) &&
                    (!(stringNewTokenFactor.substring(stringNewTokenFactor.length()-1, stringNewTokenFactor.length()).equals("]"))) &&
                    (!(stringNewTokenFactor.substring(stringNewTokenFactor.length()-1, stringNewTokenFactor.length()).equals("["))) &&
                    (!(stringNewTokenFactor.substring(stringNewTokenFactor.length()-1, stringNewTokenFactor.length()).equals(")"))) &&
                    (!(stringNewTokenFactor.substring(stringNewTokenFactor.length()-1, stringNewTokenFactor.length()).equals("."))) &&
                    (!(stringNewTokenFactor.substring(stringNewTokenFactor.length()-1, stringNewTokenFactor.length()).equals("(")))
                ){
                System.out.println("Caso extraño");
                OpExtra.leerPantalla();
                String SNTF_regex = createRegex(stringNewTokenFactor);
                System.out.println("SNTF_regex: " + SNTF_regex);
                this.regex += SNTF_regex;
                OpExtra.leerPantalla();
            }
            else {
                System.out.println("Se encontro que el SNTF no esta vacio, pero si termina con un simbolo especial");
                OpExtra.leerPantalla();
            }

        }

        //End:Analisis del caso extraño

        System.out.println("Final regex:");
        System.out.println(regex);
       // System.exit(0);
    }


    /**
     * Este metodo se utiliza para deteminar la forma de analizar el lexma en caso de
     * que haya un lexema que no temina con algun simbolo {  '{', '}' '[', ']', '(', ')'  }
     * Este tipo de lexemas es necesario analizrlo de una forma especial, y este metodo es un metodo auliar para
     * ese tipo de analisis.
     * @param lexema
     * @return
     */
    private boolean casoEspecialDeLexema(String lexema){
        boolean esCasoEspecial = false;



        return  esCasoEspecial;
    }


    private String createRegex(String stringNewTokenFactor){
        String regex = "";

        System.out.println("El toquen term encontrado hasta ahora fue: " + stringNewTokenFactor);
        System.out.println("Se va a verificar si el tokenTerm en un token term.");

                    /*Verifica si es String, Ident o Char*/
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
                    //OpExtra.leerPantalla();
                    System.out.println("Regex: " + regex);
                    //OpExtra.leerPantalla();
                }
            }
        }
        else {
            System.out.println("El token factor: \'" + tokenfactor.getLexema()+ "\' es un CHAR");
        }

        return regex;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }
}
