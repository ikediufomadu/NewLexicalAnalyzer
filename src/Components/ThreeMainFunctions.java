package Components;

import java.util.HashSet;
import java.util.Map;

import static Components.GlobalVariables.*;
import static Components.HelperFunctions.*;

public class ThreeMainFunctions {
    static String munchedString = "";
    static String munchedNumber = "";
    //Get kind of lexeme
    public static String kind(TokenInfo t) {
        String checker = String.valueOf(t);
        char tFirstChar = checker.charAt(0);

        if (Character.isLetter(tFirstChar)) {
            t.currentKeyword = "'ID'";
            value(t);
        }
        else if (Character.isDigit(tFirstChar)) {
            t.currentKeyword = "'NUM";
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
        //System.out.println(charToMunch + " This is the current char");
        //System.out.println(nextOne + " This is the current char pointed");
        if (Character.isLetter(charToMunch) || charToMunch == '_') {
            munchedString += charToMunch;
            System.out.println(munchedString);
            if (Character.isDigit(nextOne)){
                munchedString += nextOne;
            }
            //CHECK IF NEXT CHAR IS ALSO A LETTER, OR A NUMBER.
            //If an invalid char is reached, return munchedstring and then call reportLexicalError
            //When a symbol is introduced stop munch.

            return munchedString;
        }
        else if (Character.isDigit(charToMunch)) {
            munchedNumber += charToMunch;
            System.out.println(munchedNumber);

            return munchedNumber;
        }
        //Adds munchedNumber/munchedString to words list when a space is hit
        else if (Character.isWhitespace(charToMunch)) {
            wordList.add(munchedString);
            wordList.add(munchedNumber);
            munchedString = "";
            munchedNumber = "";
        }
        return munchedString;
    }
}
