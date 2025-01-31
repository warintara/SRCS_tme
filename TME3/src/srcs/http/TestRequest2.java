package src.srcs.http;


	import java.io.*;
	import java.net.*;

	public class TestRequest2 {
	    public static void main(String[] args) {
	        String host = "www-ppti.ufr-info-p6.jussieu.fr";
	        int port = 80;

	        try (Socket socket = new Socket(host, port);
	             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

	            // Envoyer une requête GET minimale
	            out.println("GET /index.html HTTP/1.1");
	            out.println("Host: " + host);
	            out.println("Connection: close"); // Permet au serveur de fermer la connexion après la réponse
	            out.println(); // Ligne vide indiquant la fin des en-têtes

	            // Lire et afficher la réponse du serveur
	            String line;
	            while ((line = in.readLine()) != null) {
	                System.out.println(line);
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
