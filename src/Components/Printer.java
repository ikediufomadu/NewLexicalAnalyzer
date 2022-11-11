package Components;

public class Printer {
    public static void printer(String  position, String munchedWord, String kindValue, String value) {
        if (!munchedWord.equals("") && !munchedWord.equals(" ")) {
            if (!kindValue.equals("'ID'") && !kindValue.equals("'NUM'")){
                System.out.println(position + " " + "'" + munchedWord + "'");
            }
            else {
                System.out.println(position + " " + kindValue + " " + value);
            }
        }
    }
}
