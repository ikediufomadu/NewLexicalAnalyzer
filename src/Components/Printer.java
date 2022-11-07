package Components;

import java.util.List;
import java.util.Map;

import static Components.GlobalVariables.*;
import static Components.HelperFunctions.*;
import static Components.ThreeMainFunctions.*;

public class Printer {
    public static void printer(String munchedWord, String kindValue) {
        System.out.println("Make sure to implement this correctly after you're done with everything else");
        return;
        /*for (Map.Entry<Integer, List<String>> entry : wordsMap.entrySet()) {
            int lineNum = entry.getKey();
            int col = 0;
            List<String> wordList = entry.getValue();
            for (String word : wordList) {
                col += word.length();
                if (!word.equals("")) {
                    TokenInfo tokenInfo = getTokenInfo(word);
                    if (tokenInfo != null) {
                        System.out.println(position(lineNum, col) + ": " + kind(tokenInfo) + " " + value(tokenInfo));
                    }
                }
            }
        }*/
    }
}
