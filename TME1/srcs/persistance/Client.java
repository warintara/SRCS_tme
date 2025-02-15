package srcs.persistance;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Client implements Sauvegardable{

	
	private final String nom;
	private final Compte compte;

	
	public Client(String nom, Compte compte) {
		this.nom=nom;
		this.compte=compte;

	}
	public Client(InputStream in) throws IOException  {
		DataInputStream d = new DataInputStream(in);
		nom = d.readUTF();
		compte = new Compte(d);
		d.close();
	}
	
		
	public String getNom() {
		return nom;
	}


	public Compte getCompte() {
		return compte;
	}

	@Override
	public boolean equals(Object o) {
		if(o==this) return true;
		if(o==null) return false;
		if(!(o instanceof Compte)) return false;
		Client other= (Client) o;
		return other.nom.equals(nom);
	}

	@Override
	public void save(OutputStream out) throws IOException {
		DataOutputStream d = new DataOutputStream(out);
		d.writeUTF(nom);
		compte.save(d);
		d.flush();
	}
	
}
