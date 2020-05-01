class Tp01q01 {

    public static boolean isNotEnd(String entry) {
        return !(entry.length() >= 3 && entry.charAt(0) == 'F' && entry.charAt(1) == 'I' && entry.charAt(2) == 'M');
    }

    public static String removeSeparators(String entry) {
        String entryWithoutSeparators = "";
        for (int i = 0; i < entry.length(); i++) {
            if (entry.charAt(i) != ' ' || 
                entry.charAt(i) != ',' || 
                entry.charAt(i) != '-' || 
                entry.charAt(i) != '.' || 
                entry.charAt(i) != '/' || 
                entry.charAt(i) != '(' || 
                entry.charAt(i) != ')' || 
                entry.charAt(i) != '"' || 
                entry.charAt(i) != ':')

                entryWithoutSeparators += entry.charAt(i);
        }
        return entryWithoutSeparators;
    }

    /**
     * Método que verifica se uma string é um palíndromo
     * @param entry
     * @return SIM ou NÃO
     */
    public static String isPalindrome(String entry) {
        int i = 0;
        int j = entry.length()-1;
        String result = "SIM";

        for (; i < entry.length(); i++, j--) {
            if (entry.charAt(i) != entry.charAt(j)) {
                result = "NAO";
                i = entry.length(); // sai do for
            }
        }

        return result;
    }

    public static void main(String args[]) {
        String entry;

        do {

            entry = MyIO.readLine();

            if  (isNotEnd(entry)) {
                entry = removeSeparators(entry);
                MyIO.println(
                    isPalindrome(entry)
                );
            }
            
        } while (isNotEnd(entry));

    }
    
}