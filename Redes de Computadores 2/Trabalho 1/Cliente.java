import java.net.Socket;
import java.io.*;
import java.util.Scanner;

public class Cliente {
    private final static String ANSI_CLEAR_SCREEN = "\033[2J";
    private final static String ANSI_CURSOR_BEGIN = "\033[H";
    private static final int portaServidor = 4000;
    private static final String ipServidor = "127.0.0.1";

    public static void main(String args[]){
        Scanner leitor = new Scanner(System.in);
		
		try {
            // Estabelecer conexao com o servidor:
			Socket socketCliente = new Socket (ipServidor, portaServidor);
			String mensagem;

            do {
                // Criar um pacote de entrada para receber mensagens, associando-o a conexao:
                ObjectInputStream sCliIn = new ObjectInputStream (socketCliente.getInputStream());
                mensagem = sCliIn.readObject().toString(); // Espera por um pacote
    
				if (!mensagem.equals("FIN")) {
					// Processa o pacote recebido:
					System.out.println(ANSI_CURSOR_BEGIN + ANSI_CLEAR_SCREEN + mensagem);

					// Criar um pacote de saida para enviar mensagens, associando-o a conexao:
					ObjectOutputStream sCliOut = new ObjectOutputStream(socketCliente.getOutputStream());
					String entrada = leitor.nextLine();
					sCliOut.writeObject(entrada); // Escreve no pacote
					sCliOut.flush(); // Envia o pacote
				}

                else {
                    System.out.println(ANSI_CURSOR_BEGIN + ANSI_CLEAR_SCREEN + "> Execução encerrada\n\n\n");
                }
                
            } while (!mensagem.equals("FIN"));

			// Finaliza a conexao:
			socketCliente.close();
			leitor.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
