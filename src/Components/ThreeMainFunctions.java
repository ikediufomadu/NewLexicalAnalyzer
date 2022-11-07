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

    public static String maxMunch(char charToMunch) {
        reportLexicalError(charToMunch, currentLine, currentCharInLine);
        if (charToMunch != ' ') {

        }
        if (Character.isLetter(charToMunch)) {
            munchedString += charToMunch;
            //CHECK IF NEXT CHAR IS ALSO A LETTER, OR A NUMBER.
            //If an invalid char is reached, return munchedstring and then call reportLexicalError
            //When a symbol is introduced stop munch.
            //When a space is reached stop munch.

            System.out.println(munchedString);

            return munchedString;
        }
        else if (Character.isDigit(charToMunch)) {
            munchedNumber += charToMunch;
            //CHECK IF NEXT CHAR IS ALSO A NUMBER, IF SO CONTINUE TO MUNCH
            //ELSE APPEND MUNCHED STRING TO THE WORDS LIST
            System.out.println(munchedString);

            return munchedNumber;
        }
        return munchedString;
    }
}
