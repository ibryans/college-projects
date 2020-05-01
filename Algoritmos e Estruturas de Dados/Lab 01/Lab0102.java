import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author bryans
 */
class Lab0102 {

    public static boolean isUpperLetter(char letter) {
        return (letter >= 'A' && letter <= 'Z');
    }

    public static boolean isNotEnd(String entry) {
        if (entry.length() < 3) 
            return true;
        else
            return (entry.charAt(0) != 'F' && entry.charAt(1) != 'I' && entry.charAt(2) != 'M');
    }

    public static int howManyUpperLetters(String entry, int index) {
        int upperLetters = 0;
        
        if (index < entry.length()) {
            if (isUpperLetter(entry.charAt(index)))
                upperLetters = 1 + howManyUpperLetters(entry, index+1);
            else
                upperLetters += howManyUpperLetters(entry, index+1);
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
                    howManyUpperLetters(entry, 0)
                );
            }

        } while (isNotEnd(entry));

        reader.close();
    }

}