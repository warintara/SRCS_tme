package srcs.service.annulaire;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import srcs.service.MyProtocolException;
import srcs.service.Service;

public class AnnuaireService implements Service, Annuaire {
	static private Map<String,String> map = new HashMap<>();
	
	@Override
	public synchronized Object lookup(String nom) {
		return (nom == null)? "" : map.get(nom);
	}

	@Override
	public synchronized void bind(Object val, String nom) {
		map.put(nom, (String) val);

	}

	@Override
	public synchronized void unbind(String nom) {
		map.remove(nom);

	}

	@Override
	public synchronized void execute(Socket connextion) {
		try{
			ObjectOutputStream o = new ObjectOutputStream(connextion.getOutputStream());
			ObjectInputStream i = new ObjectInputStream(connextion.getInputStream());
			
			Object ret = i.readUTF();
			Integer x = i.readInt();
			Integer y = i.readInt();
			
			switch((String) ret) {
				case "add":
					o.writeObject(add(x, y));
					break;
				case "sous":
					o.writeObject(sous(x, y));
					break;
				case "mult":
					o.writeObject(mult(x, y));
					break;
				case "div":
					o.writeObject(div(x, y));
					break;
				default: 
					o.writeObject(new MyProtocolException(null));
			}
			
		}catch(Exception e ) {
			
		}

	}

}
