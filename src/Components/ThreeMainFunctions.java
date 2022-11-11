package Components;

import static Components.GlobalVariables.*;
import static Components.HelperFunctions.*;

public class ThreeMainFunctions {
    static String munchedString = "";
    static String munchedNumber = "";
    static String munchedSymbol = "";
    static final String NOTHINGWASMUNCHED = null;
    static boolean wrongInput = false;
    static boolean symbolNext = false;
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
            return TokenInfo.currentTokenValue = t.toString();
        }
        else if (kind(t).equals("'NUM'")) {
            //return int value, not a string
            return TokenInfo.currentTokenValue = t.toString();
        }
        return "";
    }

    public static String maxMunch(char charToMunch, int currentLine, int j) {
        if (Character.isLetter(charToMunch) || charToMunch == '_') {
            munchedString += charToMunch;
            if (munchedNumber.length() > 0) {
                System.out.println(munchedNumber);
                munchedNumber = "";
            }
        }
        else if (Character.isDigit(charToMunch)) {
            if (TokenInfo.lastChar == '_') {
                System.out.println(munchedString);
                munchedString = "";
            }
            if (Character.isWhitespace(TokenInfo.lastChar)) {
                munchedNumber += charToMunch;
            }
            else if (!Character.isWhitespace(TokenInfo.lastChar)) {
                if (Character.isDigit(TokenInfo.lastChar)) {
                    char[] hasNumber = munchedString.toCharArray();
                    boolean anyNumber = false;
                    for (char c : hasNumber) {
                        if (Character.isDigit(c)) {
                            anyNumber = true;
                        }
                    }
                    if (anyNumber) {
                        munchedString += charToMunch;
                    }
                    else {
                        munchedNumber += charToMunch;
                    }
                }
                else if (Character.isLetter(TokenInfo.lastChar)) {
                    munchedString += charToMunch;
                }
                else {
                    munchedNumber += charToMunch;
                }
            }
        }
        else if (!Character.isDigit(charToMunch) && !Character.isLetter(charToMunch) && !Character.isWhitespace(charToMunch)) {
            if (reportLexicalError(charToMunch)) {
                if (Character.isLetter(TokenInfo.lastChar)){
                    wrongInput = true;
                    return munchedString;
                }
                if (Character.isDigit(TokenInfo.lastChar)) {
                    wrongInput = true;
                    char[] hasNumber = munchedString.toCharArray();
                    boolean anyNumber = false;
                    for (char c : hasNumber) {
                        if (Character.isDigit(c)) {
                            anyNumber = true;
                        }
                    }
                    if (anyNumber) {
                        return munchedString;
                    }
                    else {
                        return munchedNumber;
                    }
                }
                System.out.println("\nIllegal character at " + position(currentLine, currentCharInLine) + ". Character is '" + charToMunch + "'.\nExiting program...");
                System.exit(0);
            }
            //Not allowing current symbol to be sent
            if (Character.isLetter(TokenInfo.lastChar)) {
                symbolNext = true;
                return munchedString;
            }
            else if (Character.isDigit(TokenInfo.lastChar)) {
                symbolNext = true;
                return munchedNumber;
            }
            // =< logic
            if (charToMunch == '=' && TokenInfo.nextChar == '<') {
                munchedSymbol += charToMunch;
                munchedSymbol += TokenInfo.nextChar;
                symbolNext = false;
                return munchedSymbol;
            }
            // >= logic
            if (charToMunch == '>' && TokenInfo.lastChar == '='){
                munchedSymbol += charToMunch;
                munchedSymbol += TokenInfo.nextChar;
                symbolNext = false;
                return munchedSymbol;
            }
            // != logic
            if (charToMunch == '!' && TokenInfo.nextChar == '='){
                munchedSymbol += charToMunch;
                munchedSymbol += TokenInfo.nextChar;
                symbolNext = false;
                return munchedSymbol;
            }
            // := logic
            if (charToMunch == ':' && TokenInfo.nextChar == '='){
                munchedSymbol += charToMunch;
                munchedSymbol += TokenInfo.nextChar;
                symbolNext = false;
                return munchedSymbol;
            }
            // < logic
            if (charToMunch == '<' && TokenInfo.lastChar != '='){
                munchedSymbol += charToMunch;
                symbolNext = false;
                return munchedSymbol;
            }
            // = logic
            if (charToMunch == '=' && TokenInfo.lastChar != '>' && TokenInfo.lastChar != '!' && TokenInfo.nextChar != '<' && TokenInfo.lastChar != ':'){
                munchedSymbol += charToMunch;
                symbolNext = false;
                return munchedSymbol;
            }
            // > logic
            if (charToMunch == '>' && TokenInfo.nextChar != '='){
                munchedSymbol += charToMunch;
                symbolNext = false;
                return munchedSymbol;
            }
            // +, -, *, /, (, ), ;, : logic
            if (charToMunch == '+' || charToMunch == '-' || charToMunch == '*' || charToMunch == '/' || charToMunch == '(' || charToMunch == ')' || charToMunch == ';' || charToMunch == ':'){
                munchedSymbol += charToMunch;
                if (charToMunch == ':' && TokenInfo.nextChar == '='){
                    munchedSymbol = "";
                    return munchedSymbol;
                }
                if (charToMunch == '=' && TokenInfo.lastChar == ':'){
                    munchedSymbol = "";
                    return munchedSymbol;
                }
                symbolNext = false;
                return munchedSymbol;
            }
        }
        if (Character.isWhitespace(charToMunch)) {
            if (Character.isLetter(TokenInfo.lastChar) || munchedString.length() > 0) {
                return munchedString;
            }
            if (Character.isDigit(TokenInfo.lastChar) || munchedNumber.length() > 0) {
                return munchedNumber;
            }
            if (!Character.isLetter(TokenInfo.lastChar) && !Character.isDigit(TokenInfo.lastChar) && !Character.isWhitespace(TokenInfo.lastChar)) {
                return munchedSymbol;
            }
        }
        return NOTHINGWASMUNCHED;
    }
}
