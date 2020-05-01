import java.util.Scanner;

class Espelho {

    public static boolean isNotEnd(String entry) {
        return entry.length() != 3 || (entry.charAt(0) != 'F' && entry.charAt(1) != 'I' && entry.charAt(2) != 'M');
    }

    /**
     * Return the string sequence backwards
     * @param sequence
     * @return backwards sequence
     */
    public static String sequenceBackwards(String sequence) {
        String sequenceBackwards = "";
        for (int i = sequence.length()-1; i >= 0; i--) {
            sequenceBackwards += sequence.charAt(i);
        }
        return sequenceBackwards;
    }

    /**
     * Get a string with 2 numbers, and print the sequence of numbers between then. Then print a mirror of this sequence
     * @param entry
     */
    public static void printSequence(String entry) {

        String[] numbers = entry.split(" ");
        String sequence = "";

        int first = Integer.parseInt(numbers[0]);
        int last = Integer.parseInt(numbers[1]);

        for (int i = first; i <= last; i++) {
            sequence += Integer.toString(i);
        }

        sequence += sequenceBackwards(sequence);

        System.out.println(sequence);
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        String entry = "";

        do {
            
            entry = reader.nextLine();

            if (isNotEnd(entry)) {
                printSequence(entry);
            }

        } while (isNotEnd(entry));

        reader.close();
    }
}