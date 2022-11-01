package Components;

import static Components.HelperFunctions.*;

public class TwoMainFunctions {
    //Get kind of lexeme
    public static String kind(TokenInfo t) {
        switch (t.currentKeyword) {
            case "identifiers" -> t.currentKeyword = "'ID'";
            case "integer" -> t.currentKeyword = "'NUM'";
            case "keyword", "symbol" -> {
                return "'" + t.currentTokenValue + "'";
            }
            case "program_name" -> {
                return t.currentTokenValue;
            }
            case "end-of-file" -> {
                t.currentKeyword = "end-of-text";
                System.out.println("Reached the end of the file.\n...\nResetting program\n\n");
                sequenceKeepRunning();
            }
        }
        return t.currentKeyword;
    }

    //Get value of lexeme if it is an ID or NUM
    public static String value(TokenInfo t) {
        if (t.currentKeyword.equals("'ID'")) {
            return t.currentTokenValue;
        }
        else if (t.currentKeyword.equals("'NUM'")) {
            return t.currentTokenValue;
        }
        return "";
    }
}
