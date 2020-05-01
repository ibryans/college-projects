import java.util.Random;

class Tp01q04 {

    public static boolean isNotEnd(String entry) {
        return !(entry.length() >= 3 && entry.charAt(0) == 'F' && entry.charAt(1) == 'I' && entry.charAt(2) == 'M');
    }

    public static String randomChange(String entry, char first, char second) {
        
        String result = "";

        for (int i = 0; i < entry.length(); i++) {
            if (entry.charAt(i) == first)
                result += second;
            else
                result += entry.charAt(i);
        }

        return result;
    }

    public static void main(String args[]) {
        Random gerador = new Random();
        gerador.setSeed(4);

        char firstRandomLetter;
        char secondRandomLetter;

        String entry = "";

        do {
            
            entry = MyIO.readLine();

            if (isNotEnd(entry)) {
                firstRandomLetter = (char) ('a' + Math.abs(gerador.nextInt() % 26));
                secondRandomLetter = (char) ('a' + Math.abs(gerador.nextInt() % 26));
                MyIO.println(randomChange(entry, firstRandomLetter, secondRandomLetter));
            }

        } while (isNotEnd(entry));

    }
}