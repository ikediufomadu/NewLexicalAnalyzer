package Components;
import java.util.*;

public class GlobalVariables {
    static int currentLine = 0;
    static int currentCharInLine = 0;
    static HashMap<Integer, List<String>> wordsMap = new HashMap<>();
    static List<String> wordList = wordsMap.getOrDefault(currentLine, new ArrayList<>());
}
