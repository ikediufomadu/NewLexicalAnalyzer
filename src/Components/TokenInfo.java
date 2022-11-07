package Components;

public class TokenInfo {
    static String currentTokenValue;
    String currentKeyword;
    static char nextChar;

    public TokenInfo (String keyword, String tokenRead) {
        currentTokenValue = tokenRead;
        currentKeyword = keyword;
    }
    public TokenInfo (String tokenRead) {
        currentKeyword = tokenRead;
    }
    public TokenInfo (char[] charHolder, int j){
        if (j + 1 < charHolder.length) {
            nextChar = charHolder[j + 1];
        }
    }
}
