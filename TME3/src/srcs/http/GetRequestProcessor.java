package src.srcs.http;

import java.io.*;
import java.net.*;
import java.util.*;

public class GetRequestProcessor implements RequestProcessor {

    @Override
    public void process(Socket connexion) {
        try (InputStream in = connexion.getInputStream();
             OutputStream out = connexion.getOutputStream()) {

            // Lire la requête HTTP
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String requestLine = reader.readLine();

            if (requestLine == null) {
                System.out.println("Requête vide ou client déconnecté.");
                return;
            }

            System.out.println("Requête reçue: " + requestLine);

            // Extraire la méthode et le fichier demandé
            StringTokenizer tokens = new StringTokenizer(requestLine);
            String method = tokens.nextToken().toUpperCase(); // GET, POST...
            String requestedFile = tokens.nextToken(); // Nom du fichier

            // Lire et ignorer les en-têtes restants
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    break; // Fin des en-têtes HTTP
                }
            }

            // Vérifier si la requête est bien une requête GET
            if (!"GET".equals(method)) {
                sendError(out, 400, "Bad Request");
                return;
            }

            // Construire le chemin du fichier demandé
            String rootDirectory = System.getProperty("user.dir");
            File file = new File(rootDirectory, requestedFile);

            // Vérifier si le fichier existe et s'il est valide
            if (!file.exists() || file.isDirectory()) {
                System.out.println("Erreur 404 : Fichier non trouvé - " + file.getAbsolutePath());
                sendError(out, 404, "Not Found");
                return;
            }

            // Détecter le type MIME du fichier
            String contentType = getContentType(file);
            
            // Envoyer l'en-tête HTTP de la réponse
            String header = "HTTP/1.1 200 OK\r\n" +
                            "Content-Type: " + contentType + "\r\n" +
                            "Content-Length: " + file.length() + "\r\n" +
                            "\r\n"; // Fin des en-têtes
            out.write(header.getBytes());

            // Envoyer le contenu du fichier
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }

            out.flush(); // S'assurer que tout est bien envoyé

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Détection du type MIME basé sur l'extension du fichier
    private String getContentType(File file) {
        String fileName = file.getName().toLowerCase();
        
        if (fileName.endsWith(".html") || fileName.endsWith(".htm")) {
            return "text/html; charset=UTF-8";
        } else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
            return "image/jpeg";
        } else if (fileName.endsWith(".png")) {
            return "image/png";
        } else if (fileName.endsWith(".gif")) {
            return "image/gif";
        } else if (fileName.endsWith(".css")) {
            return "text/css; charset=UTF-8";
        } else if (fileName.endsWith(".js")) {
            return "application/javascript; charset=UTF-8";
        } else {
            return "application/octet-stream"; // Par défaut, fichier binaire
        }
    }

    private void sendError(OutputStream out, int statusCode, String message) throws IOException {
        // Page d'erreur HTML pour 404
        String response = "<html><head><title>" + statusCode + " " + message + "</title></head>" +
                          "<body><h1>" + statusCode + " " + message + "</h1>" +
                          "<p>Le fichier que vous cherchez n'a pas été trouvé sur le serveur.</p></body></html>";

        // Construction de l'en-tête HTTP de la réponse avec la bonne taille et l'encodage UTF-8
        String header = "HTTP/1.1 " + statusCode + " " + message + "\r\n" +
                        "Content-Type: text/html; charset=UTF-8\r\n" +
                        "Content-Length: " + response.length() + "\r\n" +
                        "\r\n"; // Fin des en-têtes

        // Envoi de l'en-tête HTTP
        out.write(header.getBytes("UTF-8"));

        // Envoi du contenu HTML de la page d'erreur
        out.write(response.getBytes("UTF-8"));
        out.flush(); // S'assurer que tout est bien envoyé
    }
}

