package Components;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static Components.Driver.main;
import static Components.HelperFunctions.*;
import static Components.Next.next;
import static Components.ThreeMainFunctions.kind;

public class Reader {
    public static StringBuilder sb = new StringBuilder();

    public static void reader(String filenameToRead) throws IOException {
        /*
             File path is hardcoded. If you want to use this program without having a Test Cases folder
             remove "./Test Cases/" and make sure the file you want to run is at the same level as the src folder.
        */
        File f = new File("./Test Cases/" + filenameToRead);

        if (f.exists() && !f.isDirectory() && f.isFile() && f.canRead()) {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String checker;
            sb = new StringBuilder();

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
                //In case there is a comment at the end of the file this will allow it to be skipped without throwing an array out of bounds error
                sb.append(" ");
                next();
            }
            br.close();
            fr.close();

            kind(null);
        } else {
            System.out.println("The file name you entered does not exist within this program's directory. Please recheck.\n");
            main(new String[0]);
        }
    }
}
