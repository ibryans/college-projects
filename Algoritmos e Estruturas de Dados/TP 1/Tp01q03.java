class Tp01q03 {

    public static boolean isNotEnd(String entry) {
        return !(entry.length() >= 3 && entry.charAt(0) == 'F' && entry.charAt(1) == 'I' && entry.charAt(2) == 'M');
    }

    public static char encode(char letter) {
        int key = 3;
        return (char) (letter + key);
    }

    public static String cesarEncrypt(String entry) {
        String result = "";

        for (int i = 0; i < entry.length(); i++) {
            result += encode(entry.charAt(i));
        }

        return result;
    }

    public static void main(String args[]) {
        String entry = "";

        do {
            entry = MyIO.readLine();

            if (isNotEnd(entry)) {
                MyIO.println(cesarEncrypt(entry));
            }
            
        } while (isNotEnd(entry));

    }
}