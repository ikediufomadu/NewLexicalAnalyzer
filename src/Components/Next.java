package Components;

import static Components.ThreeMainFunctions.*;

public class Next {
    static int j = 0;
    //Gets next lexeme
    public static void next(char[] charHolder) {
        if (charHolder.length == 0) {
            return;
        }

        char charToMunch = charHolder[j];
        //TokenInfo t = new TokenInfo(, "");
        while (!Reader.finishedReading/*!kind(t).equals("end-of-text")*/) {
            maxMunch(charToMunch);
            j++;
            //printer();
            next(charHolder);
        }
    }
}