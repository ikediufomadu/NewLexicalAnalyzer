package Components;

import static Components.HelperFunctions.*;
import static Components.GlobalVariables.*;
import static Components.Position.position;

public class ThreeMainFunctions {
    //Get kind of lexeme
    public static String kind(TokenInfo t) {
        switch (t.currentKeyword) {
            case "identifiers" -> t.currentKeyword = "'ID'";
            case "integer" -> t.currentKeyword = "'NUM'";
            case "keyword", "symbol" -> {
                return "'" + t.currentTokenValue + "'";
            }
            case "program_name" -> {
                return t.currentTokenValue;
            }
            case "end-of-file" -> {
                t.currentKeyword = "end-of-text";
                System.out.println("Reached the end of the file.\n...\nResetting program\n\n");
                sequenceKeepRunning();
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

    //Gets next lexeme
    public static void next(char[] charArray, int currentLine) {
        StringBuilder newWord = new StringBuilder();
        List<String> wordList = wordsMap.getOrDefault(currentLine, new ArrayList<>());
        for (int i = 0; i < charHolder.length; i++) {
            char letter = charHolder[i];

            // When we reach the end
            if ((letter == ':' || letter == ';') && i == charHolder.length - 1) {
                if (!newWord.toString().equals("")) wordList.add(newWord.toString());
                wordList.add(charHolder[i] + "");
                wordsMap.put(currentLine, wordList);
                newWord = new StringBuilder();
                break;
            }

            // Return if we see a comment, ignore the rest

            if (shouldAddWord(letter)) {
                performAddWord(newWord.toString(), wordList);
                wordList.add(letter + "");
                newWord = new StringBuilder();
            } else {
                newWord.append(letter);
            }
            wordsMap.put(currentLine, wordList);
        }
        if (!newWord.isEmpty()) {
            performAddWord(newWord.toString(), wordList);
            wordsMap.put(currentLine, wordList);
        }
        while (!kind().equals("end-of-text")) {
            System.out.println(position(), kind(), value() );
            next();
        }
    }
}
