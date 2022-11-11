package Components;

public class TokenInfo {
    static String currentTokenValue;
    static String currentKeyword;
    static char lastChar;
    static char nextChar;
    static char currentChar;

    public TokenInfo (char[] charHolder, int j){
        if (j > 0) {
            lastChar = charHolder[j - 1];
        }
        if (j + 1 < charHolder.length) {
            nextChar = charHolder[j + 1];
        }
        currentChar = charHolder[j];
    }
    @Override
    public String toString() {
        return String.valueOf(nextChar);
    }
}
