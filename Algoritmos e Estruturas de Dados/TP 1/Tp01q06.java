class Tp01q06 {

    public static boolean isNotEnd(String entry) {
        return !(entry.length() >= 3 && entry.charAt(0) == 'F' && entry.charAt(1) == 'I' && entry.charAt(2) == 'M');
    }

    public static boolean isOnlyVowels(String entry) {
        boolean result = true;
        for (int i = 0; i < entry.length(); i++) {
            char l = entry.charAt(i);
            if ((l >= 'A' && l <= 'Z') || (l >= 'a' && l <= 'z')) {
                if ((l != 'a' && l != 'e' && l != 'i' && l != 'o' && l != 'u') &&
                    (l != 'A' && l != 'E' && l != 'I' && l != 'O' && l != 'U'))
                    result = false;
            } else
                result = false;
        }
        return result;
    }

    public static boolean isOnlyConsonants(String entry) {
        boolean result = true;
        for (int i = 0; i < entry.length(); i++) {
            char l = entry.charAt(i);
            if ((l >= 'A' && l <= 'Z') || (l >= 'a' && l <= 'z')) {
                if ((l == 'a' || l == 'e' || l == 'i' || l == 'o' || l == 'u') || 
                    (l == 'A' || l == 'E' || l == 'I' || l == 'O' || l == 'U'))
                    result = false;
            } else
                result = false;
        }
        return result;
    }

    public static boolean isIntNumber(String entry) {
        boolean result = true;
        for (int i = 0; i < entry.length(); i++) {
            char l = entry.charAt(i);
            if (l < '0' || l > '9')
                result = false;
        }
        return result;
    }

    public static boolean isRealNumber(String entry) {
        boolean result = true;
        int dotsOrCommas = 0;
        for (int i = 0; i < entry.length(); i++) {
            char l = entry.charAt(i);
            if (dotsOrCommas < 2 && result) {
                if ((l < '0' || l > '9')) {
                    if (l == '.' || l == ',')
                        dotsOrCommas++;
                    else
                        result = false;
                }
            } else
                result = false;
        }
        return result;
    }

    public static void main(String[] args) {
        String entry = "";

        do {
            
            entry = MyIO.readLine();

            if (isNotEnd(entry)) {
                String onlyVowels = (isOnlyVowels(entry))
                ? "SIM " : "NAO ";
                String onlyConsonants = (isOnlyConsonants(entry))
                ? "SIM " : "NAO ";
                String isInt = (isIntNumber(entry))
                ? "SIM " : "NAO ";
                String isReal = (isRealNumber(entry))
                ? "SIM" : "NAO";

                MyIO.println(
                    onlyVowels + 
                    onlyConsonants + 
                    isInt + 
                    isReal
                );
            }

        } while (isNotEnd(entry));
    }
}