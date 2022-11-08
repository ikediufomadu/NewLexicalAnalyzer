package Components;

import java.util.HashSet;
import java.util.Map;

import static Components.GlobalVariables.*;
import static Components.HelperFunctions.*;

public class ThreeMainFunctions {
    static String munchedString = "";
    static String munchedNumber = "";
    static String munchedSymbol = "";
    static final String NOTHINGWASMUNCHED = "";
    //Get kind of lexeme
    public static String kind(TokenInfo t) {
        String checker = String.valueOf(t);
        //System.out.println(checker + " this is checker");
        char tFirstChar = checker.charAt(0);
        if (Character.isLetter(tFirstChar)) {
            return t.currentKeyword = "'ID'";
        }
        else if (Character.isDigit(tFirstChar)) {
            return t.currentKeyword = "'NUM'";
        }
        else if (t.equals(null)){
            t.currentKeyword = "end-of-text";
            System.out.println("Reached the end of the file.\n...\nResetting program\n\n");
            sequenceKeepRunning();
        }
        else {
            for (Map.Entry<String, HashSet<String>> entry : map.entrySet()) {
                //Checks if t is a keyword or symbol
                if (t.equals(entry)) {
                    TokenInfo.currentTokenValue = String.valueOf(t);
                    return "'" + t.currentTokenValue + "'";
                }
            }
        }
        return t.currentKeyword;
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
        System.out.println(charToMunch);

        char nextOne = TokenInfo.nextChar;
        if (Character.isLetter(charToMunch) || charToMunch == '_') {
            munchedString += charToMunch;
            if (Character.isDigit(nextOne)){
                munchedString += nextOne;
            }
            //If an invalid char is reached, return munchedString and then call reportLexicalError
        }
        else if (Character.isDigit(charToMunch)) {
            munchedNumber += charToMunch;
        }
        else if (!Character.isDigit(charToMunch) && !Character.isLetter(charToMunch) && !Character.isWhitespace(charToMunch)) {
            munchedSymbol += charToMunch;
        }
        //Adds munchedNumber/munchedString/munchedSymbol to words list when a space is hit
        else if (Character.isWhitespace(charToMunch) /*Need to account for new lines*/) {
            //Need to make sure that it is not adding partially finished strings to the list
            //Figure out what string to send back to Next function
            //Figure out what to print first.
            if (munchedString.length() > 0) {
                wordList.add(munchedString);
                TokenInfo t = new TokenInfo(munchedString);
                //System.out.println(munchedString + " munched String was outputted\nKind is: "  + kind(t) + "\nValue is: " + value(t) + "\n");
                munchedString = "";
                munchedNumber = "";
            }
            else if (munchedNumber.length() > 0) {
                wordList.add(munchedNumber);
                TokenInfo t = new TokenInfo(munchedNumber);
               //System.out.println(munchedNumber + " munched Number was outputted\nKind is: "  + kind(t) + "\nValue is: " + value(t) + "\n");
                munchedNumber = "";
            }
            else if (munchedSymbol.length() > 0) {
                wordList.add(munchedSymbol);
                TokenInfo t = new TokenInfo(munchedSymbol);
               // System.out.println(munchedSymbol + " munched Symbol was outputted\nKind is: "  + kind(t) + "\nValue is: " + value(t) + "\n");
                munchedSymbol = "";
            }
        }
        //Figure out which string to return to the function call

        return NOTHINGWASMUNCHED;
    }
}
