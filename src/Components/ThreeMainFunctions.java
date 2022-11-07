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
    static String appendedNumber = "";
    static int wordIndex = 0;
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
            while (Character.isDigit(nextOne)) {
                munchedString += nextOne;
                appendedNumber += nextOne;
            }
            System.out.println(munchedString + " This is current munched letter");
            //If an invalid char is reached, return munchedString and then call reportLexicalError
        }
        else if (Character.isDigit(charToMunch)) {
            if (!appendedNumber.equals(charToMunch)) {
                munchedNumber += charToMunch;
                System.out.println(munchedNumber + " This is current munched number");
            }
            munchedNumber = "";
            appendedNumber = "";
        }
        else if (!Character.isDigit(charToMunch) && !Character.isLetter(charToMunch) && !Character.isWhitespace(charToMunch)) {
            munchedSymbol += charToMunch;
        }
        //Adds munchedNumber/munchedString/munchedSymbol to words list when a space is hit
        else if (Character.isWhitespace(charToMunch) /*make sure to check if there is a new line*/) {
            //Need to make sure that it is not adding partially finished strings to the list
            //Need to figure out which one to print first. Use if condition to check out of the three ints which one is the least and then print the corresponding string
            if (!munchedString.isEmpty()) {
                wordList.add(munchedString);
                System.out.println(munchedString);
                munchedString = "";
                return wordList.get(wordIndex);
            }
            if (!munchedNumber.isEmpty()) {
                wordList.add(munchedNumber);
                System.out.println(munchedNumber);
                munchedNumber = "";
                return wordList.get(wordIndex);
            }
            if (!munchedSymbol.isEmpty()) {
                wordList.add(munchedSymbol);
                System.out.println(munchedSymbol);
                munchedSymbol = "";
                return wordList.get(wordIndex);
            }
        }
        //Figure out which string to return to the function call
        return nothingWasMunched;
    }
}
