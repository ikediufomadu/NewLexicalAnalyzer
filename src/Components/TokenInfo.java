package Components;

public class TokenInfo {
    static String currentTokenValue;
    static String currentKeyword;
    static char lastChar;
    static char nextChar;

    public TokenInfo (String tokenRead) {
        currentKeyword = tokenRead;
    }
    public TokenInfo (char[] charHolder, int j){
        if (j > 0) {
            lastChar = charHolder[j - 1];
        }
        if (j + 1 < charHolder.length) {
            nextChar = charHolder[j + 1];
        }
    }
}
