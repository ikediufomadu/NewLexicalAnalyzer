package Components;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static Components.Driver.main;
import static Components.GlobalVariables.*;
import static Components.Next.next;
import static Components.ThreeMainFunctions.kind;

public class Reader {
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
                //Accounts for last index in string, adds a space after it allowing the 'next' function to work with all strings
                checker += " ";
                for (int i = 0; i < checker.length(); i++) {
                    char c = checker.charAt(i);
                    //Skips code with comments
                    if (i + 1 < checker.length() && c == '/' && checker.charAt(i + 1) == '/') break;
                    sb.append(c);
                }
                next(sb.toString().toCharArray(), currentLine);

            }
            if (br.readLine() == null) {
                kind(new TokenInfo(null));
            }
            br.close();
            fr.close();
        } else {
            System.out.println("The file name you entered does not exist within this program's directory. Please recheck.\n");
            main(new String[0]);
        }
    }
}