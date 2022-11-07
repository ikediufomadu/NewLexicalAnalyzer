package Components;

public class TokenInfo {
    String currentTokenValue;
    String currentKeyword;

    public TokenInfo (String keyword, String tokenRead) {
        currentTokenValue = tokenRead;
        currentKeyword = keyword;
    }
    public TokenInfo (String tokenRead) {
        currentKeyword = tokenRead;
    }
}
