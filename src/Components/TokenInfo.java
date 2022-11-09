package Components;

public class TokenInfo {
    static String currentTokenValue;
    static String currentKeyword;
    static char lastChar;
    static boolean firstChar;

    public TokenInfo (String tokenRead) {
        currentKeyword = tokenRead;
    }
    public TokenInfo (char[] charHolder, int j){
        if (j > 0) {
            lastChar = charHolder[j - 1];
        }
        else if (j == 0) {
//            lastChar = charHolder[j];
            firstChar = true;
        }
        else {firstChar = false;}
    }
}
