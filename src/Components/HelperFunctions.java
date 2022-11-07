package Components;

import java.io.IOException;

import static Components.Driver.main;
import static Components.GlobalVariables.*;

public class HelperFunctions {
    //Report syntax errors
    public static void reportLexicalError(char c, int currentLine, int currentCharInLine) {
        if (c == '@' || c == '!' || c == '#' || c == '$' || c == '%' || c == '^' || c == '&' || c == '`' || c == '~' || c == ',' || c == '\"' || c == '?' || c == '\'' || c == '[' || c == ']') {
            System.out.println("\nIllegal character at " + position(currentLine, currentCharInLine) + ". Character is '" + c + "'.\nExiting program...");
            System.exit(0);
        }
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

    public static String position(int currentLine, int currentCharInLine) {
        return (currentLine) + ":" + (currentCharInLine);
    }
}
