package srcs.persistance;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

public class Banque implements Sauvegardable {

	private final Set<Client> clients;
	
	public Banque() {
		clients=new HashSet<>();
	}
	
	public Banque(InputStream in) throws IOException  {
		DataInputStream d = ((DataInputStream)in);
		String s = d.readUTF();
		String[] ss = s.split(" ");
		for(String compte : ss) {
			clients.add( new Client(compte));
		}
	}
		
	public int nbClients() {
		return clients.size();
	}
	
	public int nbComptes() {
		Set<Compte> comptes = new HashSet<>();
		for(Client c : clients) {
			comptes.add(c.getCompte());
		}
		return comptes.size();
	}
	
	public Client getClient(String nom) {
		for(Client c : clients) {
			if(c.getNom().equals(nom)) return c;
		}
		return null;
	}
	
	public boolean addNewClient(Client c) {
		return clients.add(c);
	}

	@Override
	public void save(OutputStream out) throws IOException {
		// TODO Auto-generated method stub
		
	}
	

}
