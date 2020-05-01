class Tp01q12 {

    public static boolean isNotEnd(String entry) {
        return !(entry.length() >= 3 && entry.charAt(0) == 'F' && entry.charAt(1) == 'I' && entry.charAt(2) == 'M');
    }

    public static char encode(char letter) {
        int key = 3;
        return (char) (letter + key);
    }

    public static String cesarEncrypt(String entry, int index) {
        String result = "";
        if (index < entry.length()) {
            result += encode(entry.charAt(index)) + cesarEncrypt(entry, index+1);
        }
        return result;
    }

    public static String cesarEncrypt(String entry) {
        return cesarEncrypt(entry, 0);
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