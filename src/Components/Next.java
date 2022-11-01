package Components;

import java.util.ArrayList;
import java.util.List;

import static Components.GlobalVariables.*;
import static Components.HelperFunctions.*;
import static Components.Position.position;
import static Components.Printer.printer;
import static Components.TwoMainFunctions.kind;

public class Next {
    //Gets next lexeme
    public static void next(char[] charHolder) {
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
            if (!newWord.isEmpty()) {
                performAddWord(newWord.toString(), wordList);
                wordsMap.put(currentLine, wordList);
            }
            while (!kind(t.currentKeyword).equals("end-of-text")) {
                printer();
                next(charHolder);
            }
        }
    }
}