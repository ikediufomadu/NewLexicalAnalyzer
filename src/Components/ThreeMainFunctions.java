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
            //return int value, not a string
            return TokenInfo.currentTokenValue = String.valueOf(t);
        }
        return "";
    }

    public static String maxMunch(char charToMunch, int currentLine, int j) {
        //If an invalid char is reached, return munchedString and then call reportLexicalError
        //This is in the wrong spot. It calls but does not return last string. Fix this
        reportLexicalError(charToMunch, currentLine, currentCharInLine);

        if (Character.isLetter(charToMunch) || charToMunch == '_') {
            munchedString += charToMunch;
            if (munchedNumber.length() > 0) {
                System.out.println(munchedNumber);
                wordList.add(munchedNumber);
                munchedNumber = "";
            }
        }
        else if (Character.isDigit(charToMunch)) {
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
            if (reportLexicalError(charToMunch, currentLine, currentCharInLine)) {
                if (munchedString.length() > 0) {
                    System.out.println(munchedString);
                    wordList.add(munchedString);
                }
                if (munchedNumber.length() > 0) {
                    System.out.println(munchedNumber);
                    wordList.add(munchedNumber);
                }
                System.out.println("\nIllegal character at " + position(currentLine, currentCharInLine) + ". Character is '" + charToMunch + "'.\nExiting program...");
                System.exit(0);
            }
            //Need to fix this logic because it allows appending of symbols that shouldn't append to each other
            if (Character.isLetter(TokenInfo.lastChar)) {
                System.out.println(munchedString);
                wordList.add(munchedString);
                munchedString = "";
            }
            else if (Character.isDigit(TokenInfo.lastChar)) {
                System.out.println(munchedNumber);
                wordList.add(munchedNumber);
                munchedNumber = "";
            }
            // =< logic
            if (charToMunch == '=' && TokenInfo.nextChar == '<') {
                munchedSymbol += charToMunch;
                munchedSymbol += TokenInfo.nextChar;
                System.out.println(munchedSymbol);
                wordList.add(munchedSymbol);
                munchedSymbol = "";
            }
            // >= logic
            if (charToMunch == '>' && TokenInfo.lastChar == '='){
                munchedSymbol += charToMunch;
                munchedSymbol += TokenInfo.nextChar;
                System.out.println(munchedSymbol);
                wordList.add(munchedSymbol);
                munchedSymbol = "";
            }
            // != logic
            if (charToMunch == '!' && TokenInfo.nextChar == '='){
                munchedSymbol += charToMunch;
                munchedSymbol += TokenInfo.nextChar;
                System.out.println(munchedSymbol);
                wordList.add(munchedSymbol);
                munchedSymbol = "";
            }
            // := logic
            if (charToMunch == ':' && TokenInfo.nextChar == '='){
                munchedSymbol += charToMunch;
                munchedSymbol += TokenInfo.nextChar;
                System.out.println(munchedSymbol);
                wordList.add(munchedSymbol);
                munchedSymbol = "";
            }
            // < logic
            if (charToMunch == '<' && TokenInfo.lastChar != '='){
                munchedSymbol += charToMunch;
                System.out.println(munchedSymbol);
                wordList.add(munchedSymbol);
                munchedSymbol = "";
            }
            // = logic
            if (charToMunch == '=' && TokenInfo.lastChar != '>' && TokenInfo.lastChar != '!' && TokenInfo.nextChar != '<' && TokenInfo.lastChar != ':'){
                munchedSymbol += charToMunch;
                System.out.println(munchedSymbol);
                wordList.add(munchedSymbol);
                munchedSymbol = "";
            }
            // > logic
            if (charToMunch == '>' && TokenInfo.nextChar != '='){
                munchedSymbol += charToMunch;
                System.out.println(munchedSymbol);
                wordList.add(munchedSymbol);
                munchedSymbol = "";
            }
            // +, -, *, /, (, ), ;, : logic
            if (charToMunch == '+' || charToMunch == '-' || charToMunch == '*' || charToMunch == '/' || charToMunch == '(' || charToMunch == ')' || charToMunch == ';' || charToMunch == ':'){
                munchedSymbol += charToMunch;
                if (charToMunch == ':' && TokenInfo.nextChar == '='){
                    munchedSymbol = "";
                }
                if (charToMunch == '=' && TokenInfo.lastChar == ':'){
                    munchedSymbol = "";
                }
                System.out.println(munchedSymbol);
                wordList.add(munchedSymbol);
                munchedSymbol = "";
            }
        }
        if (Character.isWhitespace(charToMunch)) {
            if (Character.isLetter(TokenInfo.lastChar) || munchedString.length() > 0) {
                System.out.println(munchedString);
                wordList.add(munchedString);
                munchedString = "";
            }
            if (Character.isDigit(TokenInfo.lastChar) || munchedNumber.length() > 0) {
                System.out.println(munchedNumber);
                wordList.add(munchedNumber);
                munchedNumber = "";
            }
            if (!Character.isLetter(TokenInfo.lastChar) && !Character.isDigit(TokenInfo.lastChar) && !Character.isWhitespace(TokenInfo.lastChar)) {
                System.out.println(munchedSymbol);
                wordList.add(munchedSymbol);
                munchedSymbol = "";
            }
        }
//        if (wordList.size() > 0){
//            return wordList.get(j);
//        }
        return NOTHINGWASMUNCHED;
    }
}
