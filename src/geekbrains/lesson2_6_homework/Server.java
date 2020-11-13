package geekbrains.lesson2_6_homework;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public Server(){
        ServerSocket serv = null;
        Socket sock = null;
        try {
            serv = new ServerSocket(8888);
            System.out.println("Сервер запущен, ожидаем подключения...");
            sock = serv.accept();
            System.out.println("Клиент подключился");
            new Threads(sock, "Сервер");
            while(true){
                if(sock.isClosed()){
                    break;
                }
            }
            serv.close();
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Ошибка инициализации сервера");
        } finally {
            try {
                serv.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        new Server();
    }
}
