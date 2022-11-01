package Components;

import java.util.ArrayList;
import java.util.List;

import static Components.GlobalVariables.*;
import static Components.HelperFunctions.*;
import static Components.Position.position;
import static Components.Printer.printer;
import static Components.TwoMainFunctions.kind;

public class Next {
    //Gets next lexeme
    public static void next(char[] charHolder) {
        String newWord = "";
        boolean num = false;
        int j = 0;
        for (int i = 0; i < charHolder.length; i++){
            if (i+1 < charHolder.length && (Character.isDigit(charHolder[i]) && Character.isDigit(charHolder[i+1]))) {
                newWord += charHolder[i];
                num = true;
            }
        }
        if (num = false) {
            newWord += charHolder[j];
        }
        TokenInfo t = getTokenInfo(newWord);
        while (!kind(t).equals("end-of-text") || j < charHolder.length) {
            j++;
            printer();
            next(charHolder);
        }
        return;
    }
}