package srcs.persistance;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PersistanceCompte {
	public static void saveCompte(String f, Compte e) throws IOException {
		OutputStream out = new FileOutputStream(f);
		DataOutputStream d = new DataOutputStream(out);
		e.save(d);
		d.close();
	}
	public  static Compte loadCompte(String f)throws IOException{
		InputStream out = new FileInputStream(f);
		DataInputStream d = new DataInputStream(out);
		
		Compte c = new Compte(d);
		return c;
		
	}

}
