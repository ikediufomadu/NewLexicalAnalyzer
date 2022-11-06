package Components;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static Components.Driver.main;
import static Components.GlobalVariables.*;
import static Components.HelperFunctions.reportLexicalError;
import static Components.Next.next;

public class Reader {
    public static boolean finishedReading = false;
    public static void reader(String filenameToRead) throws IOException {
        File f = new File(filenameToRead);
        if (f.exists() && !f.isDirectory() && f.isFile() && f.canRead()) {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String checker;
            StringBuilder sb = new StringBuilder();
            //Reads by line
            while ((checker = br.readLine()) != null) {
                currentLine++;
                for (int i = 0; i < checker.length(); i++) {
                    currentCharInLine++;
                    char c = checker.charAt(i);

                    //Skips code with comments
                    if (i + 1 < checker.length() && c == '/' && checker.charAt(i + 1) == '/') break;
                    //reportLexicalError(c, currentLine, currentCharInLine);
                    sb.append(c);
                }
                if (br.readLine() == null) {finishedReading = true;}
                next(sb.toString().toCharArray());
                currentCharInLine = 0;
            }
            br.close();
            fr.close();
        } else {
            System.out.println("The file name you entered does not exist within this program's directory. Please recheck.\n");
            main(new String[0]);
        }
    }
}