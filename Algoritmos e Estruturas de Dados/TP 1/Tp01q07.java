import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

class Tp01q07 {

    public static boolean isNotEnd(String entry) {
        return !(entry.length() >= 3 && entry.charAt(0) == 'F' && entry.charAt(1) == 'I' && entry.charAt(2) == 'M');
    }

    public static boolean isNotVowel(char letter) {
        return (
            letter != 'a' && letter != 160 && letter != 133 && letter != 131 && letter != 198 &&
            letter != 'e' && letter != 130 && letter != 138 && letter != 136 &&
            letter != 'i' && letter != 161 && letter != 141 && letter != 140 &&
            letter != 'o' && letter != 162 && letter != 148 && letter != 147 && letter != 228 &&
            letter != 'u' && letter != 163 && letter != 151 && letter != 150
        );
    }

    /**
     * Calcula o numero das vogais e printa na tela
     * 
     * @param leitor
     * @throws IOException
     */
    public static void numberOfVogals(BufferedReader leitor) throws IOException {

        String txt = "";
        int numOfA = 0;
        int numOfE = 0;
        int numOfI = 0;
        int numOfO = 0;
        int numOfU = 0;

        do {
            
            try {
				txt = leitor.readLine();
			} catch (IOException e) {                
                e.printStackTrace();
            }


            if (txt != null) {

                for (int i = 0; i < txt.length(); i++) {
                    
                    if (txt.charAt(i) == 'a' || txt.charAt(i) == 'A')
                        numOfA++;
                    else if (txt.charAt(i) == 'e' || txt.charAt(i) == 'E')
                        numOfE++;
                    else if (txt.charAt(i) == 'i' || txt.charAt(i) == 'I')
                        numOfI++;
                    else if (txt.charAt(i) == 'o' || txt.charAt(i) == 'O')
                        numOfO++;
                    else if (txt.charAt(i) == 'u' || txt.charAt(i) == 'U')
                        numOfU++;

                }
            }

        } while(txt != null);

        MyIO.print("a(" + numOfA + ") ");
        MyIO.print("e(" + numOfE + ") ");
        MyIO.print("i(" + numOfI + ") ");
        MyIO.print("o(" + numOfO + ") ");
        MyIO.print("u(" + numOfU + ") ");

    }

    /**
     * Calcula o numero das vogais acentuadas e printa na tela
     * 
     * @param leitor
     * @throws IOException
     */
    public static void numberOfVogalsWithAccent(BufferedReader leitor) throws IOException {

        String txt = "";

        int acuteA = 0, craseA = 0, tilA = 0, circunflexA = 0;
        int acuteE = 0, craseE = 0, circunflexE = 0;
        int acuteI = 0, craseI = 0, circunflexI = 0;
        int acuteO = 0, craseO = 0, tilO = 0, circunflexO = 0;
        int acuteU = 0, craseU = 0, circunflexU = 0;

        do {
            
            try {
				txt = leitor.readLine();
			} catch (IOException e) {                
                e.printStackTrace();
            }


            if (txt != null) {

                for (int i = 0; i < txt.length(); i++) {
                    
                    if (txt.charAt(i) == 160)
                        acuteA++;
                    else if (txt.charAt(i) == 133)
                        craseA++;
                    else if (txt.charAt(i) == 198)
                        tilA++;
                    else if (txt.charAt(i) == 131)
                        circunflexA++;

                    else if (txt.charAt(i) == 130)
                        acuteE++;
                    else if (txt.charAt(i) == 138)
                        craseE++;
                    else if (txt.charAt(i) == 136)
                        circunflexE++;

                    else if (txt.charAt(i) == 161)
                        acuteI++;
                    else if (txt.charAt(i) == 141)
                        craseI++;
                    else if (txt.charAt(i) == 140)
                        circunflexI++;

                    else if (txt.charAt(i) == 162)
                        acuteO++;
                    else if (txt.charAt(i) == 148)
                        craseO++;
                    else if (txt.charAt(i) == 228)
                        tilO++;
                    else if (txt.charAt(i) == 147)
                        circunflexO++;

                    else if (txt.charAt(i) == 163)
                        acuteU++;
                    else if (txt.charAt(i) == 151)
                        craseU++;
                    else if (txt.charAt(i) == 150)
                        circunflexU++;
                    
                }
            }

        } while(txt != null);

        // Acento agudo
        MyIO.print((char)160 + "(" + acuteA + ") ");
        MyIO.print((char)130 + "(" + acuteE + ") ");
        MyIO.print((char)161 + "(" + acuteI + ") ");
        MyIO.print((char)162 + "(" + acuteO + ") ");
        MyIO.print((char)163 + "(" + acuteU + ") ");

        // Crase
        MyIO.print((char)133 + "(" + craseA + ") ");
        MyIO.print((char)138 + "(" + craseE + ") ");
        MyIO.print((char)141 + "(" + craseI + ") ");
        MyIO.print((char)149 + "(" + craseO + ") ");
        MyIO.print((char)151 + "(" + craseU + ") ");

        // Til
        MyIO.print((char)198 + "(" + tilA + ") ");
        MyIO.print((char)228 + "(" + tilO + ") ");

        // Acento circunflexo
        MyIO.print((char)131 + "(" + circunflexA + ") ");
        MyIO.print((char)136 + "(" + circunflexE + ") ");
        MyIO.print((char)140 + "(" + circunflexI + ") ");
        MyIO.print((char)147 + "(" + circunflexO + ") ");
        MyIO.print((char)150 + "(" + circunflexU + ") ");

    }

