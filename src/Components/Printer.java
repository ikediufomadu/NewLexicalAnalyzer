package Components;

import java.util.List;
import java.util.Map;

import static Components.HelperFunctions.*;
import static Components.ThreeMainFunctions.*;

public class Printer {
    for (Map.Entry<Integer, List<String>> entry : wordsMap.entrySet()) {
        int lineNum = entry.getKey();
        int col = 0;
        List<String> wordList = entry.getValue();
        for (String word : wordList) {
            col += word.length();
            if (!word.equals("")) {
                for (int i = 0; i < word.length(); i++) {
                    reportLexicalError(word.charAt(i), lineNum, col);
                }
                TokenInfo tokenInfo = getTokenInfo(word);
                if (tokenInfo != null) {
                    System.out.println(position(lineNum, col) + ": " + kind(tokenInfo) + " " + value(tokenInfo));
                }
            }
        }
    }
}
