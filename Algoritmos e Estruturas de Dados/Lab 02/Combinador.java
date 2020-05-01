import java.util.Scanner;

class Combinador {

    public static boolean isNotEnd(String entry) {
        return (entry.length() != 3) || 
            entry.charAt(0) != 'F'
            && entry.charAt(1) != 'I'
            && entry.charAt(2) != 'M';
    }

    public static String combineStrings(String first, String second) {
        
        String result = "";
        int i = 0;

        while (i >= 0) {
            if (i < first.length()) {
                result += first.charAt(i);
                if (i < second.length()) {
                    result += second.charAt(i);
                }
                i++;
            } else if (i < second.length()) {
                result += second.charAt(i);
                i++;
            } else { 
                i = -1; 
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        String entry1; 
        String entry2;

        do {
            
            entry1 = reader.next();

            if (isNotEnd(entry1)) {
                entry2 = reader.next();
                System.out.println(combineStrings(entry1, entry2));
            }

        } while (isNotEnd(entry1));

        reader.close();

    }

}