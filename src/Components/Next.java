package Components;

import static Components.GlobalVariables.currentCharInLine;
import static Components.HelperFunctions.position;
import static Components.HelperFunctions.stringReset;
import static Components.Printer.printer;
import static Components.ThreeMainFunctions.*;

public class Next {
    static int j = 0;
    static String munchedWord;
    //Gets next lexeme
    public static void next(char[] charHolder, int currentLine) {
        //On chance an empty array is passed we return
        if (charHolder.length == 0) {
            return;
        }
        char charToMunch = charHolder[j];

        //Used in the ThreeMainFunctions file to find the next char
        TokenInfo nextChar = new TokenInfo(charHolder, j);

        munchedWord = maxMunch(charToMunch, currentLine);
        //pass wordlist string to kind
        if (munchedWord != null) {
            if (wrongInput) {
                printer(position(currentLine, currentCharInLine), munchedWord, kind(munchedWord), value(munchedWord));
                System.out.println("\nIllegal character at " + position(currentLine, currentCharInLine) + ". Character is '" + charToMunch + "'.\nExiting program...");
                System.exit(0);
            }
            //Method that takes J and reader's char array and will return j and reset J once it reaches the end of the char array
            printer(position(currentLine, currentCharInLine), munchedWord, kind(munchedWord), value(munchedWord));
            stringReset();

            if (symbolNext) {
                munchedWord = String.valueOf(TokenInfo.currentChar);
                printer(position(currentLine, currentCharInLine), munchedWord, kind(munchedWord), value(munchedWord));
                stringReset();
            }
        }
        j++;
        while (/*!kind(munchedWord).equals("end-of-text") &&*/ j <= charHolder.length - 1) {
            next(charHolder, currentLine);
        }
    }
}