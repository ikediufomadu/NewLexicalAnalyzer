package Components;

import static Components.Printer.printer;
import static Components.ThreeMainFunctions.*;

public class Next {
    static int j = 0;
    //Gets next lexeme
    public static void next(char[] charHolder) {
        if (charHolder.length == 0) {
            return;
        }
        char charToMunch = charHolder[j];

        String munchedWord = maxMunch(charToMunch);

        TokenInfo t = new TokenInfo(munchedWord);

        while (!kind(t).equals("end-of-text")) {
            if (j == charHolder.length - 1) {
                return;
            }
            j++;
            //printer(munchedWord, kind(t));
            next(charHolder);
        }
    }
}