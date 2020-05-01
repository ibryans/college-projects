class Tp01q15 {

    public static boolean isNotEnd(String entry) {
        return !(entry.length() >= 3 && entry.charAt(0) == 'F' && entry.charAt(1) == 'I' && entry.charAt(2) == 'M');
    }

    public static boolean isOnlyVowels(String entry, int index) {
        boolean result = true;
        if (index < entry.length()) {
            char l = entry.charAt(index);
            if ((l >= 'A' && l <= 'Z') || (l >= 'a' && l <= 'z')) {
                if ((l != 'a' && l != 'e' && l != 'i' && l != 'o' && l != 'u') &&
                    (l != 'A' && l != 'E' && l != 'I' && l != 'O' && l != 'U'))
                    result = false;
                else
                    result = isOnlyVowels(entry, index+1);
            } else {
                result = false;
            }
        }
        return result;
    }

    public static boolean isOnlyVowels(String entry) {
        return isOnlyVowels(entry, 0);
    }

    public static boolean isOnlyConsonants(String entry, int index) {
        boolean result = true;
        if (index < entry.length()) {
            char l = entry.charAt(index);

            if ((l >= 'A' && l <= 'Z') || (l >= 'a' && l <= 'z')) {
                if ((l == 'a' || l == 'e' || l == 'i' || l == 'o' || l == 'u') || 
                    (l == 'A' || l == 'E' || l == 'I' || l == 'O' || l == 'U'))
                    result = false;
                else
                    result = isOnlyConsonants(entry, index+1);
            } else {
                result = false;
            }
        }
        return result;
    }

    public static boolean isOnlyConsonants(String entry) {
        return isOnlyConsonants(entry, 0);
    }

    public static boolean isIntNumber(String entry, int index) {
        boolean result = true;
        if (index < entry.length()) {
            char l = entry.charAt(index);
            if (l < '0' || l > '9')
                result = false;
            else
                result = isIntNumber(entry, index+1);
        }
        return result;
    }

    public static boolean isIntNumber(String entry) {
        return isIntNumber(entry, 0);
    }

    public static boolean isRealNumber(String entry, int index, int dotsOrCommas) {
        boolean result = true;
        if (index < entry.length()) {
            if (dotsOrCommas < 2) {
                char l = entry.charAt(index);
                if ((l < '0' || l > '9')) {
                    if (l == '.' || l == ',')
                        result = isRealNumber(entry, index+1, dotsOrCommas+1);
                    else
                        result = false;
                } else {
                    result = isRealNumber(entry, index+1, dotsOrCommas);
                }
            } else {
                result = false;
            }
        }
        return result;
    }

    public static boolean isRealNumber(String entry) {
        return isRealNumber(entry, 0, 0);
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