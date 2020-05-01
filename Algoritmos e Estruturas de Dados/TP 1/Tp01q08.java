import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

class Tp01q08 {

    private static void r_printValues(RandomAccessFile file, long index, long n) throws Exception {
        file.seek(index);
        String value = file.readUTF();

        if (index != n) {
            r_printValues(file, file.getFilePointer(), n);
        }

        MyIO.println(value);
    }

    /**
     * Printa, recursivamente, os valores salvos no arquivo de trás pra frente
     * @param n
     * @throws Exception
     */
    public static void printValues(long n) throws Exception {
        RandomAccessFile file = new RandomAccessFile("./tp_08_values.txt", "r");
        r_printValues(file, 0, n);
        file.close();
    }

    /**
     * Lê n valores do usuário e os salva-os sequencialmente no arquivo específico
     * @param n - Quantidade de valores
     * @return O ponteiro pra ultima posição do arquivo
     * @throws IOException
     */
    public static long readAndSaveValues(int n) throws IOException {
        RandomAccessFile file = new RandomAccessFile("./tp_08_values.txt", "rw");
        long filePointer = 0;
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);

        for (int i = 0; i < n; i++) {
            // Salvando o ponteiro pra ultima posição do arquivo
            filePointer = file.getFilePointer();

            DecimalFormat df = new DecimalFormat("0.###", symbols);
            file.writeUTF(df.format(MyIO.readDouble()));
        }

        file.close();

        return filePointer;
    }

    public static void main(String[] args) {
        int n = MyIO.readInt();

        try {
            long finalPointer = readAndSaveValues(n);
            printValues(finalPointer);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}