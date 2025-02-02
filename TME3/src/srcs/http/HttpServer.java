package src.srcs.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class HttpServer {
    public static void main(String[] args) {
        int port = 8888;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serveur HTTP démarré sur le port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new GetRequestProcessor().process(clientSocket);
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

