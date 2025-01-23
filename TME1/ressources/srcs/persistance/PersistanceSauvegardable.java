package srcs.persistance;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;

public class PersistanceSauvegardable {
	public static void save(String fichier, Sauvegardable s) throws IOException {
		//sauvegarder dans un fichier un objet Sauvegardable
		OutputStream out = new FileOutputStream(fichier);
		DataOutputStream d = new DataOutputStream(out);
		s.save(d);
		d.close();
		
	}
	public static Sauvegardable load(String fichier) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException, ClassNotFoundException {
		//instancie un Sauvegardable à partir des données d’un fichier.
		
		InputStream out = new FileInputStream(fichier);
		DataInputStream d = new DataInputStream(out);
		String name = d.readUTF();
		String[] ss = name.split(" ");
		String id = ss[0];
		Class<?> cl = Class.forName(id);
		
		return (Sauvegardable)cl.getConstructor(InputStream.class).newInstance(d);
		
	}
}
