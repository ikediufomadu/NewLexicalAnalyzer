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
        while (j < charHolder.length-1 /*!kind(t).equals("end-of-text")*/) {
            j++;
            maxMunch(charToMunch);
            next(charHolder);
        }
    }
}