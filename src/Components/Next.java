package Components;

import static Components.Printer.printer;
import static Components.ThreeMainFunctions.*;

public class Next {
    static int j = 0;
    //Gets next lexeme
    public static void next(char[] charHolder, int currentLine) {
        if (charHolder.length == 0) {
            return;
        }
        TokenInfo nextChar = new TokenInfo(charHolder, j);

        char charToMunch = charHolder[j];

        String munchedWord = maxMunch(charToMunch, currentLine, nextChar);

        TokenInfo t = new TokenInfo(munchedWord);

        while (!kind(t).equals("end-of-text")) {
            if (j == charHolder.length - 1) {
                return;
            }
            j++;
            //printer(munchedWord, kind(t));
            next(charHolder, currentLine);
        }
    }
}