    /**
     * Calcula o número de consoantes e printa na tela
     * @param leitor
     * @throws IOException
     */
    public static void numberOfConsonants(BufferedReader leitor) throws IOException {
        String txt = "";
        int consonants = 0;

        do {
            
            try {
				txt = leitor.readLine();
			} catch (IOException e) {                
                e.printStackTrace();
            }

            if (txt != null) {

                for (int i = 0; i < txt.length(); i++) {
                    if (isNotVowel(txt.charAt(i)))
                        consonants++;
                }
            }

        } while (txt != null);

        MyIO.print("consoante(" + consonants + ") ");
    }

    /**
     * Calcula o número de quebras de linha HTML (<br>) e printa na tela
     * @param leitor
     * @throws IOException
     */
    public static void numberOfBreaks(BufferedReader leitor) throws IOException {
        String txt = "";
        int brs = 0;

        do {
            
            try {
				txt = leitor.readLine();
			} catch (IOException e) {                
                e.printStackTrace();
            }

            if (txt != null) {

                for (int i = 0; i < txt.length(); i++) {
                    if (txt.charAt(i) == '<') {
                        if (txt.charAt(i+1) == 'b' && txt.charAt(i+2) == 'r' && txt.charAt(i+3) == '>')
                            brs++;
                    }
                }
            }

        } while (txt != null);

        MyIO.print("<br>(" + brs + ") ");
    }

    /**
     * Calcula o número de quebras de linha HTML (<br>) e printa na tela
     * @param leitor
     * @throws IOException
     */
    public static void numberOfTables(BufferedReader leitor) throws IOException {
        String txt = "";
        int tables = 0;

        do {
            
            try {
				txt = leitor.readLine();
			} catch (IOException e) {                
                e.printStackTrace();
            }

            if (txt != null) {

                for (int i = 0; i < txt.length(); i++) {
                    if (txt.charAt(i) == '<') {
                        if (txt.charAt(i+1) == 't' && 
                            txt.charAt(i+2) == 'a' && 
                            txt.charAt(i+3) == 'b' &&
                            txt.charAt(i+4) == 'l' &&
                            txt.charAt(i+5) == 'e' &&
                            txt.charAt(i+6) == '>')
                                tables++;
                    }
                }
            }

        } while (txt != null);

        MyIO.print("<tables>(" + tables + ") ");
    }

    /**
     * Faz a leitura de uma (char)160ina html
     * @param page
     * @param url
     * @throws Exception
     */
    public static void readHTMLPage(String page, String url) throws Exception {
        URL address = new URL(url);
        InputStreamReader reader = new InputStreamReader(address.openStream());
        BufferedReader buffer = new BufferedReader(reader);

        try {

            numberOfVogals(buffer);
            numberOfVogalsWithAccent(buffer);
            numberOfConsonants(buffer);
            numberOfBreaks(buffer);
            numberOfTables(buffer);
            MyIO.print("nomepágina(" + page + ")");

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void main(String[] args) {
        String name = "";
        String url = "";

        MyIO.setCharset("UTF-8");

        do {
            
            name = MyIO.readLine();

            if (isNotEnd(name)) {
                url = MyIO.readLine();
                try {
                    readHTMLPage(name, url);
                } catch(Exception e) { System.out.println(e); }
            }

        } while (isNotEnd(name));
    }
}