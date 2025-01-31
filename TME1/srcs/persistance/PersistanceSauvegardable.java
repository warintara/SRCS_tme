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
	    try (OutputStream out = new FileOutputStream(fichier);
	         DataOutputStream d = new DataOutputStream(out)) {
	        s.save(d);
	    }
	}

	public static Sauvegardable load(String fichier) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException  {
	    try (InputStream in = new FileInputStream(fichier);
	         DataInputStream d = new DataInputStream(in)) {
	        String name = d.readUTF();
	        String[] ss = name.split(" ");
	        String id = ss[0];
	        Class<?> cl = Class.forName(id);
	        
	        return (Sauvegardable) cl.getConstructor(InputStream.class).newInstance(d);
	    }
	}

}
