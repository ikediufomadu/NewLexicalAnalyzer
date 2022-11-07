package Components;

import static Components.Printer.printer;
import static Components.ThreeMainFunctions.*;

public class Next {
    static int j = 0;
    static String munchedWord;
    //Gets next lexeme
    public static void next(char[] charHolder, int currentLine) {
        if (charHolder.length == 0) {
            return;
        }
        char charToMunch = charHolder[j];
        TokenInfo nextChar = new TokenInfo(charHolder, j);
        if (j != charHolder.length - 1) {
            //System.out.println(charToMunch + " " + j);
            munchedWord = maxMunch(charToMunch, currentLine);
        }
        TokenInfo t = new TokenInfo(munchedWord);
        while (!kind(t).equals("end-of-text") && j < charHolder.length - 1) {
            j++;
            //printer(kind(t));
            next(charHolder, currentLine);
        }
    }
}