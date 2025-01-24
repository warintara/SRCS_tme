package srcs.interpretor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Cat implements Command {
	private String[] commends; 
	 private final String filePath;
	
	public Cat(String[] commends) throws IllegalArgumentException{
		if(commends[0] != "cat ") {
			throw new IllegalArgumentException("Commend echo manquant");
		}
		if(commends.length == 2 ) {
			throw new IllegalArgumentException("Il faut 1 fichier ");
		}
		this.filePath = commends[1];
		File f = new File(commends[1]);
		if(! f.exists()) {
			throw new IllegalArgumentException("Ce n'est pas un fichier régulier ");
		}
		this.commends = commends;
		
	}
	 @Override
	    public void execute(PrintStream out) {
			File file = new File(filePath);
	        try (FileInputStream fis = new FileInputStream(file);
	             Scanner scanner = new Scanner(fis, StandardCharsets.UTF_8)) {

	            // Lire et écrire chaque ligne du fichier
	            while (scanner.hasNextLine()) {
	                out.println(scanner.nextLine());
	            }
	        } catch (IOException e) {
	            out.println("Erreur lors de la lecture du fichier : " + e.getMessage());
	        }
	    }


}
