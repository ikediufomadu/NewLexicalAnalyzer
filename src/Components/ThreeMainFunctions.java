package Components;

import java.util.HashSet;
import java.util.Map;

import static Components.GlobalVariables.*;
import static Components.HelperFunctions.*;

public class ThreeMainFunctions {
    static String munchedString = "";
    static String munchedNumber = "";
    static String munchedSymbol = "";
    static String nothingWasMunched = "";
    //Get kind of lexeme
    public static String kind(TokenInfo t) {
        String checker = String.valueOf(t);
        char tFirstChar = checker.charAt(0);

        if (Character.isLetter(tFirstChar)) {
            t.currentKeyword = "'ID'";
            value(t);
        }
        else if (Character.isDigit(tFirstChar)) {
            t.currentKeyword = "'NUM'";
            value(t);
        }
        else if (t.equals("end") || t.equals(null)){
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
        if (t.currentKeyword.equals("'ID'")) {
            return t.currentTokenValue;
        }
        else if (t.currentKeyword.equals("'NUM'")) {
            return t.currentTokenValue;
        }
        return "";
    }
    public static String maxMunch(char charToMunch, int currentLine) {
        reportLexicalError(charToMunch, currentLine, currentCharInLine);
        char nextOne = TokenInfo.nextChar;
        if (Character.isLetter(charToMunch) || charToMunch == '_') {
            munchedString += charToMunch;
            if (Character.isDigit(nextOne)){
                munchedString += nextOne;
            }
            //CHECK IF NEXT CHAR IS ALSO A LETTER, OR A NUMBER.
            //If an invalid char is reached, return munchedString and then call reportLexicalError
        }
        else if (Character.isDigit(charToMunch)) {
            munchedNumber += charToMunch;
        }
        else if (!Character.isDigit(charToMunch) && !Character.isLetter(charToMunch) && !Character.isWhitespace(charToMunch)) {
            munchedSymbol += charToMunch;
        }
        //Adds munchedNumber/munchedString to words list when a space is hit
        else if (Character.isWhitespace(charToMunch) /*&& (munchedString.length() >= 1 || munchedString.length() > 0|| munchedSymbol.length() > 0*/) {
            //Need to make sure that it is not adding partially finished strings to the list
            //Need to figure out which one to print first. Use if condition to check out of the three ints which one is the least and then print the corresponding string
            if (munchedString.length() > 0) {
                wordList.add(munchedString);
                System.out.println(munchedString);
                munchedString = "";
            }
            else if (munchedNumber.length() > 0) {
                wordList.add(munchedNumber);
                System.out.println(munchedNumber);
                munchedNumber = "";
            }
            else if (munchedSymbol.length() > 0) {
                wordList.add(munchedSymbol);
                System.out.println(munchedSymbol);
                munchedSymbol = "";
            }
        }
        //Figure out which string to return to the function call

        return nothingWasMunched;
    }
}
