package geekbrains.lesson2_6_homework;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Threads {
    private Scanner in;
    private Scanner input;
    private PrintWriter out;
    private Thread threadIn;
    private Thread threadOut;
    public Threads(Socket sock, String name) {
        try {
            in = new Scanner(sock.getInputStream());
            input = new Scanner(System.in);
            out = new PrintWriter(sock.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        threadOut = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (input.hasNext()) {
                        String txt = input.nextLine();
                        sendMsg(name+": "+txt);
                        if (txt.equalsIgnoreCase("-exit")) System.out.println("Соединение закрыто"); break;
                    }
                }
                close(sock);

            }
        });
        threadOut.start();
        threadIn = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (in.hasNext()) {
                        String txt = in.nextLine();
                        System.out.println(txt);
                        if (txt.contains("-exit")) System.out.println("Соединение закрыто"); break;
                    }
                }
                close(sock);
            }
        });
        threadIn.start();
    }
    private void sendMsg(String w) {
        out.println(w);
        out.flush();
    }
    private void close(Socket sock){
        threadIn.interrupt();
        threadOut.interrupt();
        try {
            sock.close();
        } catch (Exception e) {}

    }
}