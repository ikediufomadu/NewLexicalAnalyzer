package Components;

import static Components.GlobalVariables.currentCharInLine;
import static Components.HelperFunctions.stringReset;
import static Components.Printer.printer;
import static Components.ThreeMainFunctions.*;

public class Next {
    static int j = 0;
    static String munchedWord;
    //Gets next lexeme
    public static void next(char[] charHolder, int currentLine, int arrayLength) {
        //On chance an empty array is passed we return
        if (charHolder.length == 0) {
            return;
        }
        char charToMunch = charHolder[j];

        //Used in the ThreeMainFunctions file to find the next char
        TokenInfo nextChar = new TokenInfo(charHolder, j);

        //munchedWord is the string passed by the maxMunch function
        TokenInfo t = new TokenInfo(munchedWord = maxMunch(charToMunch, currentLine, arrayLength, j));

        if (munchedWord != null) {
            //System.out.println(munchedWord + " hey");
            stringReset();
        }
        j++;
        while (!kind(t).equals("end-of-text") && j <= charHolder.length - 1) {
            //printer(kind(t));
            next(charHolder, currentLine, arrayLength);
        }
    }
}