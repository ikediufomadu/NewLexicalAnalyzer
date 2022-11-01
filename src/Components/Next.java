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

            while (!kind().equals("end-of-text")) {
                printer();
                next(charHolder);
            }
            return;
        }
    }
}