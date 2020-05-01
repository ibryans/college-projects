import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author bryans
 */
class Lab01 {

    public static boolean isUpperLetter(char letter) {
        return (letter >= 'A' && letter <= 'Z');
    }

    public static boolean isNotEnd(String entry) {
        return (entry.length() < 3 || entry.charAt(0) != 'F' && entry.charAt(1) != 'I' && entry.charAt(2) != 'M');
    }

    public static int howManyUpperLetters(String entry) {
        int upperLetters = 0;

        for (int i = 0; i < entry.length(); i++) {
            if (isUpperLetter(entry.charAt(i)))
                upperLetters++;
        }

        return upperLetters;
    }

    public static void main(String args[]) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String entry;

        do {
            entry = reader.readLine();

            if (isNotEnd(entry)) {
                System.out.println(
                    howManyUpperLetters(entry)
                );
            }

        } while (isNotEnd(entry));

        reader.close();

    }

}