package Components;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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

    //Check reserved keywords
    public static TokenInfo getTokenInfo(String input) {
        for (Map.Entry<String, HashSet<String>> entry : map.entrySet()) {
            String tokenVal = entry.getKey();
            HashSet<String> set = entry.getValue();

            //Checks if input is in set
            if (set.contains(input.toLowerCase())) {
                return new TokenInfo(tokenVal, input);
            }
        }

        // check if integer
        try {
            int num = Integer.parseInt(input);
            return new TokenInfo("integer", input);
        } catch (Exception e) {
        }

        // check if identifier (character)
        if (input.length() == 1 || input.equals("expression")) {
            if (Character.isLetter(input.charAt(0))) {
                return new TokenInfo("identifiers", input);
            }
        }
        if (input.equals("end")) {
            return new TokenInfo("end-of-file", input);
        }

        if (input.length() > 1) {
            return new TokenInfo("program_name", input);
        }

        return null;
    }
}
