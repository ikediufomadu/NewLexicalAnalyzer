package Components;

import java.io.IOException;

import static Components.GlobalVariables.currentCharInLine;
import static Components.GlobalVariables.currentLine;
import static Components.HelperFunctions.*;
import static Components.Printer.printer;
import static Components.Reader.sb;
import static Components.ThreeMainFunctions.*;

public class Next {
    static int j = 0;
    public static String munchedWord = "";
    //Gets next lexeme
    public static void next() throws IOException {
        //On chance an empty array is passed we return
        if (stringToChar(sb).length == 0) {
            return;
        }
        char charToMunch = stringToChar(sb)[j];

        //Used in the ThreeMainFunctions file to find the next char
        TokenInfo nextChar = new TokenInfo(stringToChar(sb), j);

        munchedWord = maxMunch(charToMunch, currentLine);
        if (munchedWord != null) {
            //Prints characters attached and before an unaccepted symbol
            if (wrongInput) {
                printer(currentLine, munchedWord, kind(munchedWord), value(munchedWord));
                System.out.println("\nIllegal character at " + position(currentLine, currentCharInLine) + ". Character is '" + charToMunch + "'.\nExiting program...");
                System.exit(0);
            }

            printer(currentLine, munchedWord, kind(munchedWord), value(munchedWord));
            stringReset();

            if (symbolNext) {
                munchedWord = String.valueOf(TokenInfo.currentChar);
                printer(currentLine, munchedWord, kind(munchedWord), value(munchedWord));
                stringReset();
            }
        }
        j++;
        while (!TokenInfo.currentKeyword.equals("end-of-text")  && j <= stringToChar(sb).length - 1) {
            next();
        }
    }
}