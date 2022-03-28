import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.Date;
import java.text.DecimalFormat;

/** Passagem de voo */
class Passagem  {
    public String origem;
    public String destino;
    public String data;
    public double valor;
    public int quantidade; // quantidade de passagens disponiveis

    public Passagem(String origem, String destino, double valor, String data, int quantidade){
        this.origem = origem;
        this.destino = destino;
        this.valor = valor;
        this.data = data;
        this.quantidade = quantidade;
    }

    public String verificaQuantidade() {
        if (this.quantidade == 0)
            return "(ESGOTADA)";
        else if (this.quantidade > 1)
            return "(" + this.quantidade + " disponíveis)";
        else
            return "(Última disponível!)";
    }

    /** Retorna as informações da passagem */
    public String toString(){
        return this.data + "\t" 
            + this.origem + " -> " 
            + this.destino 
            + "\t R$ " + new DecimalFormat("00.00").format(this.valor) 
            + "\t " + verificaQuantidade();
    }
}

public class Servidor extends Thread {

    /** Porta utilizada para acessar o servidor */
    private final static int portaServidor = 4000;
    
    /** Socket de conexão */
    private final Socket conexaoServidor;
    private ObjectInputStream pacoteEntrada;
    private ObjectOutputStream pacoteSaida;

    private static int qtdClientes = 0;

    private int idCliente;

    /* Constantes para manipular texto no terminal */
    private final static String ANSI_GREEN = "\u001B[32m";
    private final static String ANSI_RED = "\u001B[31m";
    private final static String ANSI_RESET = "\u001B[0m";
    private final static String ANSI_CLEAR_SCREEN = "\033[2J";
    private final static String ANSI_CURSOR_BEGIN = "\033[H";
    
    /** Array de passagens aéreas */
    private static Passagem[] passagens = {
        new Passagem("Belo Horizonte", "São Paulo", 120.99, "20/04/2022 22:30", 10),
        new Passagem("São Paulo", "Belo Horizonte", 99.90, "25/04/2022 18:00", 8),
    };

    /** Construtor do servidor - Define a variável de conexão */
    public Servidor(Socket conexaoServidor) {
        this.conexaoServidor = conexaoServidor;
        qtdClientes++;
        this.idCliente = qtdClientes;
        clientesConectados();
    }

    /** Encerra a conexão com o servidor e atualiza os clientes conectados */
    public void close() {
        try {
            enviarMensagem("FIN");
            qtdClientes--;
            this.conexaoServidor.close();
            this.pacoteEntrada.close();
            this.pacoteSaida.close();
            clientesConectados();
            System.out.println("> Cliente " + this.idCliente + ANSI_RED + " desconectado!" + ANSI_RESET);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Envia uma mensagem ao cliente */
    public void enviarMensagem(String mensagem) {
        try {
            this.pacoteSaida = new ObjectOutputStream(this.conexaoServidor.getOutputStream());
            pacoteSaida.writeObject(mensagem);
            pacoteSaida.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Recebe uma mensagem do cliente */
    public String receberMensagem() {
        String mensagem = null;
        try {
            this.pacoteEntrada = new ObjectInputStream(this.conexaoServidor.getInputStream());
            mensagem = pacoteEntrada.readObject().toString();
        } catch (Exception e) {
            System.out.println("Ops! Algo inesperado aconteceu");
            e.printStackTrace();
        }
        return mensagem;
    }

    /** Retorna uma string contendo as passagens disponíveis */
    public String getCatalogoPassagens(){
        String catalogoPassagens = "";
        for (int i = 0; i < Servidor.passagens.length; i++){
            catalogoPassagens += ("["+(i+1)+"] - " + Servidor.passagens[i].toString() + "\n");
        }
        return catalogoPassagens;
    }

    public void run(){
        try {
            boolean encerrarConexao = false;
            do {
                enviarMensagem(
                    "******************************\n" +
                    "*         Bem vindo!         *\n" +
                    "*    Reserva de Passagens    *\n" +
                    "******************************\n\n" +
                    getCatalogoPassagens() + 
                    "\n[0] - Sair\n"
                );

                String mensagem = receberMensagem();

                if(mensagem!=null && !mensagem.equals("0")){
                    // Realiza compra da passagem
                    int index = Integer.parseInt(mensagem)-1;
                    if (Servidor.passagens[index].quantidade > 0) {
                        Servidor.passagens[index].quantidade--;
                    }
                }
                else 
                    encerrarConexao = true;

            } while (!encerrarConexao);
            
            // Finalizar a conexao:
            close();
        } catch (Exception e) { 
            e.printStackTrace();
        }
    }
    
    public static void inicio() {
        System.out.print(ANSI_CURSOR_BEGIN + ANSI_CLEAR_SCREEN);
        System.out.println("******************************");
        System.out.println("*" + ANSI_GREEN + "         Sistema ON         " + ANSI_RESET + "*");
        System.out.println("*    Reserva de Passagens    *");
        System.out.println("******************************\n ");
    }

    public static void clientesConectados() {
        inicio();
        System.out.print(qtdClientes);
        if (qtdClientes > 1)
            System.out.println(" clientes " + ANSI_GREEN + "conectados" + ANSI_RESET + "\n");
        else
            System.out.println(" cliente " + ANSI_GREEN + "conectado" + ANSI_RESET + "\n");
    }
    
    
    public static void main(String args[]) {
        try {
            // Inicializar um servidor de escuta por conexoes:
			inicio();
			ServerSocket socketServidor = new ServerSocket(portaServidor);
			
            while(true) {
                // Prepara para aceitar uma conexão
                Socket conexaoServidor = socketServidor.accept();

                Thread t = new Servidor(conexaoServidor);
                t.start();
            }
            
			
            // socketServidor.close();

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}