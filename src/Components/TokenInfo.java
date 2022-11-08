package Components;

public class TokenInfo {
    static String currentTokenValue;
    static String currentKeyword;
    static char nextChar;

    public TokenInfo (String tokenRead) {
        currentKeyword = tokenRead;
    }
    public TokenInfo (char[] charHolder, int j){
        if (j + 1 < charHolder.length) {
            nextChar = charHolder[j + 1];
        }
    }
}
