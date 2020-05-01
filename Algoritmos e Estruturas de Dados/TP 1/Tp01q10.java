class Tp01q10 {

    public static boolean isNotEnd(String entry) {
        return !(entry.length() >= 3 && entry.charAt(0) == 'F' && entry.charAt(1) == 'I' && entry.charAt(2) == 'M');
    }

    public static String removeSeparators(String entry) {
        String entryWithoutSeparators = "";
        for (int i = 0; i < entry.length(); i++) {
            // Se a letra for um caracter válido (sem ser espaços, virgula, ponto, etc...)
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
    public static void isPalindrome(String entry) {
        boolean result = isPalindrome(entry, 0, entry.length()-1);

        if (result) 
            MyIO.println("SIM");
        else 
            MyIO.println("NAO");
    }

    public static boolean isPalindrome(String entry, int first, int last) {
        boolean result = false;

        if (first < entry.length()-1) {
            if (entry.charAt(first) == entry.charAt(last)) {
                result = isPalindrome(entry, first+1, last-1);
            }
        } else {
            if (entry.charAt(first) == entry.charAt(last)) {
                result = true;
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
                isPalindrome(entry);
            }
            
        } while (isNotEnd(entry));

    }
    
}