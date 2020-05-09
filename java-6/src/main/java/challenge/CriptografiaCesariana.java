package challenge;

public class CriptografiaCesariana implements Criptografia {

    private String textoAlterado;
    private int iterationNumber = 3;
    private char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m',
            'n','o','p','q','r','s','t','u','v','w','x','y','z'};
    private char[] special_characters = {' ', '.', ';', '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9'};

    @Override
    public String criptografar(String texto) {

        textoAlterado = "";

        if(texto.equals(""))
            throw new IllegalArgumentException();

        texto = texto.toLowerCase();

        for (int i = 0; i < texto.length(); i++) {
            for (int j = 0; j < alphabet.length; j++) {
                if (texto.charAt(i) == alphabet[j]) {
                    if (j > 22) {
                        textoAlterado += Character.toString(alphabet[j + iterationNumber - 22]);
                    } else
                        textoAlterado += Character.toString(alphabet[j + iterationNumber]);
                }
            }
            for (int k = 0; k < special_characters.length; k++) {
                if (texto.charAt(i) == special_characters[k])
                    textoAlterado += Character.toString(special_characters[k]);
            }
        }

        return textoAlterado;
    }

    @Override
    public String descriptografar (String texto){
        textoAlterado = "";

        if(texto.equals(""))
            throw new IllegalArgumentException();

        texto = texto.toLowerCase();

        for (int i = 0; i < texto.length(); i++) {
            for (int j = 0; j < alphabet.length; j++) {
                if (texto.charAt(i) == alphabet[j]) {
                    if (j < iterationNumber) {
                        textoAlterado += Character.toString(alphabet[alphabet.length - (iterationNumber - j)]);
                    } else
                        textoAlterado += Character.toString(alphabet[j - iterationNumber]);
                }
            }
            for (int k = 0; k < special_characters.length; k++) {
                if (texto.charAt(i) == special_characters[k])
                    textoAlterado += Character.toString(special_characters[k]);
            }
        }
        return textoAlterado;
    }
}
