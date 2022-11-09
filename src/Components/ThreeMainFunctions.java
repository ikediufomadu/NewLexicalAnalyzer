package Components;

import static Components.GlobalVariables.*;
import static Components.HelperFunctions.*;

public class ThreeMainFunctions {
    static String munchedString = "";
    static String munchedNumber = "";
    static String munchedSymbol = "";
    static final String NOTHINGWASMUNCHED = null;
    //Get kind of lexeme
    public static String kind(TokenInfo t) {
        String checker = String.valueOf(t);
        //System.out.println(checker + " this is checker");
        char tFirstChar = checker.charAt(0);
        if (Character.isLetter(tFirstChar)) {
            return TokenInfo.currentKeyword = "'ID'";
        }
        else if (Character.isDigit(tFirstChar)) {
            return TokenInfo.currentKeyword = "'NUM'";
        }
        else if (t == null){
            TokenInfo.currentKeyword = "end-of-text";
            System.out.println("Reached the end of the file.\n...\nResetting program\n\n");
            sequenceKeepRunning();
        }
        else {
            TokenInfo.currentKeyword = "";
        }
        return TokenInfo.currentKeyword;
    }

    //Get value of lexeme if it is an ID or NUM
    public static String value(TokenInfo t) {
        if (kind(t).equals("'ID'")) {
            return TokenInfo.currentTokenValue = String.valueOf(t);
        }
        else if (kind(t).equals("'NUM'")) {
            return TokenInfo.currentTokenValue = String.valueOf(t);
        }
        return "";
    }

    public static String maxMunch(char charToMunch, int currentLine) {
        reportLexicalError(charToMunch, currentLine, currentCharInLine);
        //Last character does not get sent, fix this
        //System.out.println(charToMunch);

        if (Character.isLetter(charToMunch) || charToMunch == '_') {
            munchedString += charToMunch;
            //If an invalid char is reached, return munchedString and then call reportLexicalError
        }
        else if (Character.isDigit(charToMunch)) {
            if (Character.isWhitespace(TokenInfo.lastChar) || (TokenInfo.firstChar)) {
                munchedNumber += charToMunch;
            }
            else if (!Character.isWhitespace(TokenInfo.lastChar)) {
                if (munchedString.length() > 0) {
                    munchedString += charToMunch;
                }
                else {
                    munchedNumber += charToMunch;
                }
            }
        }
        else if (!Character.isDigit(charToMunch) && !Character.isLetter(charToMunch) && !Character.isWhitespace(charToMunch)) {
            munchedSymbol += charToMunch;
        }
        if (Character.isWhitespace(charToMunch)) {
            if (munchedString.length() > 0) {
                System.out.println(munchedString);
                munchedString = "";
            }
            if (munchedNumber.length() > 0) {
                System.out.println(munchedNumber);
                munchedNumber = "";
            }
            if (munchedSymbol.length() > 0) {
                System.out.println(munchedSymbol);
                munchedSymbol = "";
            }
        }
        return NOTHINGWASMUNCHED;
    }
}
