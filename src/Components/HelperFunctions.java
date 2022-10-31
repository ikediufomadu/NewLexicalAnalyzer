package Components;

import java.io.IOException;
import java.util.List;

import static Components.Driver.main;
import static Components.GlobalVariables.*;

public class HelperFunctions implements Position {
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

    static boolean shouldAddWord(char letter) {
        return letter == ' ' || letter == '(' || letter == ')' || letter == '_' || letter == '/';
    }

    public static void performAddWord(String newWord, List<String> wordList) {
        String wordToAdd = newWord;
        if (wordToAdd.equals("print")) {
            wordList.add(wordToAdd);
            return;
        }

        int index = getKeywordStartingIndex(newWord);

        // Split the string int two parts if a keyword is in there
        if (index != -1) {
            String firstWord = wordToAdd.substring(0, index);
            if (!firstWord.equals("")) wordList.add(firstWord);
            wordToAdd = wordToAdd.substring(index);
        }

        if (!wordToAdd.equals("")) wordList.add(wordToAdd);
    }

    // returns the index of where the word starts
    public static int getKeywordStartingIndex(String word) {
        for (String keyword : map.get("keyword")) {
            if (word.contains(keyword)) {
                return word.indexOf(keyword);
            }
        }
        return -1;
    }
}
