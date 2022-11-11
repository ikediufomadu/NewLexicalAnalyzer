package Components;

import java.io.IOException;

import static Components.Driver.main;
import static Components.GlobalVariables.*;
import static Components.ThreeMainFunctions.*;

public class HelperFunctions {
    //Report syntax errors
    public static boolean reportLexicalError(char c) {
        if (c == '!' && TokenInfo.nextChar == '=') {
            return false;
        }
        return c == '@' || c == '!' || c == '#' || c == '$' || c == '%' || c == '^' || c == '&' || c == '`' || c == '~' || c == ',' || c == '\"' || c == '?' || c == '\'' || c == '[' || c == ']' || c == '{' || c == '}';
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
    }
    public static void stringReset(){
        munchedString = "";
        munchedNumber = "";
        munchedSymbol = "";
    }
    public static String position(int currentLine, int currentCharInLine) {
        return (currentLine) + ":" + (currentCharInLine);
    }
}
