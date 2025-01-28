package srcs.persistance;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Compte implements Sauvegardable{

	
	
	private final String id;
	private double solde;
	

	public Compte(String id) {
		this.id=id;	
		this.solde=0.0;
	}
	
	public Compte(InputStream in) throws IOException {
	    DataInputStream d = ((DataInputStream) in);

	    if (d.available() > 0) { // Vérifie s'il y a des données disponibles
	        String s = d.readUTF();
	        String[] ss = s.split(" ");

	        if (ss.length < 3) { // Vérifie que le tableau contient les éléments attendus
	            throw new IOException("Format invalide des données pour Compte.");
	        }

	        this.id = ss[1];
	        this.solde = Integer.parseInt(ss[2]);
	    } else {
	        throw new EOFException("Le fichier ne contient pas assez de données.");
	    }
	}

		
	public String getId() {
		return id;
	}

	public double getSolde() {
		return solde;
	}

	public void crediter(double montant) {
		solde += montant;
	}

	public void debiter(double montant) {
		solde -= montant;
	}
	
	public void save(OutputStream out) throws IOException {
		int sol = (int) solde;
		((DataOutputStream)out).writeUTF((this.getClass().getName() + " " +id + " " + sol));
	}
	
	@Override
	public boolean equals(Object o) {
		if(o==this) return true;
		if(o==null) return false;
		if(!(o instanceof Compte)) return false;
		Compte other= (Compte) o;
		return other.id.equals(id);
	}
	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
}
