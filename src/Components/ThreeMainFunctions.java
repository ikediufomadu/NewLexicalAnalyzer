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
        else if (t.equals("end")){
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
    public static String maxMunch(char charToMunch, int currentLine, TokenInfo nextChar) {
        reportLexicalError(charToMunch, currentLine, currentCharInLine);
        //char nextOne = TokenInfo.nextChar;
        char s = nextChar.nextChar;
        if (Character.isDigit(charToMunch)) {
            munchedNumber += charToMunch;
            System.out.println(munchedNumber);

            return munchedNumber;
        }
        else if (Character.isLetter(charToMunch) || charToMunch == '_') {
            munchedString += charToMunch;
            if (Character.isDigit(s)){
                munchedString += s;
            }
            //CHECK IF NEXT CHAR IS ALSO A LETTER, OR A NUMBER.
            //If an invalid char is reached, return munchedstring and then call reportLexicalError
            //When a symbol is introduced stop munch.
            //When a space is reached stop munch.

            System.out.println(munchedString);

            return munchedString;
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
