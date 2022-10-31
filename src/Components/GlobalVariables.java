package Components;
import java.util.*;

public class GlobalVariables {
     static int currentLine = 0;
     static int currentCharInLine = 0;
     static HashMap<Integer, List<String>> wordsMap = new HashMap<>();
     static final HashMap<String, HashSet<String>> map = new HashMap() {
        {
            put("keyword", new HashSet<String>() {{
                add("program");
                add(":");
                add(";");
                add("bool");
                add("int");
            }});
            put("symbol", new HashSet<String>() {{
                add("<");
                add("<=");
                add("=");
                add("!=");
                add(">=");
                add(">");
                add("+");
                add("-");
                add("*");
                add("/");
                add(":=");
                add("(");
                add(")");
            }});
            put("end", new HashSet<String>() {{
                add("end-of-file");
            }});
        }
    };
}
