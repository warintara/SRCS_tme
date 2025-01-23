package srcs.persistance;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
	
	public Compte(InputStream in) throws IOException  {
		DataInputStream d = ((DataInputStream)in);
		String s = d.readUTF();
		String[] ss = s.split(" ");
		this.id = ss[1];
		this.solde = Integer.parseInt(ss[2]);
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
		((DataOutputStream)out).writeUTF(("Compte "+id + " " + sol));
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
