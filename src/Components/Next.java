package Components;

import static Components.GlobalVariables.currentCharInLine;
import static Components.HelperFunctions.stringReset;
import static Components.Printer.printer;
import static Components.ThreeMainFunctions.*;

public class Next {
    static int j = 0;
    static String munchedWord;
    static char firstIndex;
    //Gets next lexeme
    public static void next(char[] charHolder, int currentLine) {
        if (charHolder.length == 0) {
            return;
        }
        char charToMunch = charHolder[j];

        if (Character.isLetter(charHolder[j]) && charHolder[j] == charHolder[0]) {
            firstIndex = charHolder[0];
        }
        else if (Character.isWhitespace(charHolder[j]) && charHolder[j + 1] < charHolder.length){
            firstIndex = charHolder[j + 1];
        }

        //Used in the ThreeMainFunctions file to find the next char
        TokenInfo nextChar = new TokenInfo(charHolder, j);
        if (j != charHolder.length - 1) {
            munchedWord = maxMunch(charToMunch, currentLine, firstIndex);
        }
        if (munchedWord != null) {
            //System.out.println(munchedWord + " hey");
            stringReset();
        }
        TokenInfo t = new TokenInfo(munchedWord);
        while (!kind(t).equals("end-of-text") && j < charHolder.length - 1) {
            j++;
            //printer(kind(t));
            next(charHolder, currentLine);
        }
    }
}