package Components;

import java.io.IOException;

import static Components.Driver.main;
import static Components.GlobalVariables.*;
import static Components.ThreeMainFunctions.*;

public class HelperFunctions {
    static String lostChar = "";
    //Report syntax errors
    public static boolean reportLexicalError(char c) {
        if (c == '!' && TokenInfo.nextChar == '=') {
            return false;
        }
        return c == '@' || c == '!' || c == '#' || c == '$' || c == '%' || c == '^' || c == '&' || c == '`' || c == '~' || c == ',' || c == '\"' || c == '?' || c == '\'' || c == '[' || c == ']' || c == '{' || c == '}' || c == '.';
    }

    //Reruns program after successful tokenization of a file
    public static void sequenceKeepRunning() {
        try {
            reset();
            main(new String[0]);
        } catch (IOException e) {
            System.out.println("Could not continue program...quitting program");
            System.exit(0);
        }
    }

    //Reset variables to analyze next file
    private static void reset() {
        currentLine = 0;
        currentCharInLine = 0;
        Next.j = 0;
    }
    public static void stringReset(){
        if (munchedString.length() == 1) {
            if (Character.isLetter(TokenInfo.currentChar)){
                if (Character.isLetter(TokenInfo.nextChar)) {
                    lostChar = String.valueOf(TokenInfo.currentChar);
                }
            }
        }
        munchedString = "";
        munchedNumber = "";
        munchedSymbol = "";
    }
    public static String position(int currentLine, int currentCharInLine) {
        return (currentLine) + ":" + (currentCharInLine);
    }
    public static char[] stringToChar (StringBuilder sb) {
        return sb.toString().toCharArray();
    }
    public static void printer(int  currentLine, String munchedWord, String kindValue, String value) {
        int intPassed = 1;

        for (int i = 1; i < munchedWord.length(); i++) {
            if (i == munchedWord.length() - 1) {
                intPassed = i;
            }
        }
        if (!munchedWord.equals("") && !munchedWord.equals(" ") && !munchedWord.contains("\t")) {
            if (!kindValue.equals("'ID'") && !kindValue.equals("'NUM'")){
                System.out.println(position(currentLine, intPassed) + " " + "'" + munchedWord + "'");
            }
            else {
                System.out.println(position(currentLine, intPassed) + " " + kindValue + " " + value);
            }
        }
    }
}
