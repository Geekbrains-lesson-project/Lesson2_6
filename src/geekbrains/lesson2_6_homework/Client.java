package geekbrains.lesson2_6_homework;


import java.io.IOException;
import java.net.Socket;

public class Client {

    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8888;

    public Client() {
        System.out.println("Подключено к localhost");
        try {
            Socket sock = new Socket(SERVER_ADDR, SERVER_PORT);
            new Threads(sock, "Клиент");
            while(true){
                if(sock.isClosed()){
                    break;
                }
            }
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client();
    }
}